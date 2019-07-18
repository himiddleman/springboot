package com.allinpay.core.shiro;

import com.allinpay.core.constant.enums.BizEnums;
import com.allinpay.core.exception.AllinpayException;
import com.allinpay.repository.domain.permission.Admin;
import com.allinpay.repository.domain.vo.UserVO;
import com.allinpay.repository.mapper.AdminMapper;
import com.allinpay.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private ILoginService loginService;
    @Autowired
    private AdminMapper adminMapper;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        //查询用户信息
        Admin admin = adminMapper.selectByUsername(username);
        if (Objects.isNull(admin)) {
            log.error("用户不存在：{}", username);
            throw new AllinpayException(BizEnums.USER_NOT_EXIST.getCode(), BizEnums.USER_NOT_EXIST.getMsg());
        }
        UserVO userVO = loginService.generateAuthority(admin);
        return new SimpleAuthenticationInfo(userVO, userVO.getPassword(), getName());
    }

//    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
//        shaCredentialsMatcher.setHashAlgorithmName("SHA-256");
//        shaCredentialsMatcher.setHashIterations(16);
//        super.setCredentialsMatcher(shaCredentialsMatcher);
//    }
}
