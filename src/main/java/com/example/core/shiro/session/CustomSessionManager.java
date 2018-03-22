package com.example.core.shiro.session;


import com.example.core.shiro.CustomShiroSessionDAO;

import java.util.*;

/**
 * 
 * 开发公司：SOJSON在线工具 <p>
 * 版权所有：© www.sojson.com<p>
 * 博客地址：http://www.sojson.com/blog/  <p>
 * <p>
 * 
 * 用户Session 手动管理
 * 
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年6月2日 　<br/>
 *
 * @author zhou-baicheng
 * @email  so@sojson.com
 * @version 1.0,2016年6月2日 <br/>
 * 
 */

public class CustomSessionManager {

	/**
	 * session status 
	 */
	public static final String SESSION_STATUS ="sojson-online-status";
	ShiroSessionRepository shiroSessionRepository;
	
	CustomShiroSessionDAO customShiroSessionDAO;

	public void setShiroSessionRepository(
			ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}

	public void setCustomShiroSessionDAO(CustomShiroSessionDAO customShiroSessionDAO) {
		this.customShiroSessionDAO = customShiroSessionDAO;
	}
}
