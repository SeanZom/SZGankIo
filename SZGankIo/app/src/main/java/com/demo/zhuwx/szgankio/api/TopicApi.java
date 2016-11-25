package com.demo.zhuwx.szgankio.api;

import com.demo.zhuwx.szgankio.api.utils.HttpResult;
import com.demo.zhuwx.szgankio.data.TopicEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public interface TopicApi {

    @GET("data/{topic}/20/{page}")
    Observable<HttpResult<List<TopicEntity>>> getTopics(@Path("topic") String topic, @Path("page") int page);

}
