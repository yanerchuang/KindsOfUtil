package com.ywj.util;

import com.google.gson.Gson;

/**
 * author: ywj
 * created on: 2018/11/1 11:17
 * description:
 */
public class GsonUtil {
    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
