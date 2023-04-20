package com.jianbogu.cosmos.core.operator;

import com.jianbogu.cosmos.core.collection.BaseDTO;
import com.jianbogu.cosmos.core.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class FilterOperator extends BaseOperator {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public List<BaseDTO> run(RequestContext context, List<BaseDTO> items){
        long startTime = System.currentTimeMillis();
        List<BaseDTO> new_item = null;
        try {
            new_item = this.doFilter(context, items);
        } catch (Exception e){
            logger.info(this.getClass().getName() + " throws exception:" + e);
            for(StackTraceElement stackTraceElement: e.getStackTrace()){
                logger.info(stackTraceElement.toString());
            }
            new_item = new ArrayList<>();
        }
        long endTime = System.currentTimeMillis();
        long TotalTime = endTime - startTime;
        logger.info(this.getClass().getName() + " consume time:" + TotalTime + "ms, get Results count:"
                + (new_item==null?0:new_item.size()));
        return new_item;
    }

    public abstract List<BaseDTO> doFilter(RequestContext context, List<BaseDTO> items);
}
