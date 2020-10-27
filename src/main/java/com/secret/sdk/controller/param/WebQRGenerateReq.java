package com.secret.sdk.controller.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Czh
 * Date: 2020/3/5 4:15 下午
 */
@Data
public class WebQRGenerateReq {
    /**
     * 应用唯一标识
     */
    @NotBlank(message = "appId不能为空")
    private String appId;
    /**
     * redirectUri
     */
    @NotBlank(message = "redirectUri不能为空")
    private String redirectUri;
}