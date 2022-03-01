package com.zzq.paul_tools.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.internal.Utils;

/**
 * crash utils
 */
public final class CrashUtils {

    private static String defaultDir;
    private static String dir;

    private static final String FILE_SEP = System.getProperty("file.separator");
    @SuppressLint("SimpleDateFormat")
    private static final Format FORMAT   = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");


    private static OnCrashListener sOnCrashListener;





    public static String getHttpHead(String url, int httpcode, int cityCode) {
        return "************* HTTP Head ****************" +
                    "\nURL               : " + url +
                    "\nHTTP CODE         : " + httpcode +
                    "\nCITY CODE         : " + cityCode +
                    "\n************* HTTP Head ****************\n\n";
    }

    private CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static File getDirPath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && context.getExternalCacheDir() != null)
            return context.getExternalCacheDir();
        else {
            return context.getCacheDir();
        }
    }

    /**
     * 上传日志
     */
    public static void uploadLog() {
//        UpgradePresenter presenter = new UpgradePresenter();
//        presenter.uploadLogFile(1, defaultDir);
//        presenter.uploadLogFile(2, ActivityLifecycleUtils.getApp().getExternalCacheDir() + FILE_SEP + "log" + FILE_SEP);
    }

    public static void input2File(final String input, final String filePath) {
        if (!createOrExistsFile(filePath)) {
            return;
        }
        Future<Boolean> submit = Executors.newSingleThreadExecutor().submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter(filePath, true));
                    bw.write(input);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
                        if (bw != null) {
                            bw.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        try {
            if (submit.get()) return;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static boolean createOrExistsFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public interface OnCrashListener {
        void onCrash(String crashInfo, Throwable e);
    }
}