package com.secret.sdk.controller.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PartnerUserRechargeReq {

	/**
	 * 应用唯一标识
	 */
	@NotBlank(message = "appId不能为空")
	private String appId;
	/**
	 * 伙伴方的本次充值唯一ID
	 */
	@NotBlank(message = "伙伴方交易唯一标识不能为空")
	private String transferRecordNo;
	/**
	 * 充值到账用户ID
	 */
	@NotBlank(message = "充值到账用户不能为空")
	private String gotUserId;
	/**
	 * 币种
	 */
	@NotBlank(message = "币种不能为空")
	private String coinName;
	/**
	 * 充值金额
	 */
	@DecimalMin(value = "0.00000001", message = "充值金额必须大于等于0.00000001")
	@NotNull(message = "充值金额amount不能为空")
	private BigDecimal amount;
	/**
	 * 手续费
	 */
	@DecimalMin(value = "0", message = "手续费金额必须大于等于0")
	@NotNull(message = "手续费fee不能为空")
	private BigDecimal fee;
	
}
