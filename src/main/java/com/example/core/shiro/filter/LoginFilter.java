package com.example.core.shiro.filter;

import com.example.common.model.UUser;
import com.example.common.utils.LoggerUtils;

import com.example.core.shiro.token.manager.TokenManager;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends AccessControlFilter {

    final static Class<LoginFilter> CLASS = LoginFilter.class;



    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        System.out.println("经过了loginFilter");
        UUser token = TokenManager.getToken();

        if(null != token || isLoginRequest(request, response)){// && isEnabled()
            return Boolean.TRUE;
        }
        if (ShiroFilterUtils.isAjax(request)) {// ajax请求
            Map<String,String> resultMap = new HashMap<String, String>();
            LoggerUtils.debug(getClass(), "当前用户没有登录，并且是Ajax请求！");
            resultMap.put("login_status", "300");
            resultMap.put("message", "\u5F53\u524D\u7528\u6237\u6CA1\u6709\u767B\u5F55\uFF01");//当前用户没有登录！
            ShiroFilterUtils.out(response, resultMap);
        }

        return Boolean.FALSE ;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //保存Request和Response 到登录后的链接
        System.out.println(this.getLoginUrl());
        saveRequestAndRedirectToLogin(request, response);
        System.out.println(this.getLoginUrl());
        return Boolean.FALSE ;
    }
}
