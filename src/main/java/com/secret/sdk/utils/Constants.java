
package com.secret.sdk.utils;

/**
 * @author Cherish
 * @date 2018年8月9日
 */
public interface Constants {
    String SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_CODE = "100004";
    String SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_MSG = "未获取到请求参数";
    String SYSERROR_SIGNATURE_INCORRECT_ERROR_CODE = "100005";
    String SYSERROR_SIGNATURE_INCORRECT_ERROR_MSG = "签名不正确";
    String SYSERROR_SIGNATURE_VALIDATE_ERROR_CODE = "100006";
    String SYSERROR_SIGNATURE_VALIDATE_ERROR_MSG = "校验签名失败";

    /**
     * SDK order 签名
     */
    //签名key，匹配相应的密钥
    String SDK_ORDER_SIGN__KEY = "appKey";
    //签名
    String SDK_ORDER_SIGN__VALUE = "orderSign";
}
