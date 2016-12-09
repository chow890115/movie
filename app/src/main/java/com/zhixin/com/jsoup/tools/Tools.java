package com.zhixin.com.jsoup.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 常用的一些验证、判断方法
 * Created by zhangwenxing on 16/9/6.
 */
public class Tools {

    public static String PATTERN = "^([\\u4e00-\\u9fa5]|\\w+)+$";
    public static String PATTERN_PWD = "^(\\w+)+$";
    public static String PATTERN_CHINESE = "([\\u4e00-\\u9fa5])+";
    public static String PATTERN_TEL = "^1[3|4|5|7|8]\\d{9}$";
    public static String ID_NUMBER_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    public static String ID_NUMBER_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
    public static String PATTERN_ENGLISH = "^[A-Za-z]+$";

    public static boolean checkChineseInString(String name) {
        Pattern p = Pattern.compile(PATTERN_CHINESE);
        Matcher matcher = p.matcher(name);
        return matcher.find();
    }

    public static boolean checkEnglishInString(String name) {
        Pattern p = Pattern.compile(PATTERN_ENGLISH);
        Matcher matcher = p.matcher(name);
        return matcher.find();
    }

    public static boolean checkValidTelNum(String tel) {
        Pattern p = Pattern.compile(PATTERN_TEL);
        Matcher m = p.matcher(tel);
        return m.matches();
    }

    /**
     * 字符串只留汉字
     *
     * @param str
     * @return
     */
    public static String getChinese(String str) {
        String string = "";
        int k = str.length();
        for (int i = 0; i < k; i++) {
            String l = str.substring(i, i + 1);
            if (checkChineseInString(l)) {
                string += l;
            }
        }
        return string;
    }


    /**
     * 字符串去符号
     *
     * @param str
     * @return
     */
    public static String checkNotSymbol(String str) {
        String string = "";
        int k = str.length();
        for (int i = 0; i < k; i++) {
            String l = str.substring(i, i + 1);
            if (checkChineseInString(l) || checkEnglishInString(l)) {
                string += l;
            }
        }
        return string;
    }

    /**
     * 身份证正则
     *
     * @param idNumber
     * @return
     */
    public static boolean checkValidTelNumIdNumber15(String idNumber) {
        if (idNumber.length() == 15) {
            Pattern p = Pattern.compile(ID_NUMBER_15);
            Matcher m = p.matcher(idNumber);
            return m.matches();
        } else {
            Pattern p = Pattern.compile(ID_NUMBER_18);
            Matcher m = p.matcher(idNumber);
            return m.matches();
        }
    }

    public static boolean stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }


    /**
     * 检测Sdcard是否存在
     *
     * @return
     */
    public static boolean isExitsSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    // 检查SD卡状态
    public static boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    // 获取SD卡路径
    public static String getExternalStoragePath() {
        // 获取SdCard状态
        String state = Environment.getExternalStorageState();
        // 判断SdCard是否存在并且是可用的
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Environment.getExternalStorageDirectory().canWrite()) {
                return Environment.getExternalStorageDirectory()
                        .getPath();
            }
        }
        return null;
    }

    public static boolean stackResumed(Context context) {
        ActivityManager manager = (ActivityManager) context
                .getApplicationContext().getSystemService(
                        Context.ACTIVITY_SERVICE);
        String packageName = context.getApplicationContext().getPackageName();
        List<ActivityManager.RunningTaskInfo> recentTaskInfos = manager.getRunningTasks(1);
        if (recentTaskInfos != null && recentTaskInfos.size() > 0) {
            ActivityManager.RunningTaskInfo taskInfo = recentTaskInfos.get(0);
            if (taskInfo.baseActivity.getPackageName().equals(packageName) && taskInfo.numActivities > 1) {
                return true;
            }
        }

        return false;
    }

    public static String checkUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return "http";
        }
        return url;
    }

    /**
     * 检测是否为最新版本
     */
    public static boolean isUpdateApk(Context mContext, int appVersion) {
        String currentAppVersion = "";
        int currentVersionCode = 0;
        PackageManager manager = mContext.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            currentAppVersion = info.versionName;//当前版本名
            currentVersionCode = info.versionCode;//当前版本号
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (currentVersionCode < appVersion) {//更新
            return true;
        } else {//不更新
            return false;
        }
    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 手机返回 True，平板返回 False
     */
    public static boolean isPhone(Context context) {
        return !((context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE);
    }

    /**
     * 获取设备唯一ID
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String id = tm.getDeviceId();
        return id;
    }


}
