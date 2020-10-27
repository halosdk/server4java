package com.secret.sdk.controller.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

/**
 * @author Czh
 * Date: 2020-06-12 11:17
 */

@Setter
@Getter
public class SdkOrderRefundReq {

    /**
     * orderId
     */
    private String orderId;
    /**
     * thirdOrderNo
     */
    private String thirdOrderNo;

    private BigDecimal amount;

    @NotBlank(message = "appKey不能为空")
    private String appKey;
}