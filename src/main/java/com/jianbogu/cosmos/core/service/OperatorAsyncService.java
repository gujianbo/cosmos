package com.jianbogu.cosmos.core.service;

import com.alibaba.fastjson.JSONObject;
import com.jianbogu.cosmos.core.collection.BaseDTO;
import com.jianbogu.cosmos.core.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OperatorAsyncService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Async("element_operator")
    public CompletableFuture<List<BaseDTO>> asyncOperator(RequestContext requestContext, List<BaseDTO> items, JSONObject processor) throws InterruptedException,
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
        List<BaseDTO> new_items = singleOperator(requestContext, items, processor);
        return CompletableFuture.completedFuture(new_items);
    }

    public List<BaseDTO> singleOperator(RequestContext requestContext, List<BaseDTO> items, JSONObject processor) throws InterruptedException,
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String processorName = (String) processor.get("processor");
        int timeout = (Integer) processor.get("timeout");

        Class clz = Class.forName(processorName);
        Method method = clz.getMethod("run", RequestContext.class, List.class);
        Constructor constructor = clz.getConstructor();
        Object object = constructor.newInstance();
        Object res = method.invoke(object, requestContext, items);
        return (ArrayList<BaseDTO>)res;
    }
}
