package com.secret.sdk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.secret.sdk.controller.param.*;
import com.secret.sdk.controller.response.BaseResult;
import com.secret.sdk.controller.response.SdkOrderCallback;
import com.secret.sdk.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Controller
@RequestMapping("/sdk")
@Api(value = "sdk 相关接口", tags = "sdkController", description = "sdk 相关接口")
public class SdkController {

    @Value("${sdk.domain}")
    private String SDK_DOMAIN;
    @Value("${sdk.secretKey}")
    private String SECRET_KEY;    //加密密钥，从secret开放平台获取
    @Value("${sdk.iv}")
    private String IV;    //加密偏移量，从secret开放平台获取


//    private String URL_TOKEN = SDK_DOMAIN + "/api/v1/sdk/oauth2/token";
//    private String URL_TOKEN_REFRESH = SDK_DOMAIN + "/api/v1/sdk/oauth2/token/refresh";
//    private String URL_USERINFO = SDK_DOMAIN + "/api/v1/sdk/userinfo";
//    private String URL_WITHDRAW = SDK_DOMAIN + "/api/v1/sdk/merchant/user/withdraw";

    @RequestMapping(value = "/oauth2/token", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult token(@Valid @RequestBody OAuthTokenParam req) throws Exception {

        String URL_TOKEN = SDK_DOMAIN + "/api/v1/sdk/oauth2/token";

        String encryptedBody = EncryptUtil.encrypt(SECRET_KEY, IV, JSON.toJSONString(req));

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("appId", req.getAppId());
        jsonParams.put("requestBody", encryptedBody);

        String result = HttpUtil.post(URL_TOKEN, jsonParams);
        log.info("token result:{}", result);

        BaseResult baseResult = JSON.parseObject(result, BaseResult.class);

        if (baseResult.isSuccess()) {
            JSONObject data = JSON.parseObject(EncryptUtil.decrypt(SECRET_KEY, IV, (String) baseResult.getData()));
            return BaseResult.success(data);
        }

        return baseResult;
    }

    @RequestMapping(value = "/oauth2/token/refresh", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult refreshToken(@Valid @RequestBody OAuthRefreshTokenParam req) throws Exception {
        String URL_TOKEN_REFRESH = SDK_DOMAIN + "/api/v1/sdk/oauth2/token/refresh";

        String encryptedBody = EncryptUtil.encrypt(SECRET_KEY, IV, JSON.toJSONString(req));

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("appId", req.getAppId());
        jsonParams.put("requestBody", encryptedBody);

        String result = HttpUtil.post(URL_TOKEN_REFRESH, jsonParams);
        log.info("refresh token result:{}", result);

        BaseResult baseResult = JSON.parseObject(result, BaseResult.class);

        if (baseResult.isSuccess()) {
            JSONObject data = JSON.parseObject(EncryptUtil.decrypt(SECRET_KEY, IV, (String) baseResult.getData()));
            return BaseResult.success(data);
        }

        return baseResult;
    }

    @RequestMapping(value = "/oauth2/userinfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult userInfo(@Valid @RequestBody UserInfoParam req) throws Exception {

        String URL_USERINFO = SDK_DOMAIN + "/api/v1/sdk/oauth2/userinfo";

        String encryptedBody = EncryptUtil.encrypt(SECRET_KEY, IV, JSON.toJSONString(req));

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("appId", req.getAppId());
        jsonParams.put("requestBody", encryptedBody);

        String result = HttpUtil.post(URL_USERINFO, jsonParams);
        log.info("userinfo result:{}", result);

        BaseResult baseResult = JSON.parseObject(result, BaseResult.class);

        if (baseResult.isSuccess()) {
            JSONObject data = JSON.parseObject(EncryptUtil.decrypt(SECRET_KEY, IV, (String) baseResult.getData()));
            return BaseResult.success(data);
        }

        return baseResult;
    }


    @RequestMapping(value = "/oauth2/qr/generate", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult qrGenerate(@Valid @RequestBody WebQRGenerateReq req) throws Exception {

        String URL_USERINFO = SDK_DOMAIN + "/api/v1/sdk/oauth2/qr/generate";

        String encryptedBody = EncryptUtil.encrypt(SECRET_KEY, IV, JSON.toJSONString(req));

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("appId", req.getAppId());
        jsonParams.put("requestBody", encryptedBody);

        String result = HttpUtil.post(URL_USERINFO, jsonParams);
        log.info("userinfo result:{}", result);

        BaseResult baseResult = JSON.parseObject(result, BaseResult.class);

        if (baseResult.isSuccess()) {
            JSONObject data = JSON.parseObject(EncryptUtil.decrypt(SECRET_KEY, IV, (String) baseResult.getData()));
            return BaseResult.success(data);
        }

        return baseResult;
    }

    @RequestMapping(value = "/oauth2/qr/code", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult qrCode(@Valid @RequestBody QRCodeReq req) throws Exception {

        String URL_USERINFO = SDK_DOMAIN + "/api/v1/sdk/oauth2/qr/code";

        String encryptedBody = EncryptUtil.encrypt(SECRET_KEY, IV, JSON.toJSONString(req));

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("appId", req.getAppId());
        jsonParams.put("requestBody", encryptedBody);

        String result = HttpUtil.post(URL_USERINFO, jsonParams);
        log.info("userinfo result:{}", result);

        BaseResult baseResult = JSON.parseObject(result, BaseResult.class);

        if (baseResult.isSuccess()) {
            JSONObject data = JSON.parseObject(EncryptUtil.decrypt(SECRET_KEY, IV, (String) baseResult.getData()));
            return BaseResult.success(data);
        }

        return baseResult;
    }

    @RequestMapping(value = "/merchant/user/withdraw", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult withdraw(@Valid @RequestBody UserWithdrawParam req) throws Exception {
        String URL_WITHDRAW = SDK_DOMAIN + "/api/v1/sdk/merchant/user/withdraw";
        String encryptedBody = EncryptUtil.encrypt(SECRET_KEY, IV, JSON.toJSONString(req));

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("appId", req.getAppId());
        jsonParams.put("requestBody", encryptedBody);

        String result = HttpUtil.post(URL_WITHDRAW, jsonParams);
        log.info("withdraw result:{}", result);

        BaseResult baseResult = JSON.parseObject(result, BaseResult.class);

        if (baseResult.isSuccess()) {
            JSONObject data = JSON.parseObject(EncryptUtil.decrypt(SECRET_KEY, IV, (String) baseResult.getData()));
            return BaseResult.success(data);
        }

        return baseResult;
    }

    @RequestMapping(value = "/order/place", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "统一下单", notes = "统一下单")
    public BaseResult orderPlace(@Valid @RequestBody SdkOrderPlaceReq req) throws Exception {
        String URL_WITHDRAW = SDK_DOMAIN + "/api/v1/sdk/order/place";
        if(StringUtils.isBlank(req.getOrderNo())){
            req.setOrderNo(UUIDTools.getUUID());
        }
        Map<String, String> params = JSON.parseObject(JSON.toJSONString(req), new TypeReference<Map<String, String>>() {
        });
        SignUtils.sdkOrderSign(params, SECRET_KEY);
        String encryptedBody = EncryptUtil.encrypt(SECRET_KEY, IV, JSON.toJSONString(params));
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("appId", req.getAppKey());
        jsonParams.put("requestBody", encryptedBody);

        String result = HttpUtil.post(URL_WITHDRAW, jsonParams);

        BaseResult baseResult = JSON.parseObject(result, BaseResult.class);

        if (baseResult.isSuccess()) {
            JSONObject data = JSON.parseObject(EncryptUtil.decrypt(SECRET_KEY, IV, (String) baseResult.getData()));
            return BaseResult.success(data);
        }

        return baseResult;
    }


    @RequestMapping(value = "/order/sign", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "订单签名", notes = "订单签名")
    public BaseResult orderSign(@Valid @RequestBody SdkOrderSignReq req) {
        String  secretKey=req.getSecretKey();
        req.setSecretKey(null);
        if(StringUtils.isBlank(req.getOrderNo())){
            req.setOrderNo(UUIDTools.getUUID());
        }
        Map<String, String> params = JSON.parseObject(JSON.toJSONString(req), new TypeReference<Map<String, String>>() {
        });
        return BaseResult.success(SignUtils.sdkOrderSign(params, secretKey));
    }

    @PostMapping(value = "/testCallback")
    @ResponseBody
    public HashMap testCallback(SdkOrderCallback req)  {
        log.info("fincy 回调内容:{}",req);

        HashMap<Object, Object> hashMap = new HashMap<>(1);
        hashMap.put("code","success");
        return hashMap;
    }
    @RequestMapping(value = "/order/orderDetail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "订单详情", notes = "订单详情")
    public BaseResult orderDetail(@Valid @RequestBody SdkOrderDetailReq req) throws Exception {
        String URL_WITHDRAW = SDK_DOMAIN + "/api/v1/sdk/merchant/orderDetail";

        String encryptedBody = EncryptUtil.encrypt(SECRET_KEY, IV, JSON.toJSONString(req));

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("appId", req.getAppId());
        jsonParams.put("requestBody", encryptedBody);

        String result = HttpUtil.post(URL_WITHDRAW, jsonParams);
        log.info("withdraw result:{}", result);

        BaseResult baseResult = JSON.parseObject(result, BaseResult.class);
        if (baseResult.isSuccess()) {
            JSONObject data = JSON.parseObject(EncryptUtil.decrypt(SECRET_KEY, IV, (String) baseResult.getData()));
            return BaseResult.success(data);
        }

        return baseResult;
    }

    @RequestMapping(value = "/order/refund", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "订单退款", notes = "订单退款")
    public BaseResult orderRefund(@Valid @RequestBody SdkOrderRefundReq req) throws Exception {
        String URL_WITHDRAW = SDK_DOMAIN + "/api/v1/sdk/order/refund";
        Map<String, String> params = JSON.parseObject(JSON.toJSONString(req), new TypeReference<Map<String, String>>() {
        });
        SignUtils.sdkOrderSign(params, SECRET_KEY);
        String encryptedBody = EncryptUtil.encrypt(SECRET_KEY, IV, JSON.toJSONString(params));
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("appId", req.getAppKey());
        jsonParams.put("requestBody", encryptedBody);

        String result = HttpUtil.post(URL_WITHDRAW, jsonParams);

        BaseResult baseResult = JSON.parseObject(result, BaseResult.class);

        if (baseResult.isSuccess()) {
            JSONObject data = JSON.parseObject(EncryptUtil.decrypt(SECRET_KEY, IV, (String) baseResult.getData()));
            return BaseResult.success(data);
        }

        return baseResult;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {

        System.out.println("-----------------------------");

        return "test/test";
    }


}
