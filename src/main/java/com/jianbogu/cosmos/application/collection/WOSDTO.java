package com.jianbogu.cosmos.application.collection;

import com.alibaba.fastjson.annotation.JSONField;
import com.jianbogu.cosmos.core.collection.BaseDTO;

import java.util.List;

public class WOSDTO extends BaseDTO {
    @JSONField(name = "WOSId")
    private String WOSId;
    @JSONField(name = "abstract")
    private String abs;
    @JSONField(name = "authors")
    private List<String> authors;
    @JSONField(name = "categories")
    private List<String> categories;

    @JSONField(name = "score")
    private double score;

    @JSONField(name = "highlight_title")
    private String highlightTitle;

    public String getWOSId() {
        return WOSId;
    }

    public void setWOSId(String WOSId) {
        this.WOSId = WOSId;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getHighlightTitle() {
        return highlightTitle;
    }

    public void setHighlightTitle(String highlightTitle) {
        this.highlightTitle = highlightTitle;
    }
}
