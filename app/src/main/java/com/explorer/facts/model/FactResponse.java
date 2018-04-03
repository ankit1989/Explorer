package com.explorer.facts.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ankitpatel on 3/4/18.
 */

public class FactResponse {

    private String title;
    @SerializedName("rows")
    private List<Fact> facts;

    public FactResponse (String title, List<Fact> facts) {
        this.title = title;
        this.facts = facts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Fact> getFacts() {
        return facts;
    }

    public void setFacts(List<Fact> facts) {
        this.facts = facts;
    }
}
