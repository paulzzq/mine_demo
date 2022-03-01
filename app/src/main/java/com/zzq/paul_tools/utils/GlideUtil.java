package com.zzq.paul_tools.utils;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzq.paul_tools.R;

/**
 * Description:  Glide加载图片工具类
 * Author:       张丹枫
 * CreateDate:   2019/3/14 16:40
 */
public class GlideUtil {

    /**
     * 加载网络图片
     *
     * @param mContext  上下文
     * @param imgSource 图片来源
     * @param imageView imageView控件
     */
    public static <T> void loadImage(Context mContext, T imgSource, ImageView imageView) {
        loadImage(mContext, imgSource, imageView, R.drawable.pic_loading_default, R.drawable.pic_loading_default);
    }

    /**
     * 加载网络图片 设置加载中 加载失败的占位图
     *
     * @param mContext    上下文
     * @param imgSource   图片来源
     * @param imageView   显示图片组件
     * @param placeholder 加载中的占位图
     * @param error       加载失败显示的图片
     */
    public static <T> void loadImage(Context mContext, T imgSource, ImageView imageView, int placeholder, int error) {
//        Glide.with(mContext)
//                .load(imgSource)
//                .apply(new RequestOptions()
//                        //图片加载中显示的图片
//                        .placeholder(placeholder)
//                        //图片加载失败后显示的图片
//                        .error(error)
//                        //设置缓存
//                        .diskCacheStrategy(DiskCacheStrategy.ALL))
//                //设置给控件
//                .into(imageView);

        Glide.with(mContext).load(imgSource)
                .diskCacheStrategy( DiskCacheStrategy.ALL)
                .placeholder(placeholder)
                .into(imageView);
    }



    /**
     * 获取本地图片的宽高
     * @param path 图片本地路径
     */
    public static int[] getImageWH(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        return new int[]{options.outWidth, options.outHeight};
    }

}
