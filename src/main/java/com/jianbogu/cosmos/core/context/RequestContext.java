package com.jianbogu.cosmos.core.context;

import java.util.Map;

public class RequestContext {
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private String query = "";

    private int userId;
    private Map<String, String> extInfo;
    private int pageSize;
    private int pageIndex;
    private String streamId;

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
