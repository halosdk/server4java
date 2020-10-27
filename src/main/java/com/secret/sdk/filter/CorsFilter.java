//package com.secret.sdk.filter;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CorsFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) res;
//
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods",
//                "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers",
//                "locale,access-token,cert,sign,time,ukey_token,Locale,Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
//        chain.doFilter(req, res);
//    }
//
//    @Override
//    public void destroy() {
//        // TODO Auto-generated method stub
//    }
//}