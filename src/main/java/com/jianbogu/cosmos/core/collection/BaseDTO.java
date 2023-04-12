package com.jianbogu.cosmos.core.collection;

import com.alibaba.fastjson.annotation.JSONField;

public class BaseDTO {
    @JSONField(name = "id")
    protected int id;
    @JSONField(name = "title")
    protected String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
