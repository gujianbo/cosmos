package com.jianbogu.cosmos.core.collection;

import com.alibaba.fastjson.annotation.JSONField;

public class BaseDTO {
    @JSONField(name = "id")
    protected long id;
    @JSONField(name = "title")
    protected String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
