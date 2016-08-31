package com.itany.bbs.action;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itany.bbs.entity.Board;
import com.itany.bbs.entity.Paginate;
import com.itany.bbs.entity.Topic;
import com.itany.bbs.entity.User;
import com.itany.bbs.service.BoardService;
import com.itany.bbs.service.TopicService;
import com.itany.factory.ObjectFactory;

public class TopicAction {
	// 获取所有帖子版块
		public String findAll(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			BoardService service = (BoardService) ObjectFactory
					.getObject("boardservice");
			TopicService service2 = (TopicService) ObjectFactory
					.getObject("topicservice");
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
			int records = service2.getTopicCountAll();
			page.setRecords(records);

			// 计算最大的页码
			page.calc();
			request.setAttribute("page", page);
			System.out.println("getMaxPageNo:" + page.getMaxPageNo());
			// 获取主题对象的 list 集合
			List<Topic> topics = service2.findTopicAll(page);
		
			request.setAttribute("topics", topics);

			return "success";

		}
	// 根据模块id获取帖子版块
	public String findAllTopic(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("findAllTopic commit");
		BoardService service = (BoardService) ObjectFactory
				.getObject("boardservice");
		TopicService service2 = (TopicService) ObjectFactory
				.getObject("topicservice");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("users");
		if (user == null) {
			request.setAttribute("error", "请登陆后查看");
			return "login";

		}
		// 查找版块boardId 对象的名称
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		Board board = service.getBoardById(boardId);
		
		if(board == null){
			//response.sendError(404);
			request.setAttribute("loginrr", "你要查找的信息不存在，请登陆后重新操作");
			return "fail";
		}
		
		request.setAttribute("board", board);

		System.out.println("1: " + board.getBoardName());

		// 页号如果为空默认为1
		int pageNo;
		String num = request.getParameter("pageNo");
		if (num != null && !num.trim().equals("")) {
			pageNo = Integer.parseInt(num);
		} else {
			pageNo = 1;
		}
		// int pageNo = num==null ? 1: Integer.parseInt(num);
		// 新建一个分页对象
		Paginate page = new Paginate();

		page.setPageNo(pageNo);
		page.setPageSize(5);

		// 获取主题帖子的数量
		int records = service2.getTopicCount(boardId);
		page.setRecords(records);

		// 计算最大的页码
		page.calc();
		request.setAttribute("page", page);
		System.out.println("getMaxPageNo:" + page.getMaxPageNo());
		// 获取主题对象的 list 集合
		List<Topic> topics = service2.findTopicByPage(boardId, page);
		request.setAttribute("topics", topics);

		return "success";

	}
	
	// 后台修改topic获取帖子版块
		public String xiugaiopic(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			TopicService service2 = (TopicService) ObjectFactory
					.getObject("topicservice");
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("users");
			if (user == null) {
				request.setAttribute("error", "请登陆后查看");
				return "login";

			}
			// 查找版块boardId 对象的名称
			int topicid = Integer.parseInt(request.getParameter("topicId"));;
			String topictitle = request.getParameter("title");
			String tcont = request.getParameter("tcontents");
			String tconte=tcont.replaceAll("[<]", "&lt;");
			String tcontents=tconte.replaceAll("[>]+", "&gt;");
			String istops = request.getParameter("istop");
			int istop=istops.equals("是") ? 1 : 0;
			String isgoods = request.getParameter("isgood");
			int isgood= isgoods.equals("是") ? 1 : 0;
			

			
			Topic t=new Topic();
			t.setTopicId(topicid);
			t.setTitle(topictitle);
			t.setTcontents(tcontents);
			t.setIsgood(isgood);
			t.setIstop(istop);
			boolean b=service2.xiugaiTopic(t);	
			if(b){
				return "success";
			}

			return "faild";

		}
		// 后台删除topic获取帖子版块
				public String delet(HttpServletRequest request,
						HttpServletResponse response) throws Exception {
					System.out.println("delet");
					
					TopicService service2 = (TopicService) ObjectFactory
							.getObject("topicservice");
					HttpSession session = request.getSession();
					User user = (User) session.getAttribute("users");
					if (user == null) {
						request.setAttribute("error", "请登陆后查看");
						return "login";

					}
					// 查找版块boardId 对象的名称
					int topicid = Integer.parseInt(request.getParameter("topicid"));;
					String userid1=request.getParameter("userid");
					int userid = Integer.parseInt(request.getParameter("userid"));
					
					boolean b=service2.deleteTopic(topicid, userid);	
					if(b){
						return "success";
					}

					return "faild";

				}
	
