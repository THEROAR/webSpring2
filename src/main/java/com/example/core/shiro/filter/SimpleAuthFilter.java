package com.example.core.shiro.filter;

import com.example.common.utils.LoggerUtils;
import com.example.core.shiro.session.CustomSessionManager;
import com.example.core.shiro.session.SessionStatus;
import net.sf.json.JSONObject;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SimpleAuthFilter extends AccessControlFilter {
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        System.out.println("经过了simpleFilter");
        HttpServletRequest httpRequest = ((HttpServletRequest)request);
        String url = httpRequest.getRequestURI();
        if(url.startsWith("/open/")){
            return Boolean.TRUE;
        }
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        Map<String, String> resultMap = new HashMap<String, String>();
        SessionStatus sessionStatus = (SessionStatus) session.getAttribute(CustomSessionManager.SESSION_STATUS);
        if (null != sessionStatus && !sessionStatus.isOnlineStatus()) {
            //判断是不是Ajax请求
            if (ShiroFilterUtils.isAjax(request) ) {
                LoggerUtils.debug(getClass(), "当前用户已经被踢出，并且是Ajax请求！");
                resultMap.put("user_status", "300");
                resultMap.put("message", "您已经被踢出，请重新登录！");
                out(response, resultMap);
            }
            return  Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //先退出
        Subject subject = getSubject(request, response);
        subject.logout();
        WebUtils.saveRequest(request);
        WebUtils.issueRedirect(request, response, "/open/kickedOut.shtml");
        return false;

    }

    private void out(ServletResponse hresponse, Map<String, String> resultMap)
            throws IOException {
        hresponse.setCharacterEncoding("UTF-8");
        PrintWriter out = hresponse.getWriter();
        out.println(JSONObject.fromObject(resultMap).toString());
        out.flush();
        out.close();
    }
}
