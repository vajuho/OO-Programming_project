package com.example.oo_ohjelmointi_projekti;

import java.util.ArrayList;

public class WikipediaData {
    private ArrayList<String> wikiUrl;

    public WikipediaData(ArrayList<String> wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public ArrayList<String>getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(ArrayList<String> wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

}
