package com.secret.sdk.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 签名工具
 *
 * @author zhiHua chen Created data on 2018-06-21.
 */
@Slf4j
public class SignUtils {
    /**
     * 密钥对，该部分代码后续应该抽取出来
     */
    private final static Map<String, String> signatureKeyPairs = new HashMap<String, String>();
    private final static String THUMBNAIL = "thumbnail";

    static {

        // IM ios
        signatureKeyPairs.put("GiI96RYjGulSnDa3dlrNLAOK8Z6YE4", "ndE2jdZNFixH9G6Aidsfyf7lYT3PxW");
        // IM 安卓
        signatureKeyPairs.put("2Nw1hvfS0ZZarlg9L57RPFDlIA3oXx", "9vRBWp63xIwlsc7vKKtvJTVKImNvrM");
        // IM 内部调用
        signatureKeyPairs.put("6XeLS1QPHUTQa7l8RXatTk0b7LOF0K", "y4sXSM4fJtd7R5lxnpN3IObMTLCJ01");
        // IM  mac
        signatureKeyPairs.put("tZP83JPXztu4iyCrP5sIO3RXEdBlOz", "LlueGQj95NhJPUIeZYclgKPSWL6loC");
        // IM win
        signatureKeyPairs.put("EkvSAMlJxUve6C2tM1sxNrdJqIJZR7", "LtqkCAhywWJ4hPPhh5d3sBa21MPHl3");
        // IM web
        signatureKeyPairs.put("DsDFSqweqweEWRdsgfsadfEWRHJ577", "CddasdfEFGDFG23123rffgdgRTREGG");


    }

    public static String getInternalServiceSignatureKey() {
        return "6XeLS1QPHUTQa7l8RXatTk0b7LOF0K";
    }

