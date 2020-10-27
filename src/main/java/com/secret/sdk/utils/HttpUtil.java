package com.secret.sdk.utils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
public class HttpUtil {

    private static final int HTTP_SUCCESS_CODE = 200;
    private static final Integer CONNECT_TIMEOUT = 60;
    private static final Integer READ_TIMEOUT = 60;

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build();

    public static String post(String url, Map<String, Object> params) throws Exception {
        String s = JSON.toJSONString(params);
        log.info("请求参数：{}",s);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , s);
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String result = response.body().string();
        log.info("响应参数：{}",result);
        if (HTTP_SUCCESS_CODE != response.code()) {
            throw new Exception("调用secret SDK失败");
        }


        response.close();
        return result;
    }



    public static String post(String url, Map<String, Object> params,String token) throws Exception {
        String s = JSON.toJSONString(params);


        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , JSON.toJSONString(params));
        Request request = new Request.Builder()
                .post(requestBody)
                .addHeader("access-token", token)
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String result = response.body().string();
        if (HTTP_SUCCESS_CODE != response.code()) {
            throw new Exception("调用secret SDK失败");
        }


        response.close();
        return result;
    }



}
