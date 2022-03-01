package com.zzq.paul_tools.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zzq.paul_tools.MyApplication;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @Description: 常用工具类
 * @Author: zzq
 * @Date: 2020-09-11 21:12
 */
public class AppUtil {
//    public static final String REG_SYMBOL ="^(?=.*[a-zA-Z0-9].*)(?=.*[a-zA-Z\\\\W].*)(?=.*[0-9\\\\W].*).{8,16}$";

    //数字
    public static final String REG_NUMBER = ".*\\d+.*";
    //小写字母
    public static final String REG_UPPERCASE = ".*[A-Z]+.*";
    //大写字母
    public static final String REG_LOWERCASE = ".*[a-z]+.*";
    //特殊符号
    public static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";

    public static boolean checkPasswordRule(String password) {
        //密码为空或者长度小于8位则返回false
        if (password == null || password.length() < 8) return false;
        int i = 0;
        if (password.matches(REG_NUMBER)) i++;
        if (password.matches(REG_LOWERCASE) || password.matches(REG_UPPERCASE)) i++;
        if (password.matches(REG_SYMBOL)) i++;
        if (i < 2) return false;

        return true;
    }

    /**
     * 是否登录
     *
     * @return true.已登录  false.未登录
     */
    public static boolean isLogin() {
//        String userId = SharedPrefs.getInstance().getUserId();
//        if (TextUtils.isEmpty(userId)) {
//            return false;
//        } else {
        return true;
//        }
    }


    /**
     * 判断list是否是空的
     *
     * @return true：空  false：不为空
     */
    public static boolean isEmptyList(List list) {
        if (null == list || list.size() == 0) {
            return true;
        }
        return false;
    }


    private static long lastClickTime;

    public static boolean isFastRequestClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 3000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 防止用户连续点击
     *
     * @return true:连续点击 false：单次点击
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 校验手机
     *
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPhone(String mobiles) {
        String regExp = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 获取版本名称
     *
     * @param ctx
     * @return
     */
    public static String getVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);

            localVersion = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取APP版本号
     *
     * @param ctx
     * @return
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);

            localVersion = resolverVersionInt(packageInfo.versionName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    private static int resolverVersionInt(String versionName) {
        int version = 0;
        if (TextUtils.isEmpty(versionName)) {
            return version;
        }
        String[] versionStr = versionName.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String s : versionStr) {
            sb.append(s);
        }
        version = Integer.parseInt(sb.toString());
        return version;
    }

    /**
     * 数字小写转大写 “1”--“一”
     */
    public static String numberToCapital(int number) {
        if (number > 12) {
            return "";
        }
        String[] nums = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
        return nums[number];
    }

    /**
     * 通过反射设置RecyclerView滑动速度
     *
     * @param velocity 速度值 RecyclerView默认是8000
     */
    public static void setMaxFlingVelocity(RecyclerView recyclerView, int velocity) {
        try {
            Field field = recyclerView.getClass().getDeclaredField("mMaxFlingVelocity");
            field.setAccessible(true);
            field.set(recyclerView, velocity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断字符串是否是json格式
     *
     * @param jsonStr
     * @return
     */
    public static <T> boolean isJson(String jsonStr, @NonNull Class<T> service) {
        try {
            new Gson().fromJson(jsonStr, service);
            return true;
        } catch (JsonSyntaxException ex) {
            return false;
        }
    }

    /**
     * 判断字符串是否是json格式
     *
     * @param jsonStr
     * @return
     */
    public static boolean isJson(String jsonStr, Type typeOfT) {
        try {
            new Gson().fromJson(jsonStr, typeOfT);
            return true;
        } catch (JsonSyntaxException ex) {
            return false;
        }
    }

    /**
     * 获取手机IMEI
     *
     * @param context
     * @return
     */
    public static final String getIMEI(Context context) {
        try {
            //实例化TelephonyManager对象
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMEI号
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return "";
            }
            String imei = telephonyManager.getDeviceId();
            //在次做个验证，也不是什么时候都能获取到的啊
            if (imei == null) {
                imei = "";
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getSerialNumber(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                return "";
            }
            try {
                return Build.getSerial();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Build.SERIAL;
    }

}
