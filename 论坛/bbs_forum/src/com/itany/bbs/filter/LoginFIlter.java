/*
 * 文 件 名:  LoginFIlter.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  杨卫兵 yangweibing@itany.com
 * 创建时间:  2016年8月4日
 */
package com.itany.bbs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <一句话功能简述>
 *  
 * @author  杨卫兵
 * @version  [V1.00, 2016年8月4日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class LoginFIlter implements Filter
{
    
    /**
     * 重写方法
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * 重写方法
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws IOException
     * @throws ServletException
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse reqs, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)reqs;
        HttpSession session = request.getSession();
        if(null == session.getAttribute("users")){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
        chain.doFilter(request,response);

        
    }
    
    /**
     * 重写方法
     * @param arg0
     * @throws ServletException
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig arg0)
        throws ServletException
    {
        // TODO Auto-generated method stub
        
    }
    
}
