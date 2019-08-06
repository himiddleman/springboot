package com.allinpay.core.shiro;

import com.allinpay.core.constant.enums.BizEnums;
import com.allinpay.core.exception.AllinpayException;
import com.allinpay.repository.domain.permission.Admin;
import com.allinpay.repository.domain.vo.UserVO;
import com.allinpay.repository.mapper.AdminMapper;
import com.allinpay.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private ILoginService loginService;
    @Autowired
    private AdminMapper adminMapper;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roles = new HashSet<>();
        Set<String> perms = new HashSet<>();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        UserVO primaryPrincipal = (UserVO) principals.getPrimaryPrincipal();

        //角色
        roles.add("user");
        if (primaryPrincipal.getEmail().equals("admin")) {
            roles.add("admin");
        }
        //权限
        perms.add("user");
        perms.add("hello");

        simpleAuthorizationInfo.addStringPermissions(perms);

        return simpleAuthorizationInfo;
    }

    //密码加密方式 ，一般使用主键、用户名作为盐值
    public static void main(String[] args) {
        String algorithmName = "Md5";
        Object password = "admin";
        ByteSource salt = ByteSource.Util.bytes("123456");
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(algorithmName, password, salt, hashIterations);
        System.out.println(simpleHash);
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
        return new SimpleAuthenticationInfo(userVO, userVO.getPassword(), ByteSource.Util.bytes("123456"), getName());
    }

    //使用md5加密
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName("MD5");
        shaCredentialsMatcher.setHashIterations(1024);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }

}
