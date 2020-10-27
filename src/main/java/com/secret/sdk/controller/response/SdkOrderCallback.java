package com.secret.sdk.controller.response;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2020-03-10 11:49
 * @Description
 */
@Data
public class SdkOrderCallback {

    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 交易类型【1：提现、2：充值、3：支付、4：退款】
     */
    private Integer transfeType;
    /**
     * 币种
     */
    private String coinName;
    /**
     * 交易金额
     */
    private String transfeAmout;

    private String msg;
    private String code;
}
