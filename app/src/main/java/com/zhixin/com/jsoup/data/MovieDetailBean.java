package com.zhixin.com.jsoup.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangstar on 2016/11/14.
 */

public class MovieDetailBean implements Serializable {

    /**
     * rating : {"max":10,"average":9.4,"stars":"50","min":0}
     * reviews_count : 2954
     * wish_count : 55035
     * douban_site :
     * year : 1994
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p511118051.jpg","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p511118051.jpg","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p511118051.jpg"}
     * alt : https://movie.douban.com/subject/1295644/
     * id : 1295644
     * mobile_url : https://movie.douban.com/subject/1295644/mobile
     * title : 这个杀手不太冷
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/1295644
     * seasons_count : null
     * schedule_url :
     * episodes_count : null
     * countries : ["法国"]
     * genres : ["剧情","动作","犯罪"]
     * collect_count : 948832
     * casts : [{"alt":"https://movie.douban.com/celebrity/1025182/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/8833.jpg","large":"https://img3.doubanio.com/img/celebrity/large/8833.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/8833.jpg"},"name":"让·雷诺","id":"1025182"},{"alt":"https://movie.douban.com/celebrity/1054454/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/2274.jpg","large":"https://img3.doubanio.com/img/celebrity/large/2274.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/2274.jpg"},"name":"娜塔莉·波特曼","id":"1054454"},{"alt":"https://movie.douban.com/celebrity/1010507/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/104.jpg","large":"https://img3.doubanio.com/img/celebrity/large/104.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/104.jpg"},"name":"加里·奥德曼","id":"1010507"},{"alt":"https://movie.douban.com/celebrity/1019050/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/23874.jpg","large":"https://img3.doubanio.com/img/celebrity/large/23874.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/23874.jpg"},"name":"丹尼·爱罗","id":"1019050"}]
     * current_season : null
     * original_title : Léon
     * summary : 里昂（让·雷诺饰）是名孤独的职业杀手，受人雇佣。一天，邻居家小姑娘马蒂尔达（纳塔丽·波特曼饰)敲开他的房门，要求在他那里暂避杀身之祸。原来邻居家的主人是警方缉毒组的眼线，只因贪污了一小包毒品而遭恶警（加里·奥德曼饰）杀害全家的惩罚。马蒂尔达得到里昂的留救，幸免于难，并留在里昂那里。里昂教小女孩使枪，她教里昂法文，两人关系日趋亲密，相处融洽。
     * 女孩想着去报仇，反倒被抓，里昂及时赶到，将女孩救回。混杂着哀怨情仇的正邪之战渐次升级，更大的冲突在所难免……©豆瓣
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1031876/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/33301.jpg","large":"https://img3.doubanio.com/img/celebrity/large/33301.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/33301.jpg"},"name":"吕克·贝松","id":"1031876"}]
     * comments_count : 162346
     * ratings_count : 714817
     * aka : ["杀手莱昂","终极追杀令(台)","杀手里昂","Leon","Leon: The Professional"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean implements Serializable {
        /**
         * max : 10
         * average : 9.4
         * stars : 50
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p511118051.jpg
         * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p511118051.jpg
         * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p511118051.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean implements Serializable {
        /**
         * alt : https://movie.douban.com/celebrity/1025182/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/8833.jpg","large":"https://img3.doubanio.com/img/celebrity/large/8833.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/8833.jpg"}
         * name : 让·雷诺
         * id : 1025182
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean implements Serializable {
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/8833.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/8833.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/8833.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1031876/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/33301.jpg","large":"https://img3.doubanio.com/img/celebrity/large/33301.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/33301.jpg"}
         * name : 吕克·贝松
         * id : 1031876
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/33301.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/33301.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/33301.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}