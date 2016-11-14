package com.zhixin.com.jsoup.data;

/**
 * Created by zhangstar on 2016/11/14.
 */

public class MovieDetailBean {
    private int id;//条目ID
    private String title;//中文名
    private String original_title;//原名（英文名）
    private String aka;//又名
    private String mobile_url;//移动版条目页
    private DoubanRating rating;//评分
    private int ratings_count;//评分人数
    private int wish_count;//想看人数
    private int collect_count;//看过人数
    private DoubanImages images;
    private Celebrity casts;//影人信息 主演信息
    private String year;//年代
    private String[] genres;//影片类型

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

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public DoubanRating getRating() {
        return rating;
    }

    public void setRating(DoubanRating rating) {
        this.rating = rating;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public DoubanImages getImages() {
        return images;
    }

    public void setImages(DoubanImages images) {
        this.images = images;
    }

    public Celebrity getCasts() {
        return casts;
    }

    public void setCasts(Celebrity casts) {
        this.casts = casts;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    class Celebrity {
        private int id;//影人条目ID
        private String name;//中文名
        private String alt;//影人条目URL
        private DoubanImages avatars;//影人图片

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public DoubanImages getAvatars() {
            return avatars;
        }

        public void setAvatars(DoubanImages avatars) {
            this.avatars = avatars;
        }
    }
}