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
import com.lesein.common.base.util.JacksonUtil;
import com.lesein.common.base.util.ParamValidatorUtil;
import com.lesein.common.base.util.RedisTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;

/**
 * @author WangJie
 * @date 2023/3/29
 */
@Component
public class LoginService extends BaseService<UserAccountMapper, UserAccount> {

    @Resource
    private RedisTemplateUtil redisTemplateUtil;

    /**
     * 登录
     *
     * @param request
     * @return
     */
    public LoginResponse authorization(LoginRequest request) {
        UserAccount userAccount = baseMapper.selectByAccountAndStatus(request.getAccountName(), UserStatusEnum.启用.getCode());
        ParamValidatorUtil.checkNotNull(userAccount, "账号信息不存在");

        String decrypt = AESCryptoUtil.decrypt(userAccount.getUserPassword(), userAccount.getId().toString());
        if (!Objects.equals(request.getPassword(), decrypt)) {
            throw new BusinessValidateException("密码错误");
        }
        UUID uuid = UUID.randomUUID();
        redisTemplateUtil.saveString(uuid.toString(), JacksonUtil.toJson(new UserAccount().setId(userAccount.getId()).setUserName(userAccount.getUserName()).setUserMobile(userAccount.getUserMobile())),600);
        return new LoginResponse().setToken(uuid.toString());

    }

    /**
     * token解析
     *
     * @param request
     */
    public UserAccountDTO analysis(AnalysisRequest request) {
        String token = request.getToken();
        String string = redisTemplateUtil.getString(token);
        if(StringUtils.isNotBlank(string)){
            redisTemplateUtil.expire(token,600);
        }
        return JacksonUtil.fromJson(string, UserAccountDTO.class);
    }
}
