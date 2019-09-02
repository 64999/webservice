package com.webservice.dao;

import com.webservice.entity.Sysuser;

public interface SysUserDao {

    public String validate(String usename);

    public Sysuser findAll(String username);
}
