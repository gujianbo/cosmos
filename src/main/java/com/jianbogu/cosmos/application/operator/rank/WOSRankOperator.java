package com.jianbogu.cosmos.application.operator.rank;

import com.jianbogu.cosmos.application.collection.PaperDTO;
import com.jianbogu.cosmos.application.collection.WOSDTO;
import com.jianbogu.cosmos.core.collection.BaseDTO;
import com.jianbogu.cosmos.core.context.RequestContext;
import com.jianbogu.cosmos.core.operator.RankOperator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WOSRankOperator extends RankOperator {
    @Override
    public List<BaseDTO> doRank(RequestContext context, List<BaseDTO> items) {
        Collections.sort(items, (o1, o2) -> {
            double val = ((WOSDTO)o2).getScore() - ((WOSDTO)o1).getScore();
            if(val > 0){
                return 1;
            }else if(val < 0){
                return -1;
            }else{
                return 0;
            }
        });
        return items;
    }
}