	// 根据模块name获取帖子版块
		public String findTopicByBname(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			
			HttpSession session = request.getSession(true);
			//response.setHeader("content-type", "text/html;charset=utf-8");
			
			System.out.println("findAllTopic commit");
			BoardService service = (BoardService) ObjectFactory
					.getObject("boardservice");
			TopicService service2 = (TopicService) ObjectFactory
					.getObject("topicservice");
		
			User user = (User) session.getAttribute("users");
			if (user == null) {
				request.setAttribute("error", "请登陆后查看");
				return "login";

			}
			// 查找版块board对象的名称name
			//String boardname = request.getParameter("boardname");
			String boardname=request.getParameter("boardname");
			
//			String boardname = new String(request.getParameter("boardname").getBytes("iso-8859-1"), "utf-8");  
			

			// 页号如果为空默认为1
			int pageNo;
			String num = request.getParameter("pageNo");
			if (num != null && !num.trim().equals("")) {
				pageNo = Integer.parseInt(num);
			} else {
				pageNo = 1;
			}
			// int pageNo = num==null ? 1: Integer.parseInt(num);
			// 新建一个分页对象
			Paginate page = new Paginate();

			page.setPageNo(pageNo);
			page.setPageSize(5);

			// 获取主题帖子的数量
			int records = service2.getTopicCountByBoardname(boardname);
			page.setRecords(records);

			// 计算最大的页码
			page.calc();
			request.setAttribute("page", page);
			System.out.println("getMaxPageNo:" + page.getMaxPageNo());
			// 获取主题对象的 list 集合
			List<Topic> topics = service2.findTopicByboardname(boardname, page);
			System.out.println("topics:"+ topics.size());
			request.setAttribute("topics", topics);
			return "success";

		}
	
	// 获取置顶帖子版块
		public String findTopTopic(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			System.out.println("findAllTopic commit");
			BoardService service = (BoardService) ObjectFactory
					.getObject("boardservice");
			TopicService service2 = (TopicService) ObjectFactory
					.getObject("topicservice");
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("users");
			if (user == null) {
				request.setAttribute("error", "请登陆后查看");
				return "login";

			}
			// 查找版块boardId 对象的名称
			int Istop =1;
			
			request.setAttribute("top", Istop);


			// 页号如果为空默认为1
			int pageNo;
			String num = request.getParameter("pageNo");
			if (num != null && !num.trim().equals("")) {
				pageNo = Integer.parseInt(num);
			} else {
				pageNo = 1;
			}
			  pageNo = num==null ? 1: Integer.parseInt(num);
			// 新建一个分页对象
			Paginate page = new Paginate();

			page.setPageNo(pageNo);
			page.setPageSize(5);

			// 获取主题帖子的数量
			int records = service2.getTopicCountBytop(Istop);
			page.setRecords(records);

			// 计算最大的页码
			page.calc();
			request.setAttribute("page", page);
			System.out.println("getMaxPageNo:" + page.getMaxPageNo());
			// 获取主题对象的 list 集合
			List<Topic> topics = service2.findTopicByTop(Istop, page);
			request.setAttribute("topics", topics);

			return "success";

		}
	