    private static String signBySecretKey(Map<String, Object> params, String secretKey) {
        Map<String, Object> sortedMap = new TreeMap<>(params);
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            Object objectValue = entry.getValue();
            if (objectValue instanceof List) {
                objectValue = String.valueOf(objectValue);
            }
            if (objectValue instanceof String) {
                String value = (String) objectValue;
                //如果字符串长度超过5000，截取前五千
                if (value.length() > 5000) {
                    value = value.substring(0, 5000);
                }
                buffer.append(entry.getKey()).append(value);
            } else {
                buffer.append(entry.getKey()).append(objectValue);
            }

        }
        String signature = Md5.encode(buffer.toString() + secretKey).toUpperCase();
        if (signature.length() >= 35) {
            return signature.substring(4, 34);
        } else {
            return signature.substring(4);
        }
    }




    /**
     * SDK Order调用签名校验
     *
     * @param expireTime 签名有效时间，单位为秒
     * @return
     * @throws IOException
     */
    public static boolean checkSdkOrderSign(Map<String, String> params, Integer expireTime, String appSecret) {
        try {
            // 参数不包含时间戳、签名key、签名 拒绝访问
            if (params == null
                    || StringUtils.isBlank(params.get(Constants.SDK_ORDER_SIGN__KEY))
                    || StringUtils.isBlank(params.get(Constants.SDK_ORDER_SIGN__VALUE))) {
                throw LxtxException.newException(Constants.SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_CODE, Constants.SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_MSG);
            }

//            // 当前有效的时间戳 expireTime秒内
//            if (expireTime == null) {
//                expireTime = 30;
//            }
//            long currentInvalidTimeMillis = System.currentTimeMillis() - 1000L * expireTime;
//            if (Long.valueOf(params.get(Constants.SDK_ORDER_SIGN__TIMESTAMP_KEY)) < currentInvalidTimeMillis) {
//                throw LxtxException.newException(Constants.SYSERROR_SIGNATURE_EXPIRED_ERROR_CODE, Constants.SYSERROR_SIGNATURE_EXPIRED_ERROR_MSG);
//            }

            // 获取密钥
            if (StringUtils.isBlank(appSecret)) {
                throw LxtxException.newException(Constants.SYSERROR_SIGNATURE_INCORRECT_ERROR_CODE, Constants.SYSERROR_SIGNATURE_INCORRECT_ERROR_MSG);
            }

            String originSign = params.get(Constants.SDK_ORDER_SIGN__VALUE);
            params.remove(Constants.SDK_ORDER_SIGN__VALUE);

            String newSign = signBySecretKey(new TreeMap<>(params), appSecret);
            if (!newSign.equals(originSign)) {
                throw LxtxException.newException(Constants.SYSERROR_SIGNATURE_INCORRECT_ERROR_CODE, Constants.SYSERROR_SIGNATURE_INCORRECT_ERROR_MSG);
            }
        } catch (LxtxException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw LxtxException.newException(Constants.SYSERROR_SIGNATURE_VALIDATE_ERROR_CODE, Constants.SYSERROR_SIGNATURE_VALIDATE_ERROR_MSG);
        }
        return true;
    }

    /**
     * SDK Order签名
     *
     * @param params
     * @return
     * @throws IOException
     */
    public static Map<String, String> sdkOrderSign(Map<String, String> params, String appSecret) {
        try {
            // 参数不包含时间戳、签名key、签名 拒绝访问
            if (params == null
                    || StringUtils.isBlank(params.get(Constants.SDK_ORDER_SIGN__KEY).toString())
            ) {
                throw LxtxException.newException(Constants.SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_CODE, Constants.SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_MSG);
            }

            // 获取密钥
            if (StringUtils.isBlank(appSecret)) {
                throw LxtxException.newException(Constants.SYSERROR_SIGNATURE_INCORRECT_ERROR_CODE, Constants.SYSERROR_SIGNATURE_INCORRECT_ERROR_MSG);
            }


            String newSign = signBySecretKey(new TreeMap<>(params), appSecret);
            params.put(Constants.SDK_ORDER_SIGN__VALUE, newSign);

        } catch (LxtxException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw LxtxException.newException(Constants.SYSERROR_SIGNATURE_VALIDATE_ERROR_CODE, Constants.SYSERROR_SIGNATURE_VALIDATE_ERROR_MSG);
        }
        return params;
    }


    public static void sdkOrderPlace() throws Exception {
        //请求参数签名加密
        Map<String, String> jsonParams = new HashMap<>();

        String appId = "78b2b2f46f3849969f150f103671fb1a";
        jsonParams.put("appKey", appId);
        jsonParams.put("orderNo", "9ab02bcd455b");
        jsonParams.put("coinName", "DC");
        jsonParams.put("amount", "1");
        jsonParams.put("expireTime", "7200");
        jsonParams.put("productDesc", "测试SDK支付");
        jsonParams.put("thirdPartyTimestamp", System.currentTimeMillis() + "");

        String appSecret = "Ub+09ORar/tOjjaxEJArrA==";
        String appSecretIv = "wpZ/3M0j81D1GwVsYrFuFQ==";

        Map<String, String> sdkOrderSignMaps = SignUtils.sdkOrderSign(jsonParams, appSecret);
        //签名结果
        String result = JSON.toJSONString(sdkOrderSignMaps);
        //打印
        String encrypt = Aes.encrypt(appSecret, result, appSecretIv);
        //打印
        System.out.println("签名结果\n" + "{\"requestBody\":\"" + encrypt + "\",\"appId\":\"" + appId + "\"}");

        //返回结果解密
        String decrypt = Aes.decrypt(appSecret,

                //填写返回结果data里的长字符串
                "N1e4UiKHpAJcHR//eXJUDV/MG+7huZTJ7Dv6t66WIIh7qgoSYJnmFjdhgsvcXDi+8jVW/qYin+Kqwaw8jPjR4n46yB49JJdlgjML9sr/sMvXwU44U66W//nC1lfO1lNGMWOIkrwqP2zAKpoPXX4jSg==",

                appSecretIv);

        System.out.println("返回结果解密\n" + decrypt);
    }
}
