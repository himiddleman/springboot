package com.allinpay.service.impl;

import com.allinpay.core.constant.enums.BizEnums;
import com.allinpay.core.exception.AllinpayException;
import com.allinpay.repository.domain.permission.Admin;
import com.allinpay.repository.domain.permission.Authority;
import com.allinpay.repository.domain.permission.Resources;
import com.allinpay.repository.domain.permission.Role;
import com.allinpay.repository.domain.vo.UserVO;
import com.allinpay.repository.mapper.AuthorityMapper;
import com.allinpay.repository.mapper.ResourcesMapper;
import com.allinpay.repository.mapper.RoleMapper;
import com.allinpay.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public UserVO generateAuthority(Admin admin) {
        try {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userVO, admin);
            //查询角色集
            List<Role> roleList = roleMapper.selectRoleList(admin.getKid());
            Set<Integer> roleSet = new HashSet<>();
            roleList.stream().forEach(role -> {
                roleSet.add(role.getKid());
            });
            //查询权限集
            List<Authority> authorityList = authorityMapper.selectAuthorityList(roleSet);
            Set<Integer> authoritySet = new HashSet<>();
            authorityList.stream().forEach(authority -> {
                authoritySet.add(authority.getKid());
            });
            //查询资源集
            List<Resources> resourcesList = resourcesMapper.selectResourceList(authoritySet);
            Set<String> resourcesSet = new HashSet<>();
            resourcesList.stream().forEach(resources -> {
                resourcesSet.add(resources.getUrl());
            });
            userVO.setRoleList(roleSet);
            userVO.setAuthorityList(authoritySet);
            userVO.setUrlList(resourcesSet);
            return userVO;
        } catch (Exception e) {
            log.error("生成用户权限信息失败！", e);
            throw new AllinpayException(BizEnums.USER_GENERATE_AUTHORITY_FAIL.getCode(), BizEnums.USER_GENERATE_AUTHORITY_FAIL.getMsg());
        }
    }
}
