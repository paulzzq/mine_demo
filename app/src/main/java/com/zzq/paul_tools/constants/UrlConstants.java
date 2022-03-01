package com.zzq.paul_tools.constants;


/**
 * @author zhuzaiqing
 * @describe 常量类
 * @time 2018/11/1 13:46
 */
public class UrlConstants {
    //查询小说接口
//    public static final String BASE_URL="https://www.apiopen.top/";
    public static final String BASE_URL="https://api.xzytest.cn/";
//    public static final String BASE_URL="http://192.168.2.46:4438/";
//    public static final String BASE_URL= BuildConfig.BASE_URL;

//    https://www.apiopen.top/findStatistics?appKey=00d91e8e0cca2b76f515926a36db68f5




    //查询小说
    public static  final  String QUERY_STORY="novelSearchApi?";

    public static  final  String QUERY_STORY2="novelApi";
    //查询统计
    public static final String QUERY_COUNT_BASE_URL="findStatistics?";

    //查询诗人
    public static final String QUERY_POERMER_URL="singlePoetry?";


    /**
     * 历史文章
     */
    public static final String ARTICLE_URL = "v1/{article}";

    public static final String DELETE_URL = "/api/del_cache";
    /**
     * 文章
     */
    public static final String ARTICLE_DETAIL_URL = "v1/{article}/{id}";


//    public static  final  String QUERY_STORY2="parent/message/v1/count?\n";
}
