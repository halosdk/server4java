package com.secret.sdk.controller.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class PartnerTransferRecordReq {

    /**
     * appId
     */
    /**
     * 应用唯一标识
     */
    @NotBlank(message = "appId不能为空")
    private String appId;
    /**
     * 伙伴方的交易ID
     */
    private String id;
    /**
     * 伙伴方的本次充值唯一ID
     */
    private String transferRecordNo;

}
