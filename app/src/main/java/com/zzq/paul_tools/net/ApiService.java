package com.zzq.paul_tools.net;

import com.zzq.paul_tools.bean.Article;
import com.zzq.paul_tools.bean.BaseBean;
import com.zzq.paul_tools.bean.BaseListBean;
import com.zzq.paul_tools.bean.CountDetailBean;
import com.zzq.paul_tools.bean.HomeDataBean;
import com.zzq.paul_tools.bean.Poemer;
import com.zzq.paul_tools.bean.StoryDetailBean;
import com.zzq.paul_tools.constants.UrlConstants;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author zhuzaiqing
 * @describe 请求入参类
 * @time 2018/11/1 14:22
 */

public interface ApiService {
    /**
     * 查询小说
     */
    @FormUrlEncoded
    @POST(UrlConstants.QUERY_STORY)
    Flowable<BaseBean<List<String>>> getStoryLists(@Field("name") String name);


    /**
     * 查询统计
     *
     * @param name
     * @return
     */
    @FormUrlEncoded
    @POST(UrlConstants.QUERY_COUNT_BASE_URL)
    Flowable<BaseBean<List<CountDetailBean>>> getCounts(@Field("appKey") String name);


    @GET(UrlConstants.QUERY_STORY)
    Flowable<BaseBean<List<String>>> getPoemer(@Field("name") String name);


    @GET(UrlConstants.QUERY_STORY2)
    Flowable<BaseBean<List<StoryDetailBean>>> queryStory();


    @GET()
    Observable<BaseBean<HomeDataBean>> requestHomeData(@Url String url);

    @GET(UrlConstants.ARTICLE_URL)
    Observable<BaseListBean<Article>> getHistory(
            @Path("article") String url,
            @Query("index") int publishId,
            @Query("oper_type") int type);


    @GET(UrlConstants.DELETE_URL)
    Observable<BaseListBean<Article>> deleteQuery(
            @Query("phone_number") int publishId
    );


}
