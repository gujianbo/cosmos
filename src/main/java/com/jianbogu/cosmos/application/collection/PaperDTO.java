package com.jianbogu.cosmos.application.collection;

import com.alibaba.fastjson.annotation.JSONField;
import com.jianbogu.cosmos.core.collection.BaseDTO;

import java.util.ArrayList;
import java.util.List;

public class PaperDTO extends BaseDTO {
    @JSONField(name = "abstract")
    private String abs;
    @JSONField(name = "authors")
    private List<String> authors;
    @JSONField(serialize = false)
    private List<String> areas;
    @JSONField(name = "tags")
    private List<String> tags;
    @JSONField(name = "conference")
    private String conference;
    @JSONField(name = "year")
    private String year;
    public PaperDTO(int id){
        this.id = id;
        authors = new ArrayList<>();
    }
    public PaperDTO(int id, String title){
        this(id);
        this.title = title;
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

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