		// 获取置顶帖子版块
				public String findGoodTopic(HttpServletRequest request,
						HttpServletResponse response) throws Exception {
					System.out.println("findAllTopic commit");
					BoardService service = (BoardService) ObjectFactory
							.getObject("boardservice");
					TopicService service2 = (TopicService) ObjectFactory
							.getObject("topicservice");
					HttpSession session = request.getSession();
					User user = (User) session.getAttribute("users");
					if (user == null) {
						request.setAttribute("error", "请登陆后查看");
						return "login";

					}
					// 查找版块boardId 对象的名称
					int Isgood = 1;
					
					request.setAttribute("good", Isgood);

					// 页号如果为空默认为1
					int pageNo;
					String num = request.getParameter("pageNo");
					if (num != null && !num.trim().equals("")) {
						pageNo = Integer.parseInt(num);
					} else {
						pageNo = 1;
					}
					  pageNo = num==null ? 1: Integer.parseInt(num);
					// 新建一个分页对象
					Paginate page = new Paginate();

					page.setPageNo(pageNo);
					page.setPageSize(5);

					// 获取主题帖子的数量
					int records = service2.getTopicCountBygood(Isgood);
					page.setRecords(records);

					// 计算最大的页码
					page.calc();
					request.setAttribute("page", page);
					System.out.println("getMaxPageNo:" + page.getMaxPageNo());
					// 获取主题对象的 list 集合
					List<Topic> topics = service2.findTopicBygood(Isgood, page);
					request.setAttribute("topics", topics);

					return "success";

				}
				
	
	

	// 发表话题 页面
	public String publish(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BoardService service2 = (BoardService) ObjectFactory
				.getObject("boardservice");
		TopicService service = (TopicService) ObjectFactory
				.getObject("topicservice");

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("users");
		if (user == null) {
			return "login";
		}

		String title = request.getParameter("title");
		String content = request.getParameter("contents");
		// 禁止发 空帖子
		if (title == null || "".equals(title.trim()) || content == null
				|| "".equals(content.trim())) {
			request.setAttribute("kong", "请填写文字发送");
			return "fail";

		}

		int boardId = Integer.parseInt(request.getParameter("boardId"));

		Board board = service2.getBoardById(boardId);

		session.setAttribute("board", board);

		System.out.println("333: " + board.getBoardName());

		int userid = user.getUserid();
		// 创建时间
		java.util.Date d = new java.util.Date();
		Timestamp postdate = new Timestamp(d.getTime());
		// 最后修改时间
		Timestamp modifydate = postdate;

		// Topic topic = new Topic(null, title, content, "", null, null, null,
		// null, userid, boardId, postdate, modifydate, null);

		Topic tp = new Topic();
		tp.setTitle(title.trim());
		tp.setTcontents(content.trim());
		tp.setBoardid(boardId);
		tp.setUserid(user.getUserid());

		// 发帖成功返回新帖id，失败返回0
		int topic = service.addTopic(tp);
		if (topic > 0) {
			return "success";
		}
		return "fail";

	}
	//设置帖子置顶
	public String totop(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BoardService service2 = (BoardService) ObjectFactory
				.getObject("boardservice");
		TopicService service = (TopicService) ObjectFactory
				.getObject("topicservice");

		int topicId = Integer.parseInt(request.getParameter("totop"));
		

	
		boolean totop = service.totop(topicId);
		if(totop){
			
			return "success";
		}
		return "faild";


	}
	//设置帖子为精华
	public String togood(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BoardService service2 = (BoardService) ObjectFactory
				.getObject("boardservice");
		TopicService service = (TopicService) ObjectFactory
				.getObject("topicservice");

		int topicId = Integer.parseInt(request.getParameter("togood"));
		

	
		boolean togood = service.togood(topicId);
		if(togood){
			
			return "success";
		}
		return "faild";


	}
	
	
	
	
	

	public static void main(String[] args) {
		BoardService service = (BoardService) ObjectFactory
				.getObject("boardservice");

		System.out.println(service.getBoardById(7).getBoardName());

		TopicService service2 = (TopicService) ObjectFactory
				.getObject("topicservice");
		int records = service2.getTopicCount(5);
		System.out.println(records);
		Paginate page = new Paginate();
		page.setRecords(records);

		System.out.println(page.getPageSize());

	}
	
}
