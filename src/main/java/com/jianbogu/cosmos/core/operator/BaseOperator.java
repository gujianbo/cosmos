package com.jianbogu.cosmos.core.operator;

import com.jianbogu.cosmos.core.collection.BaseDTO;
import com.jianbogu.cosmos.core.context.RequestContext;

import java.util.List;

public abstract class BaseOperator {
    public abstract List<BaseDTO> run(RequestContext context, List<BaseDTO> items);
}