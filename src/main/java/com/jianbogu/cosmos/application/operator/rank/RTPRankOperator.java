package com.jianbogu.cosmos.application.operator.rank;

import com.jianbogu.cosmos.application.collection.PaperDTO;
import com.jianbogu.cosmos.core.collection.BaseDTO;
import com.jianbogu.cosmos.core.context.RequestContext;
import com.jianbogu.cosmos.core.operator.RankOperator;

import java.util.ArrayList;
import java.util.List;

public class RTPRankOperator extends RankOperator {
    @Override
    public List<BaseDTO> doRank(RequestContext context, List<BaseDTO> items) {
        List<BaseDTO> items_new = new ArrayList<>();
        for(int i=0; i<items.size(); i++){
            PaperDTO item = (PaperDTO) items.get(i);
            String title = item.getTitle();
            item.setTitle(title+"_rank");
            items_new.add(item);
        }
        return items_new;
    }
}

