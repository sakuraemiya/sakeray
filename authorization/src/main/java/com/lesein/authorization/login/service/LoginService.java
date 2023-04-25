package com.lesein.authorization.login.service;

import com.lesein.authorization.login.dao.UserAccountMapper;
import com.lesein.authorization.login.dto.LoginResponse;
import com.lesein.authorization.login.dto.UserAccountDTO;
import com.lesein.authorization.login.entity.UserAccount;
import com.lesein.authorization.login.enums.UserStatusEnum;
import com.lesein.authorization.login.request.AnalysisRequest;
import com.lesein.authorization.login.request.LoginRequest;
import com.lesein.common.base.exception.BusinessValidateException;
import com.lesein.common.base.persistence.BaseService;
import com.lesein.common.base.util.AESCryptoUtil;
import com.lesein.common.base.util.ParamValidatorUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author WangJie
 * @date 2023/3/29
 */
@Component
public class LoginService extends BaseService<UserAccountMapper, UserAccount> {

    /**
     * 登录
     * @param request
     * @return
     */
    public LoginResponse authorization(LoginRequest request) {
        UserAccount userAccount = baseMapper.selectByAccountAndStatus(request.getAccountName(), UserStatusEnum.启用.getCode());
        ParamValidatorUtil.checkNotNull(userAccount,"账号信息不存在");

        String decrypt = AESCryptoUtil.decrypt(userAccount.getUserPassword(), userAccount.getId().toString());
        if(!Objects.equals(request.getPassword(),decrypt)){
            throw new BusinessValidateException("密码错误");
        }
        UUID uuid = UUID.randomUUID();
        //todo 存入redis中
        return new LoginResponse().setToken(uuid.toString());

    }

    /**
     * token解析
     * @param request
     */
    public UserAccountDTO analysis(AnalysisRequest request){
        String token = request.getToken();
        //todo 从redis中取出人员信息
        return new UserAccountDTO().setUserName("王杰");
    }
}
