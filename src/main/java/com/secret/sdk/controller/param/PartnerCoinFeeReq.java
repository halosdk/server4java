package com.secret.sdk.controller.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
public class PartnerCoinFeeReq {
	/**
	 * 应用唯一标识
	 */
	@NotBlank(message = "appId不能为空")
	private String appId;
	/**
	 * 币种
	 */
	@NotBlank(message = "币种不能为空")
	private String coinName;
	
	/**
	 * 手续费计算金额
	 */
	@DecimalMin(value = "0.00000001", message = "充值金额必须大于0.00000001")
	private BigDecimal amount;

}
