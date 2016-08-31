package com.itany.bbs.service;

import java.util.List;

import com.itany.bbs.entity.Paginate;
import com.itany.bbs.entity.Topic;

public interface TopicService {
	/**
     * 根据版块id查找最新发布的主题帖：如果没有则返回null
     * @param boardId   版块id
     * @return
     */
    Topic findLastTopicByBoard(int boardId);
    
    /**
     * 根据Istop查找最新发布的主题帖：如果没有则返回null
     * @param top   
     * @return
     */
    List<Topic> findTopicByTop(int top,Paginate page);
    
    /**
     * 根据bordername查找最新发布的主题帖：如果没有则返回null
     * @param top   
     * @return
     */
    List<Topic> findTopicByboardname(String borardname,Paginate page);
    
    /**
     * 根据Isgood查找最新发布的主题帖：如果没有则返回null
     * @param good  
     * @return
     */
    List<Topic> findTopicBygood(int good,Paginate page);
    
    /**
     * 根据版块id分页查询该版块的主题帖
     * @param boardId   版块id
     * @param page      分页对象
     * @return          
     */
    List<Topic> findTopicAll(Paginate page);
    
    /**
     * 根据版块id分页查询该版块的主题帖
     * @param page      分页对象
     * @return          
     */
    List<Topic> findTopicByPage(int boardId, Paginate page);
    
    /**
     * 根据版块id获取该版块的帖子数
     * @param boardId   版块id
     * @return
     */
    int getTopicCount(int boardId);
    
    int getTopicCountAll();
    int getTopicCountBytop(int top);
    int getTopicCountByBoardname(String boardname);
    int getTopicCountBygood(int good);

    /**
     * 根据主题帖id获取主题帖信息
     * @param topicId   主题帖id
     * @return          主题帖对象
     */
    Topic findTopicById(int topicId);
    
    /**
     * 添加主题帖，用于用户发帖
     * @param t     主题帖对象
     * @return      发帖成功返回新帖id，失败返回0
     */
    int addTopic(Topic t);

    /**
     * 修改主题帖
     * @param t     主题帖对象
     * @return      true-修改成功；false-失败      
     * @throws Exception 
     */
    boolean modifyTopic(Topic t) throws Exception;
    
    /**
     * 修改主题帖
     * @param t     主题帖对象
     * @return      true-修改成功；false-失败      
     * @throws Exception 
     */
    boolean xiugaiTopic(Topic t) throws Exception;
    
    
    /**
     * 置顶主题帖
     * @param t     主题帖对象
     * @return      true-修改成功；false-失败      
     * 
     */
    boolean totop(int id);
    
    /**
     * 设置主题帖精华
     * @param t     主题帖对象
     * @return      true-修改成功；false-失败      
     * 
     */
    boolean togood(int id);
    /**
     * 删除主题帖
     * @param topicId   主题帖id
     * @param userId    用户id
     * @return          true-删除成功；false-失败
     */
    boolean deleteTopic(int topicId,int userId) throws Exception;
}
