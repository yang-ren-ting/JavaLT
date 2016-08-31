package com.itany.bbs.serviceImpl;

import java.util.List;

import com.itany.bbs.dao.TopicDao;
import com.itany.bbs.entity.Paginate;
import com.itany.bbs.entity.Topic;
import com.itany.bbs.service.TopicService;
import com.itany.factory.ObjectFactory;

public class TopicServiceImpl implements TopicService {
	TopicDao dao = (TopicDao) ObjectFactory.getObject("topicdao");
	@Override
	public Topic findLastTopicByBoard(int boardId) {
		
		return this.dao.findLastTopicByBoard(boardId);
	}

	@Override
	public List<Topic> findTopicByPage(int boardId, Paginate page) {
		return this.dao.findTopicByPage(boardId, page);
	}

	@Override
	public int getTopicCount(int boardId) {
		
		return this.dao.getTopicCount(boardId);
	}

	@Override
	public Topic findTopicById(int topicId) {
		return this.dao.findTopicById(topicId);
	}

	@Override
	public int addTopic(Topic t) {
		return this.dao.addTopic(t);
	}

	@Override
	public boolean modifyTopic(Topic t) throws Exception {
		return this.dao.modifyTopic(t);
	}

	@Override
	public boolean deleteTopic(int topicId, int userId) throws Exception {
		return this.dao.deleteTopic(topicId, userId);
	}

	@Override
	public List<Topic> findTopicByTop(int top, Paginate page) {
		return this.dao.findTopicByIsTop(top, page);
		
	}

	@Override
	public List<Topic> findTopicBygood(int good, Paginate page) {
		// TODO Auto-generated method stub
		return this.dao.findTopicByIsgood(good, page);
	}

	@Override
	public int getTopicCountBytop(int top) {
	
		return this.dao.getTopicCountBytop(top);
	}

	@Override
	public int getTopicCountBygood(int good) {
		// TODO Auto-generated method stub
		return this.dao.getTopicCountBygood(good);
	}

	@Override
	public boolean totop(int id) {
		
		return this.dao.totopTopic(id);
	
	}

	@Override
	public boolean togood(int id) {
		// TODO Auto-generated method stub
		return this.dao.togoodTopic(id);
	}

	@Override
	public List<Topic> findTopicAll(Paginate page) {
		return this.dao.findTopicByAll(page);
	}

	@Override
	public int getTopicCountAll() {
		return this.dao.getTopicCountAll();

	}

	@Override
	public List<Topic> findTopicByboardname(String borardname, Paginate page) {
		return this.dao.findTopicBybordname(borardname, page);
	}

	@Override
	public int getTopicCountByBoardname(String boardname) {
		return this.dao.getTopicCountByboardname(boardname);
		
	}

	@Override
	public boolean xiugaiTopic(Topic t) throws Exception {
		
		return this.dao.xiugaiTopic(t);
		
	}

	

	

	

}
