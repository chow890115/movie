package com.zhixin.com.jsoup.data;

/**
 * Created by zhangwenxing on 2016/11/10.
 */

/**
 * 豆瓣评分Bean
 */
public class DoubanRating {
private int max;//最高评分
    private int min;//最低评分
    private float average;//评分
    private int stars;//评星数

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
