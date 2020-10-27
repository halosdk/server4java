package com.secret.sdk.controller.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Setter
@Getter
public class UserInfoParam {

    /**
     * accessToken
     */
    @NotNull(message = "accessToken不能为空")
    private String accessToken;
    /**
     * openid
     */
    @NotNull(message = "openId不能为空")
    private String openId;

    /**
     * appId
     */
    @NotNull(message = "appId不能为空")
    private String appId;
}