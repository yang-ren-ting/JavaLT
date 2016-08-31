package com.itany.bbs.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;





public class CharacterFlter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		MyRequestWapper wrapper=new MyRequestWapper(request);
		
		chain.doFilter(wrapper, resp);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	private class MyRequestWapper extends HttpServletRequestWrapper{
		private HttpServletRequest original;
		public MyRequestWapper(HttpServletRequest request) {
			super(request);
			this.original=request;
		}

	@Override
	public String getParameter(String name) {
		String temp=this.original.getParameter(name);
		
		if(temp==null||temp.isEmpty()){
			return temp;
		}
		try {
			temp=new String(temp.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
}
}
