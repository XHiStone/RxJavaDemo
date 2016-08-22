package com.isoftstone.rxjavademo.app;

import android.content.Context;
import android.util.Log;

import com.isoftstone.rxjavademo.utils.CacheManager;
import com.isoftstone.rxjavademo.http.HttpManager;
import com.isoftstone.rxjavademo.utils.TokenUtil;

import java.io.File;

import okhttp3.Cache;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.app
 *
 * @Author: xie
 * @Time: 2016/8/18 10:16
 * @Description:
 */

public class SingleBeans {
    private static SingleBeans singleBeans = new SingleBeans();
    private static Cache cache;
    private static HttpManager httpManager;
    private static CacheManager cacheManager;
    private static TokenUtil tokenUtil;

    public static SingleBeans getSingleBeans(Context context) {

        if (cache == null) {
            File cacheFile = new File(context.getCacheDir(), Constants.HTTP_CACHFILENAME);
            cache = new Cache(cacheFile, Constants.HTTP_CACHSIZE);
        }

        if (httpManager == null) {
            httpManager = HttpManager.getInstance();
        }

        if (cacheManager == null) {
            cacheManager = CacheManager.get(context.getExternalCacheDir().getAbsolutePath() +
                    File.separator + "cache");
        }

        if (tokenUtil == null) {
            Log.i("tag", "---------");
            tokenUtil = tokenUtil.getInstance();
            tokenUtil.getTocken(context);
        }
        return singleBeans;
    }

    public static Cache getCache() {
        return cache;
    }

    public static HttpManager getHttpManager() {
        return httpManager;
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    public static TokenUtil getTokenUtil() {
        return tokenUtil;
    }
}
