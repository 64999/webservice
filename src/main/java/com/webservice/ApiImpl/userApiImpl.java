package com.webservice.ApiImpl;


import com.webservice.ApiInterface.userApi;
import com.webservice.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;

@Service(value = "userApi")
@WebService(serviceName = "userApiService", portName = "userApiServicPort",
        targetNamespace = "http://service.com.webservice.userApi",
        endpointInterface = "com.webservice.ApiInterface.userApi")
public class userApiImpl implements userApi {

    /*@Resource
    private userDao userDao;

    public String addToUser(String username,String sex) {
        User u = new User();
        u.setName(username);
        u.setSex(sex);
        int res = -1;
        res = userDao.addToUser(u);
        if(res > 0){
            return "succeed";
        }else{
            return "failure";
        }
    }

    public String findAll(String username) {
        User user = userDao.findAll(username);
        if(user != null){
            return "1";
        }else{
            return "0";
        }
    }*/
}
