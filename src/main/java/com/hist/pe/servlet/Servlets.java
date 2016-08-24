/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hist.pe.servlet;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.Validate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hist.pe.config.Global;
import com.hist.pe.security.SystemAuthorizingRealm.Principal;
import com.hist.pe.utils.StringUtils;
import com.hist.pe.utils.UserUtils;


/**

 * @author liu
 * @version 2014-8-19
 */
public class Servlets {

	
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;


	
	
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {

	}

	public static void setNoCacheHeader(HttpServletResponse response) {

	}

	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {

	}

	
	public static void setEtag(HttpServletResponse response, String etag) {

	}

	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
			long lastModified) {

		return true;
	}

	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {

		return true;
	}

	
	public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
	}

	public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
		Validate.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		String pre = prefix;
		if (pre == null) {
			pre = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(pre) || paramName.startsWith(pre)) {
				String unprefixed = paramName.substring(pre.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					values = new String[] {};
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	/**
	 * 组合Parameters生成Query String的Parameter部分,并在paramter name上加上prefix.
	 * 
	 */
	public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix) {
		StringBuilder queryStringBuilder = new StringBuilder();

		String pre = prefix;
		if (pre == null) {
			pre = "";
		}
		Iterator<Entry<String, Object>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			queryStringBuilder.append(pre).append(entry.getKey()).append("=").append(entry.getValue());
			if (it.hasNext()) {
				queryStringBuilder.append("&");
			}
		}
		return queryStringBuilder.toString();
	}

	

	/**
	 * 是否是Ajax异步请求
	 * 
	 * @param request
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {

		String accept = request.getHeader("accept");
		String xRequestedWith = request.getHeader("X-Requested-With");
		Principal principal = UserUtils.getPrincipal();

		// 如果是异步请求或是手机端，则直接返回信息
		return ((accept != null && accept.indexOf("application/json") != -1
				|| (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
				|| (principal != null )));
	}

	/**
	 * 获取当前请求对象
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 判断访问URI是否是静态文件请
	 * 
	 * @throws Exception
	 */
	public static boolean isStaticFile(String uri) {
		return false;
	}
}
