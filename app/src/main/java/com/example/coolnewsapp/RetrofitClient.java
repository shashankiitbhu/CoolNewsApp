package com.example.coolnewsapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitClient {

    public class ApiClient{
        public static final String BASE_URL_NEWS = "https://newsapi.org/v2/";
        public static RetrofitClient apiNewsInterface;

        public static RetrofitClient getNewsApiInterface() {
            if (apiNewsInterface == null) {

                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                if (BuildConfig.DEBUG) {
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                } else {
                    interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
                }



                OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(180, TimeUnit.SECONDS);

//

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl(BASE_URL_NEWS)
                        .client(httpClient.build())
                        .build();

                apiNewsInterface = retrofit.create(RetrofitClient.class);
                return apiNewsInterface;
            } else {
                return apiNewsInterface;
            }
        }
    }
    @retrofit2.http.GET("top-headlines")
    Call<TotalHeadlines> getTopHeadlines(@Query("country") String country, @Query("apiKey") String key);

    @retrofit2.http.GET("top-headlines")
    Call<TotalHeadlines> getGlobalHeadlines(@Query("country") String country, @Query("apiKey") String key);

    @retrofit2.http.GET("top-headlines")
    Call<TotalHeadlines> getTechNews(@Query("q") String country, @Query("apiKey") String key);
}
