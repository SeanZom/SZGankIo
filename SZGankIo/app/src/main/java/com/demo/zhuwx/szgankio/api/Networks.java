package com.demo.zhuwx.szgankio.api;

import com.demo.zhuwx.szgankio.SZGankIo;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class Networks {

    public static final String BASE_URL = "http://gank.io/api/";

    private static final long HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;
    private static final long CONNECT_TIMEOUT_SEC = 15;
    private static final long READ_TIMEOUT_SEC = 20;
    private static final long WRITE_TIMEOUT_SEC = 20;

    private static Retrofit sRetrofit;

    private static Networks sNetworks;

    public static Networks getInstance() {
        if (sNetworks == null) {
            sNetworks = new Networks();
        }

        return sNetworks;
    }

    public TopicApi getTopicApi() {
        return configRetrofit(TopicApi.class);
    }

    private <T> T configRetrofit(Class<T> service) {
        sRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return sRetrofit.create(service);

    }


    private OkHttpClient configClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SEC, TimeUnit.SECONDS);

        final File baseDir = SZGankIo.getAppContext().getCacheDir();

        if (baseDir != null) {
            final File cacheDir = new File(baseDir, "HttpResponseCache");
            builder.cache(new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE));
        }

        Interceptor logIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                ResponseBody responseBody = response.body();
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();
                Charset UTF8 = Charset.forName("UTF-8");
                Logger.d("=REQUEST URL=\n" + request.toString());
                Logger.json(buffer.clone().readString(UTF8));
                return null;
            }
        };

        builder.addNetworkInterceptor(logIntercept);

        return builder.build();
    }

}
