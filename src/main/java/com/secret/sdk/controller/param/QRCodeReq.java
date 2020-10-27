package com.secret.sdk.controller.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Czh
 * Date: 2020/3/6 3:16 下午
 */
@Data
public class QRCodeReq {

    /**
     * appId
     */
    private String appId;

    /**
     * 二维码code
     */
    @NotBlank(message = "登录二维码不能为空")
    private String qrCode;
}