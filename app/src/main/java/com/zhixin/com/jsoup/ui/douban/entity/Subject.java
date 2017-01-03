package com.zhixin.com.jsoup.ui.douban.entity;

/**
 * Created by zhangwenxing on 2016/11/10.
 */

public class Subject {
    private int id;//条目ID
    private String title;//中文名
    private String original_title;//原名
    private String alt;//条目URL
    private Images images;//电影海报图
    private String year;//年代
    private String subtype;//条目分类 movie photo等
    private Rating rating;//豆瓣评分

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

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
