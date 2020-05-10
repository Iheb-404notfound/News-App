package com.iheb.newsapp;


public class Story {
    private String sectionName;
    private String webPublicationDate;
    private String webTitle;
    private String webUrl;
    private String pillarName;

    public Story(String sectionName, String webPublicationDate, String webTitle, String webUrl, String pillarName) {
        this.sectionName = sectionName;
        this.webPublicationDate = webPublicationDate;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.pillarName = pillarName;
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
