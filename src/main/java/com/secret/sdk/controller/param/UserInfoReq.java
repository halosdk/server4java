package com.secret.sdk.controller.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Czh
 * @Date: 2020/7/3 11:16 上午
 */
@Data
public class UserInfoReq {
    /**
     * 应用唯一标识
     */
    @NotBlank(message = "appId不能为空")
    private String appId;
    /**
     * 平台用户手机号的国际简码
     */
    @NotBlank(message = "用户手机号的国际简码不能为空")
    private String countryCode;
    /**
     * 平台用户手机号
     */
    @NotBlank(message = "用户手机号不能为空")
    private String telephone;
}
