package com.zhixin.com.jsoup.data;

import java.util.List;

/**
 * Created by zhangwenxing on 2016/11/10.
 */

public class Douban250Bean {
    private int count;//一次请求多少条
    private int start;//第几条开始
    private int total;
    private String titile;
    private List<Douban250SubjectsBean> subjects;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public List<Douban250SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Douban250SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public int getCount() {

        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
