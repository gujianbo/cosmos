package com.jianbogu.cosmos.core.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jianbogu.cosmos.core.collection.BaseDTO;
import com.jianbogu.cosmos.core.component.ScenePipeline;
import com.jianbogu.cosmos.core.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchRecService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ScenePipeline scenePipeline;
    public RequestContext init(Map<String, String> params){
        RequestContext requestContext = new RequestContext();
        int userId = 0;
        if(params.containsKey("user_id")) {
            userId = Integer.valueOf(params.get("user_id"));
        }
        requestContext.setUserId(userId);

        int pageSize = 10;
        if(params.containsKey("page_size")) {
            pageSize = Integer.valueOf(params.get("page_size"));
        }
        requestContext.setPageSize(pageSize);

        int pageIndex = 1;
        if(params.containsKey("page_index")) {
            pageIndex = Integer.valueOf(params.get("page_index"));
        }
        requestContext.setPageIndex(pageIndex);

        String steamId = "0";
        if(params.containsKey("steam_id")) {
            steamId = params.get("steam_id");
        }
        requestContext.setStreamId(steamId);

        requestContext.setExtInfo(params);
        return requestContext;
    }

    @Autowired
    OperatorAsyncService operatorAsyncService;
    public List<BaseDTO> process(JSONArray pipeline, RequestContext requestContext){
        List<BaseDTO> items = new ArrayList<>();
        for(Object tmp: pipeline){
            JSONObject processor = (JSONObject) tmp;
            String opratorType = (String) processor.getOrDefault("type", "single");
            // 单个任务
            if("single".equals(opratorType)){
                String processorName = (String) processor.get("processor");
                try {
                    items = operatorAsyncService.singleOperator(requestContext, items, processor);
                }catch (Exception e){
                    logger.error("Exception-" + e);
                    items = new ArrayList<>();
                }
                logger.info("Processor " + processorName + " done!");
                continue;
            }
            // 并发任务
            JSONArray asyncProcessors = (JSONArray) processor.get("processors");

            List<CompletableFuture<List<BaseDTO>>> taskList = new ArrayList<>();
            for(Object processorTmp: asyncProcessors) {
                JSONObject processorOp = (JSONObject) processorTmp;
                String processorOpName = (String) processorOp.get("processor");
                try {
                    CompletableFuture<List<BaseDTO>> task = operatorAsyncService.asyncOperator(requestContext, items, processorOp);
                    taskList.add(task);
                }catch (Exception ex){
                    logger.error("Processor " + processorOpName + ", Parallel Exception-" + ex);
                }
            }
            CompletableFuture.allOf(taskList.toArray(new CompletableFuture[taskList.size()])).join();
            logger.info("Parallel processors done!");
            items.clear();
            for(int i=0; i<taskList.size(); i++){
                try {
                    List<BaseDTO> res_item = taskList.get(i).get();
                    items.addAll(res_item);
                }catch(Exception e){
                    logger.error("Task " + i + " Exception-" + e);
                }
            }
        }

        return items;
    }

    public JSONObject run(String scene_id, Map<String, String> params){
        RequestContext requestContext = init(params);
        JSONObject jsonObject = new JSONObject();
        if(scenePipeline.getScenePipeline(scene_id) == null) {
            jsonObject.put("status", "error");
            jsonObject.put("error_info", "scene_id not found!");
            return jsonObject;
        }
        JSONArray pipeline = scenePipeline.getScenePipeline(scene_id);
        List<BaseDTO> items = process(pipeline, requestContext);
        JSONArray recRes = dumpJson(items);
        jsonObject.put("status", "success");
        jsonObject.put("rec_result", recRes);
        jsonObject.put("rec_num", items.size());

        return jsonObject;
    }

    public JSONArray dumpJson(List<BaseDTO> items) {
        JSONArray array = new JSONArray();
        for(BaseDTO itemTmp: items){
            JSONObject itemJson = (JSONObject)JSONObject.toJSON(itemTmp);
            array.add(itemJson);
        }
        return array;
    }
}
