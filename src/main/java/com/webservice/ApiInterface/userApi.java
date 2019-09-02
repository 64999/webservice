package com.webservice.ApiInterface;


import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * targetNamespace :指定从 Web Service 生成的 WSDL 和 XML 元素的 XML 名称空间。缺省值为从包含该 Web Service 的包名映射的名称空间。（字符串）
 * Webparam:方法参数名，使接口文档参数名与接口方法保持一致
 *
 */
@WebService(targetNamespace = "http://service.com.webservice.userApi", name = "userApi")
public interface userApi {

    /*public String addToUser(@WebParam(name = "username") String username, @WebParam(name = "sex") String sex);

    public String findAll(@WebParam(name = "username") String username);*/
}
