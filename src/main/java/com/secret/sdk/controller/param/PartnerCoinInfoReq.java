package com.secret.sdk.controller.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class PartnerCoinInfoReq {

	/**
	 * appId
	 */
	/**
	 * 应用唯一标识
	 */
	@NotBlank(message = "appId不能为空")
	private String appId;
	/**
	 * coinIds,查询多个用英文逗号隔开，null返回所有
	 */
	private String coinIds;
	
}
