package com.example.oo_ohjelmointi_projekti;

import java.util.ArrayList;

public class WikipediaData {
    private ArrayList<String> wikiData;

    public WikipediaData(ArrayList<String> wikiData) {
        this.wikiData = wikiData;
    }

    public ArrayList<String> getWikiUrlAndDescription() {
        return wikiData;
    }

    public void setWikiUrlAndDescription(ArrayList<String> wikiData) {
        this.wikiData = wikiData;
    }

}
