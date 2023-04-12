package com.jianbogu.cosmos.core.component;

import com.alibaba.fastjson.JSONArray;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 从配置文件中读取的pipeiline流程
 */
@Component
@ConfigurationProperties(prefix = "scene")
public class ScenePipeline {
    private Map<String, JSONArray> scenes;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    String file = "";

    public void setScene(String sceneId, JSONArray pipeline){
        if(scenes==null){
            scenes = new HashMap<>();
        }
        scenes.put(sceneId, pipeline);
    }

    public JSONArray getScenePipeline(String sceneId){
        if(!scenes.containsKey(sceneId)){
            return null;
        }
        return scenes.get(sceneId);
    }
}
