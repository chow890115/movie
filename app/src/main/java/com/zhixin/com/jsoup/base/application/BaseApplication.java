package com.zhixin.com.jsoup.base.application;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwenxing on 2016/11/10.
 */

public class BaseApplication extends Application {
    public static BaseApplication instance;
    private List<Activity> activitys = new ArrayList<Activity>();


    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activitys != null) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        }
    }

    // 遍历所有Activity并finish
    public void exit() {
        if (activitys != null && activitys.size() > 0) {
            for (Activity activity : activitys) {
                activity.finish();
            }
        }
    }
}
