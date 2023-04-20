package com.jianbogu.cosmos.application.context;

import com.jianbogu.cosmos.core.context.RequestContext;

import java.util.Map;

public class WOSRequestContext extends RequestContext {
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private String query = "";
    public void setParams(Map<String, String> params){
        int userId = 0;
        if(params.containsKey("user_id")) {
            userId = Integer.valueOf(params.get("user_id"));
        }
        this.setUserId(userId);

        int pageSize = 10;
        if(params.containsKey("page_size")) {
            pageSize = Integer.valueOf(params.get("page_size"));
        }
        this.setPageSize(pageSize);

        int pageIndex = 1;
        if(params.containsKey("page_index")) {
            pageIndex = Integer.valueOf(params.get("page_index"));
        }
        this.setPageIndex(pageIndex);

        String steamId = "0";
        if(params.containsKey("steam_id")) {
            steamId = params.get("steam_id");
        }
        this.setStreamId(steamId);

        String query = "";
        if(params.containsKey("query")) {
            query = params.get("query");
        }
        this.setQuery(query);

        this.setExtInfo(params);
    }
}
