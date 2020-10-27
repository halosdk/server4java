package com.secret.sdk.controller.param;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Czh
 * Date: 2019-03-29 14:41
 */
@Getter
@Setter
@NoArgsConstructor
public class SdkOrderPlaceReq {
    /**
     * 应用key
     */
    @NotBlank(message = "应用appKey不能为空")
    @Length(max = 32, message = "应用appKey最大长度为32")
    private String appKey;
    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
     */
    @Length(max = 32, message = "商户订单号orderNo最大长度为32")
    private String orderNo;
    /**
     * 币种名称
     */
    @NotBlank(message = "币种名称coinName不能为空")
    private String coinName;
    /**
     * 金额
     */
    @DecimalMin(value = "0.00000001", message = "转账金额必须大于0.00000001")
    private BigDecimal amount;
    /**
     * 交易结束时间 单位分钟
     */
    @NotNull(message = "交易时间expireTime不能为空")
    @Min(value = 30, message = "交易时间expireTime必须大于等于30分钟")
    private Integer expireTime;
    /**
     * 支付备注
     */
    @Length(max = 255, message = "支付备注thirdPartyRemarks最大长度为255")
    private String thirdPartyRemarks;
    /**
     * 商品说明
     */
    @NotBlank(message = "商品说明productDesc不能为空")
    @Length(max = 128, message = "商品说明productDesc最大长度为128")
    private String productDesc;
    /**
     * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    @Length(max = 128, message = "附加数据attach最大长度为128")
    private String attach;
    /**
     * 时间戳
     */
//    @NotNull(message = "时间戳thirdPartyTimestamp不能为空")
    private Long thirdPartyTimestamp;

    /**
     * 支付场景 1：APP 2: H5 3:NATIVE
     */
    @NotNull(message = "payScenes 不能为空")
    private Integer payScenes;
}