package com.jianbogu.cosmos.application.controller;

import com.alibaba.fastjson.JSONObject;
import com.jianbogu.cosmos.core.service.SearchRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RecController {
    @Autowired
    SearchRecService searchRecService;

    @ResponseBody
    @GetMapping("/rec/{scene_id}")
    public JSONObject rec(@PathVariable("scene_id") String scene_id, @RequestParam Map<String, String> params){
        JSONObject res = searchRecService.run(scene_id, params);
        return res;
    }
}
