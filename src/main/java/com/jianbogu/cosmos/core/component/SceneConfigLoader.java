package com.jianbogu.cosmos.core.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class SceneConfigLoader implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ScenePipeline scenePipeline;
    @Override
    public void run(String... args) throws Exception {
        ClassPathResource resource = new ClassPathResource(scenePipeline.getFile());
        InputStream inputStream = resource.getInputStream();
        String jsonStr = IOUtils.toString(inputStream, Charsets.toCharset("utf-8"));

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        for(String scene: jsonObject.keySet()){
            logger.info("scene:"+scene+", json:"+ jsonObject.get(scene));
            scenePipeline.setScene(scene, (JSONArray) jsonObject.get(scene));
        }
    }
}
