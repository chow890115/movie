package com.zhixin.com.jsoup.tools;

import android.content.SharedPreferences;

import com.zhixin.com.jsoup.base.application.BaseApplication;

/**
 * Created by zhangstar on 2016/11/16.
 */

public class SharedPreferencesUtil {
    public static String SP_NAME = "zhixin";
    private static SharedPreferences sp;

    public static void saveBoolean(String key, boolean value) {
        if (sp == null)
            sp = BaseApplication.getInstance().getSharedPreferences(SP_NAME, 0);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        if (sp == null) {
            sp = BaseApplication.getInstance().getSharedPreferences(SP_NAME, 0);

        }
        return sp.getBoolean(key, defValue);
    }

    public static void saveString(String key, String value) {
        if (sp == null)
            sp = BaseApplication.getInstance().getSharedPreferences(SP_NAME, 0);
        sp.edit().putString(key, value).apply();
    }

    public static void saveInt(String key, int value) {
        if (sp == null)
            sp = BaseApplication.getInstance().getSharedPreferences(SP_NAME, 0);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(String key, int value) {
        if (sp == null) {
            sp = BaseApplication.getInstance().getSharedPreferences(SP_NAME, 0);

        }
        return sp.getInt(key, value);
    }

    public static String getString(String key, String defValue) {
        if (sp == null) {
            sp = BaseApplication.getInstance().getSharedPreferences(SP_NAME, 0);

        }
        return sp.getString(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        if (sp == null) {
            sp = BaseApplication.getInstance().getSharedPreferences(SP_NAME, 0);

        }
        return sp.getLong(key, defValue);
    }

    public static void saveLong(String key, long defValue) {
        if (sp == null)
            sp = BaseApplication.getInstance().getSharedPreferences(SP_NAME, 0);
        sp.edit().putLong(key, defValue).commit();
    }
}
