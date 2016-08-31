package com.itany.bbs.action;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itany.bbs.entity.Paginate;
import com.itany.bbs.entity.Topic;
import com.itany.bbs.entity.User;
import com.itany.bbs.service.BoardService;
import com.itany.bbs.service.TopicService;
import com.itany.bbs.service.UserService;
import com.itany.bbs.util.MD5Util;
import com.itany.factory.ObjectFactory;

public class UserAction {
	UserService service = (UserService) ObjectFactory.getObject("userservice");
	
	//	注册 action
	public String regist(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession(true);
		request.setCharacterEncoding("utf-8");
		String loginname = request.getParameter("loginname");
        String loginpwd = request.getParameter("loginpwd");
        String md5pass = MD5Util.getMD5(loginpwd);
        String email = request.getParameter("email");
        String head = request.getParameter("head");
        java.util.Date date = new java.util.Date();
        Date regtime = new Date(date.getTime());
        int id = this.service.findIdByUserName(loginname);
        System.out.println(id);
       // System.out.println("id: "+id);
        if(id>0){
        	request.setAttribute("checkname", id);
        	return "fail";
        } 
        User user = new User(null, loginname, md5pass, email, head, regtime, null, null);
        this.service.addUser(user);
        request.setAttribute("user",user);
        return "success";
         
	}
	
	//登录action
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession(true);
		response.setHeader("content-type", "text/html;charset=utf-8");
		String username = request.getParameter("loginname");
		String password = request.getParameter("loginpwd");
		String passwords = MD5Util.getMD5(password);
		String vcode = request.getParameter("vcode");
		
		//验证码判断
		String code = (String) session.getAttribute("code");
		System.out.println(code);
		if(!(code.equalsIgnoreCase(vcode))){
			request.setAttribute("loginrr","验证码错误");
			return "fail";
		}
		//判断用户 密码是否正确
		User user = this.service.isValidUser(username, passwords);
		
		if(user !=null){
			session.setAttribute("users", user);
			return "success";
			
		}else{
		request.setAttribute("loginrr", "输入的用户密码有误");
		return "fail";
		}
		
	}
	
	//安全退出
	public String exit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.getSession().invalidate();
		return "success";
	}
	
	//修改密碼
	public String email(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession(true);
		
		 String loginname= request.getParameter("loginname");
		 String email = request.getParameter("email");
	      
	        boolean state = service.findLoginPwd(loginname, email);
	        
	        if(state == false){
	            request.setAttribute("error","找回失败");
	            return "fail";
	        }

		return "success";
		
	}
	
	//查找所有用户
	public String findAllusers(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserService service =(UserService)ObjectFactory.getObject("userservice");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("users");
		if (user == null) {
			request.setAttribute("error", "请登陆后查看");
			return "login";

		}
		int pageNo;
		String num = request.getParameter("pageNo");
		if (num != null && !num.trim().equals("")) {
			pageNo = Integer.parseInt(num);
		} else {
			pageNo = 1;
		}
		Paginate page = new Paginate();

		page.setPageNo(pageNo);
		page.setPageSize(5);

		// 获取主题帖子的数量
		int records = service.allUsercount();
		page.setRecords(records);

		// 计算最大的页码
		page.calc();
		request.setAttribute("page", page);
		System.out.println("getMaxPageNo:" + page.getMaxPageNo());
		// 获取主题对象的 list 集合
		List<User> users = service.findUsersall(page);
	
		request.setAttribute("users", users);

		return "success";

	}
	
}
