package com.jianbogu.cosmos.application.service;

import com.alibaba.fastjson.JSONObject;
import com.jianbogu.cosmos.application.context.WOSRequestContext;
import com.jianbogu.cosmos.core.context.RequestContext;
import com.jianbogu.cosmos.core.service.SearchRecService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WOSSearchRecService extends SearchRecService {
    public RequestContext init(Map<String, String> params){
        RequestContext requestContext = new WOSRequestContext();
        requestContext.setParams(params);
        return requestContext;
    }
//
//    public JSONObject run(String scene_id, Map<String, String> params){
//        return super.run(scene_id, params);
//    }
}
