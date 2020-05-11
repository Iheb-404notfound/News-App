package com.iheb.newsapp;


import java.util.ArrayList;

public class Story {
    private String sectionName;
    private String webPublicationDate;
    private String webTitle;
    private String webUrl;
    private String pillarName;
    private ArrayList<String> Authors;

    public ArrayList<String> getAuthors() {
        return Authors;
    }

    public Story(String sectionName, String webPublicationDate, String webTitle, String webUrl, String pillarName, ArrayList<String> authors) {
        this.sectionName = sectionName;
        this.webPublicationDate = webPublicationDate;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.pillarName = pillarName;
        Authors = authors;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getPillarName() {
        return pillarName;
    }
}
