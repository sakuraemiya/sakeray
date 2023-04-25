package com.lesein.authorization.login.dao;

import com.lesein.authorization.login.entity.UserAccount;
import com.lesein.common.base.persistence.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * UserAccount 数据操作层
 * </p>
 *
 * @author WangJie
 * @since 2023-04-24
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {

    UserAccount selectByAccountAndStatus(@Param("userAccount")String userAccount,@Param("status")Integer status);
}