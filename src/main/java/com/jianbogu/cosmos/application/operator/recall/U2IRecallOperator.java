package com.jianbogu.cosmos.application.operator.recall;

import com.jianbogu.cosmos.application.collection.PaperDTO;
import com.jianbogu.cosmos.core.collection.BaseDTO;
import com.jianbogu.cosmos.core.context.RequestContext;
import com.jianbogu.cosmos.core.operator.RecallOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class U2IRecallOperator extends RecallOperator {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public List<BaseDTO> doRecall(RequestContext context, List<BaseDTO> items) {
        logger.info("Processor "+this.getClass().getName()+ " start!");
        List<BaseDTO> items_new = new ArrayList<>();
        for(int i=0; i<5; i++){
            PaperDTO item = new PaperDTO(i+3000, "u2i_item"+(i+3000));
            items_new.add(item);
            try {
                Thread.sleep(300);
            }catch (InterruptedException e){
                logger.info(e.toString());
            }
            logger.info("Processor "+this.getClass().getName()+ " time "+ (i*300));
        }
        return items_new;
    }
}
