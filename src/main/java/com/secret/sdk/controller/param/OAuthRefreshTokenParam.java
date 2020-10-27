package com.secret.sdk.controller.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


@Setter
@Getter
public class OAuthRefreshTokenParam {

    /**
     * 应用唯一标识
     */
    @NotBlank(message = "appId不能为空")
    private String appId;
    /**
     * refreshToken
     */
    @NotBlank(message = "refreshToken不能为空")
    private String refreshToken;
    /**
     * 填写为authorizationCode
     */
    @NotBlank(message = "grantType不能为空")
    private String grantType;

}