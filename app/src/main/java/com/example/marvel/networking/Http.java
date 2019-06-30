package com.example.marvel.networking;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Http {
    static Retrofit retrofit;

    public static void initialize() {
        String bUrl = "https://gateway.marvel.com:443/v1/public/";
        retrofit = new Retrofit.Builder()
                .baseUrl(bUrl)
                .client(getHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static <T> T create(Class<T> object) {
        if (retrofit == null)
            initialize();
        return retrofit.create(object);
    }

    private static final String TAG = "Http";
    public static String PUBLIC_KEY = "ec1c96ead8543bb4e0e56ab89d3c55a0";
    private static String PRIVATE_KEY = "9e5126a6ad8dbcf8baa76ad11eab0fec13af9f3b";

    private static OkHttpClient getHeader() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // add interceptor to add queries with each request

        OkHttpClient okClient = new OkHttpClient.Builder().addInterceptor(logging)
                .addNetworkInterceptor(
                        chain -> {
                            Request request = chain.request();
                            String ts = Long.toString(System.currentTimeMillis() / 1000);
                            HttpUrl url = request.url().newBuilder()
                                    .addQueryParameter("apikey", PUBLIC_KEY)
                                    .addQueryParameter("ts", ts)
                                    .addQueryParameter("hash", md5(ts + PRIVATE_KEY + PUBLIC_KEY))
                                    .build();
                            request = request.newBuilder().url(url).build();

                            return chain.proceed(request);
                        })
                .build();
        return okClient;
    }


    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            String hash = new String(Strings.hexEncode(digest.digest()));
            return hash;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    } // function for get hash value
}
