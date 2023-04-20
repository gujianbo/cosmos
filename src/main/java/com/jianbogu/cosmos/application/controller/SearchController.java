package com.jianbogu.cosmos.application.controller;

import com.alibaba.fastjson.JSONObject;
import com.jianbogu.cosmos.application.service.WOSSearchRecService;
import com.jianbogu.cosmos.core.service.SearchRecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jianbogu.cosmos.application.operator.search.WOSSearchOperator;

import java.util.Map;

@Controller
public class SearchController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    WOSSearchRecService wosSearchRecService;

    @ResponseBody
    @GetMapping("/search/{scene_id}")
    public JSONObject search(@PathVariable("scene_id") String scene_id, @RequestParam Map<String, String> params){
        logger.info("search service in scene " + scene_id);
        JSONObject res = wosSearchRecService.run(scene_id, params);
        return res;
    }
}
