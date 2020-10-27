package com.secret.sdk.controller.param;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OAuthTokenParam {

	/**
	 * 应用唯一标识
	 */
	@NotBlank(message = "appId不能为空")
	private String appId;
	/**
	 * 填写第一步获取的code参数
	 */
	@NotBlank(message = "code不能为空")
	private String code;
	/**
	 * 填写为authorizationCode
	 */
	@NotBlank(message = "grantType不能为空")
	private String grantType;

}