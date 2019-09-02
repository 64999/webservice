package com.webservice.service;

import com.webservice.dao.SysUserDao;
import com.webservice.entity.Sysuser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    public String validate(String username,String password) {
        String pwd = sysUserDao.validate(username);
        if (password.equals(pwd)){
            return "success";
        }else{
            return "false";
        }

    }

    public Sysuser findAll(String username) {
        return sysUserDao.findAll(username);
    }
}
