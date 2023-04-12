package com.jianbogu.cosmos.application.operator.load;

import com.jianbogu.cosmos.core.collection.BaseDTO;
import com.jianbogu.cosmos.core.context.RequestContext;
import com.jianbogu.cosmos.core.operator.LoadOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserProfileOperator extends LoadOperator {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public List<BaseDTO> doLoad(RequestContext context, List<BaseDTO> items) {
        int uid = context.getUserId();
        logger.info("load getUserId:" + uid);
        try {
            Thread.sleep(300);
        }catch (InterruptedException e){
            logger.info(e.toString());
        }
        return items;
    }
}
