package com.secret.sdk.controller;

import com.alibaba.fastjson.JSON;
import com.secret.sdk.controller.param.*;
import com.secret.sdk.controller.response.BaseResult;
import com.secret.sdk.utils.EncryptUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Czh
 * @Date: 2020/8/3 11:30 上午
 */


@Slf4j
@Component
@RestController
@RequestMapping("/partner")
@Api(value = "partner 相关接口", tags = "partnerController", description = "partner 相关接口")
public class PartnerController extends BaseController {

    @Value("${partner.domain}")
    private String partnerDomain;
    /**
     * 加密密钥，从secret开放平台获取
     */
    @Value("${partner.secretKey}")
    private String secretKey;
    /**
     * 加密偏移量，从secret开放平台获取
     */
    @Value("${partner.iv}")
    private String iv;

    @Override
    protected String getSecretKey() {
        return secretKey;
    }

    @Override
    protected String getIv() {
        return iv;
    }

    /**
     * 功能描述: 伙伴API-手续费计算
     *
     * @param req
     * @return com.secret.sdk.controller.response.BaseResult
     * @author Czh
     * @date 2020/8/3 4:41 下午
     */
    @RequestMapping(value = "/coin/fee", method = RequestMethod.POST)
    public BaseResult coinFee(@Valid @RequestBody PartnerCoinFeeReq req) throws Exception {
        String requestUrl = partnerDomain + "/api/v1/partner/coin/fee";
        String encryptedBody = EncryptUtil.encrypt(secretKey, iv, JSON.toJSONString(req));
        return this.httpRequest(req.getAppId(), encryptedBody, requestUrl);
    }

    /**
     * 功能描述: 伙伴API-用户充值
     *
     * @param req
     * @return com.secret.sdk.controller.response.BaseResult
     * @author Czh
     * @date 2020/8/3 4:41 下午
     */
    @RequestMapping(value = "/user/recharge", method = RequestMethod.POST)
    public BaseResult userRecharge(@Valid @RequestBody PartnerUserRechargeReq req) throws Exception {
        String requestUrl = partnerDomain + "/api/v1/partner/user/recharge";
        String encryptedBody = EncryptUtil.encrypt(secretKey, iv, JSON.toJSONString(req));
        return this.httpRequest(req.getAppId(), encryptedBody, requestUrl);
    }

    /**
     * 功能描述: 伙伴API-查询用户信息
     *
     * @param req
     * @return com.secret.sdk.controller.response.BaseResult
     * @author Czh
     * @date 2020/8/3 4:41 下午
     */
    @RequestMapping(value = "/user/check", method = RequestMethod.POST)
    public BaseResult userCheck(@Valid @RequestBody UserInfoReq req) throws Exception {
        String requestUrl = partnerDomain + "/api/v1/partner/user/check";
        String encryptedBody = EncryptUtil.encrypt(secretKey, iv, JSON.toJSONString(req));
        return this.httpRequest(req.getAppId(), encryptedBody, requestUrl);
    }

    /**
     * 功能描述: 伙伴API-根据ID、交易号获取充值记录
     *
     * @param req
     * @return com.secret.sdk.controller.response.BaseResult
     * @author Czh
     * @date 2020/8/3 4:41 下午
     */
    @RequestMapping(value = "/transfer/record", method = RequestMethod.POST)
    public BaseResult transferRecord(@Valid @RequestBody PartnerTransferRecordReq req) throws Exception {
        String requestUrl = partnerDomain + "/api/v1/partner/transfer/record";
        String encryptedBody = EncryptUtil.encrypt(secretKey, iv, JSON.toJSONString(req));
        return this.httpRequest(req.getAppId(), encryptedBody, requestUrl);
    }

    /**
     * 功能描述:  伙伴API-查询交易限额
     *
     * @param req
     * @return com.secret.sdk.controller.response.BaseResult
     * @author Czh
     * @date 2020/8/3 4:41 下午
     */
    @PostMapping("/coin/info")
    public BaseResult getPartnerCoinInfo(@Valid @RequestBody PartnerCoinInfoReq req) throws Exception {
        String requestUrl = partnerDomain + "/api/v1/partner/coin/info";
        String encryptedBody = EncryptUtil.encrypt(secretKey, iv, JSON.toJSONString(req));
        return this.httpRequest(req.getAppId(), encryptedBody, requestUrl);
    }

    /**
     * 功能描述: 伙伴API-查询伙伴商户余额
     *
     * @param req
     * @return com.secret.sdk.controller.response.BaseResult
     * @author Czh
     * @date 2020/8/3 4:41 下午
     */
    @PostMapping("/coin/balance")
    public BaseResult getPartnerCoinBalance(@Valid @RequestBody PartnerCoinInfoReq req) throws Exception {
        String requestUrl = partnerDomain + "/api/v1/partner/coin/balance";
        String encryptedBody = EncryptUtil.encrypt(secretKey, iv, JSON.toJSONString(req));
        return this.httpRequest(req.getAppId(), encryptedBody, requestUrl);
    }

}
