package com.xbw.website.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//这个需要配合 @ServletComponentScan
@WebFilter(filterName = "myFilter", urlPatterns = "/*", initParams = {
		@WebInitParam(name = "exclusions", value = "/auth,/login,js,png,css,gif,svg,ttf,eot,woff,woff2,otf,ico,jpg") })
public class MyFilter implements Filter {
	 private String[] reqUrls;  
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String urlsStr=filterConfig.getInitParameter("exclusions");  
        reqUrls=urlsStr.split(",");  

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;  
        HttpServletResponse response=(HttpServletResponse)resp;  
        HttpSession session=request.getSession();  
        String url=request.getRequestURI();  
        boolean flag=false;  
        for(int i=0;i<reqUrls.length;i++){  
            url=url.toLowerCase();  
            reqUrls[i]=reqUrls[i].toLowerCase();  
            reqUrls[i]=reqUrls[i].trim();  
            if(url.endsWith(reqUrls[i])){  
                //System.out.println("约定不拦截的请求直接放行==>"+url);  
                flag=true;  
                break;  
            }  
        }  
        if(flag){  
            chain.doFilter(req, resp);//约定不拦截的请求直接放行  
        }else{  
            Object userName= session.getAttribute("user");  
            if(userName!=null){  
            	  chain.doFilter(req, resp);//已经登录的请求放行  
            }else{  
                response.sendRedirect("/login");  
            }  
        }  

	}

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
