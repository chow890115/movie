package android.zhixin.com.jsoup.data;

/**
 * Created by zhangwenxing on 2016/11/10.
 */

public class Douban250SubjectsBean {
    private String id;//条目ID
    private String title;//中文名
    private String original_title;//原名
    private String alt;//条目URL
    private DoubanImages images;//电影海报图
    private String year;//年代
    private String subtype;//条目分类 movie photo等
    private DoubanRating rating;//豆瓣评分

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public DoubanImages getImages() {
        return images;
    }

    public void setImages(DoubanImages images) {
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

    public DoubanRating getRating() {
        return rating;
    }

    public void setRating(DoubanRating rating) {
        this.rating = rating;
    }
}
