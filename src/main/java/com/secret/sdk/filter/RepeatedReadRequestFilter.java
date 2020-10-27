package com.secret.sdk.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * filter实现 servletRequest.getInputStream() 重复读取,http
 * body缓存在RepeatedlyReadRequestWrapper的body中。
 *
 * @author zkj
 * @date 2018/8/8
 */

public class RepeatedReadRequestFilter implements Filter {
	private String allowHeaders = "locale,access-token,cert,sign,time,ukey_token,Locale,Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token";
	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		res.setContentType("text/html;charset=UTF-8");
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "*");
		res.setHeader("Access-Control-Max-Age", "0");
		res.setHeader("Access-Control-Allow-Headers", allowHeaders);
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("XDomainRequestAllowed","1");

		if (req.getMethod().equals("OPTIONS")) {
			res.setStatus(HttpServletResponse.SC_OK);
		}


		chain.doFilter(servletRequest, res);
	}

	@Override
	public void destroy() {
		// Do nothing
	}
}
