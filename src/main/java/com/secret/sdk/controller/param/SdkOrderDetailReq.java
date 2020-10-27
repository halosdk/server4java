package com.secret.sdk.controller.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2020-03-10 16:18
 * @Description
 */
@Data
public class SdkOrderDetailReq {
    /**
     *
     */
    private String appId;
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商户ID
     */
    @NotBlank(message = "merchantId 不能为空")
    private String merchantId;
}
