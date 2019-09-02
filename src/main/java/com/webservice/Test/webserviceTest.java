package com.webservice.Test;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class webserviceTest {

    public static void main(String[] args) {
        //工厂接口实例
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        //创建接口地址
        Client client = clientFactory.createClient("http://localhost:8099/webservice/userApiService?wsdl");
        Object [] result;
        try {

            result=client.invoke("addToUser", "Tom","0");
            System.out.println(result[0]);
            result = client.invoke("findAll","a");
            System.out.println(result[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
