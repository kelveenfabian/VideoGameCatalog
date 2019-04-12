package com.example.videogamecatalog.network;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IGDBApi {

    private static Retrofit retrofit;
    private static Map<Class, Object> serviceMap = new HashMap<>();

    private IGDBApi() {}

    private static Retrofit getInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("https://api-v3.igdb.com/")
                                             .client(httpClient)
                                             .addConverterFactory(GsonConverterFactory.create())
                                             .build();
        }
        return retrofit;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getService(Class<T> serviceClass) {
        T serviceInstance = (T) serviceMap.get(serviceClass);
        if (serviceInstance == null) {
            serviceInstance = getInstance().create(serviceClass);
            serviceMap.put(serviceClass, serviceInstance);
        }
        return serviceInstance;
    }
}
