package com.secret.sdk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.secret.sdk.controller.response.BaseResult;
import com.secret.sdk.utils.EncryptUtil;
import com.secret.sdk.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Czh
 * @Date: 2020/8/3 4:31 下午
 */
@Slf4j
public abstract class BaseController {

    protected BaseResult httpRequest(String appId, String encryptedBody, String requestUrl) throws Exception {
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("appId", appId);
        jsonParams.put("requestBody", encryptedBody);

        String result = HttpUtil.post(requestUrl, jsonParams);
        log.info("token result:{}", result);
        BaseResult baseResult = JSON.parseObject(result, BaseResult.class);
        if (baseResult.isSuccess()) {
            JSONObject data = JSON.parseObject(EncryptUtil.decrypt(getSecretKey(), getIv(), (String) baseResult.getData()));
            return BaseResult.success(data);
        }
        return baseResult;
    }

    protected abstract String getSecretKey();

    protected abstract String getIv();
}
