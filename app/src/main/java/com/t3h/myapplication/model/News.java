package com.t3h.myapplication.model;

public class News {
    private String title;
    private String avatar;
    private String description;
    private String pubDate;
    private String detailLink;

    public News() {
    }

    public News(String title, String avatar, String description, String pubDate, String detailLink) {
        this.title = title;
        this.avatar = avatar;
        this.description = description;
        this.pubDate = pubDate;
        this.detailLink = detailLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }
}
