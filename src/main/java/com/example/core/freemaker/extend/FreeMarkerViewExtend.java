package com.example.core.freemaker.extend;


import com.example.common.model.UUser;
import com.example.common.utils.LoggerUtils;
import com.example.core.config.IConfig;
import com.example.core.shiro.token.manager.TokenManager;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class FreeMarkerViewExtend extends FreeMarkerView {
	
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request){
		
		try {
			super.exposeHelpers(model, request);
		} catch (Exception e) {
			LoggerUtils.fmtError(FreeMarkerViewExtend.class,e, "FreeMarkerViewExtend 加载父类出现异常。请检查。");
		}
		model.put("contextPath", request.getContextPath());
		model.putAll(Ferrmarker.initMap);
		UUser token = TokenManager.getToken();
		//String ip = IPUtils.getIP(request);
		if(TokenManager.isLogin()){
			model.put("token", token);//登录的token
		}
		
		model.put("_time", new Date().getTime());
		model.put("NOW_YEAY", Calendar.getInstance().get(Calendar.YEAR));//今年
		
		model.put("_v", String.valueOf(System.currentTimeMillis()));//版本号，重启的时间
		model.put("cdn", IConfig.get("domain.cdn"));//CDN域名
		model.put("basePath", request.getContextPath());//base目录。
		
	}
}
