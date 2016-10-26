package com.guoyizeng.projecttemplate.network;

import com.guoyizeng.fastjsonconverter.FastJsonConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2016/7/29.
 */
public class BusinessService {


    private static final String BASE_URL = "";
    private static API mBusinessService;
    private BusinessService() {
    }

    public static API instance(){
        if (mBusinessService == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            mBusinessService = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//适配器
                    .client(okHttpClient)
                    .build()
                    .create(API.class);
        }

        return mBusinessService;
    }
}
