package com.shiro.filter;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class loginFormAuthenticationFilter extends FormAuthenticationFilter{

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request,response,getSuccessUrl(),null,true);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        /*if (!StringUtils.isEmpty(getSuccessUrl())) {
            // getSession(false)：如果当前session为null,则返回null,而不是创建一个新的session
            Session session = subject.getSession(false);
            if (session != null) {
                session.removeAttribute("shiroSavedRequest");
            }
        }
        return super.onLoginSuccess(token, subject, request, response);*/
        WebUtils.getAndClearSavedRequest(request);
        WebUtils.redirectToSavedRequest(request,response,"/loginService/index");
        return false;

    }


}
