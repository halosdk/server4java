package com.secret.sdk.controller.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Getter
@Setter
public class UserWithdrawParam {

    /**
     * 商户ID
     */
    @NotBlank(message = "merchantId 不能为空")
    private String merchantId;
    /**
     * 商户订单号
     */
    @NotBlank(message = "outTradeNo 不能为空")
    private String outTradeNo ;
    /**
     * 币种名称
     */
    @NotBlank(message = "coinName 不能为空")
    private String coinName;

    /**
     * 提币金额
     */
    @DecimalMin(value = "0.0001",message = "amount 必须大于0.0001")
    private BigDecimal amount;

    /**
     * 唯一标识
     */
    @NotBlank(message = "appId 不能为空")
    private String appId;

    /**
     * openid
     */
    @NotBlank(message = "openId 不能为空")
    private String openId;
    /**
     * 提币备注
     */
    @Length(max = 255,message = "remark 最大长度为255")
    private String remark;
    /**
     * 收款用户id
     */
    private String userId;
}