package com.itany.bbs.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itany.bbs.dao.TopicDao;
import com.itany.bbs.entity.Paginate;
import com.itany.bbs.entity.Topic;
import com.itany.bbs.rowmapper.TopicRowmapper;
import com.itany.jdbc.util.JDBCTemplate;
import com.itany.jdbc.util.JDBCUtil;
import com.itany.jdbc.util.KeyHolder;
import com.itany.jdbc.util.PreparedStatementCreator;

public class TopicDaoImpl implements TopicDao {
	
	private JDBCTemplate templete = new JDBCTemplate();
	private TopicRowmapper rowmapper = new TopicRowmapper();
	private QueryRunner qr=new QueryRunner();
    private Connection conn=JDBCUtil.getConnection();
    
	@Override
	/**
	 * 根据版块id查找最新发布的主题帖：如果没有则返回null
	 * @param boardId   版块id
	 * @return
	 */
	public Topic findLastTopicByBoard(int boardId) {
		  String sql = "SELECT * FROM TOPICS WHERE boardid = ? AND state = 1 ORDER BY topicId DESC";
	        List<Topic> list = this.templete.query(sql, rowmapper,new Object[] {boardId});
	        return list.size() == 0 ? null : list.get(0);
	}

	@Override
	/**
	 * 根据版块id分页查询该版块的主题帖
	 * @param boardId   版块id
	 * @param page      分页对象
	 * @return          
	 */
	public List<Topic> findTopicByPage(int boardId, Paginate page) {
        String sql = "SELECT * FROM TOPICS WHERE boardid = ? AND state = 1 ORDER BY postdate DESC LIMIT ?,?";
        Integer start = (page.getPageNo() - 1) * page.getPageSize();
        Object[] params = new Object[3];
        params[0] = boardId;
        params[1] = start;
        params[2] = page.getPageSize();
        List<Topic> list = this.templete.query(sql, rowmapper, params);
        return list;
       
	}

	@Override
	/**
	 * 根据版块id获取该版块的帖子数
	 * @param boardId   版块id
	 * @return
	 */
//	public int getTopicCount(int boardId) {
//		String sql = "select count(*) from topics where boardid= ? and state =1";
////		Object[] params = new Object[1];
////		params[0] = boardId;
////		
//		List list = this.templete.query(sql, rowmapper, new Object[]{boardId});
//		
//		int count = list.size();
//		return count;
//	}

	 public int getTopicCount(int boardId)
    {
        String sql="select count(*) from topics where state = 1 and boardid= ? ";
        ResultSetHandler<Long> rsh = new ScalarHandler<Long>();
        try
        {
            Long ret=qr.query(conn, sql, rsh, boardId);
           // return ret==null ? 0 : ret.intValue();
            
            if(ret != null){
            	return ret.intValue();
            }
            
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        }
        return 0;
    }
	
	@Override
	/**
	 * 根据主题帖id获取主题帖信息
	 * @param topicId   主题帖id
	 * @return          主题帖对象
	 */
	public Topic findTopicById(int topicId) {
		 String sql = "SELECT * FROM TOPICS WHERE topicId = ? AND state = 1";
	     List<Topic> list = templete.query(sql, rowmapper, new Object[] {topicId});
	     return list.size() == 0 ? null : list.get(0);
	}

	@Override
	/**
	 * 添加主题帖，用于用户发帖
	 * @param t     主题帖对象
	 * @return      发帖成功返回新帖id，失败返回0
	 */
//	public int addTopic(Topic t) {
//		KeyHolder keyHolder = new KeyHolder();
//		final String sql = "INSERT INTO TOPICS(title,tcontents,treplycontents,isreply,userid,boardid,postdate,modifydate) value(?,?,?,?,?,?,?,?)";
//		final Object param[] = {t.getTitle(), t.getTcontents(), t.getTreplycontents(), t.getIsreply(), t.getUserid(), t.getBoardid(), t.getPostdate(), t.getModifydate()};
//	     
//		this.templete.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws Exception {
//				PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//				
//				for(int i = 0;i>param.length;i++){
//					ps.setObject(i+1, param[i]);
//				}
//				return ps;
//			}
//		}, keyHolder);
//		return ((Long) keyHolder.getKey()).intValue();
//	}
	   public int addTopic(Topic t)
    {
        KeyHolder kh=new KeyHolder();
        JDBCTemplate tm=new JDBCTemplate();
        final String sql="insert into topics(title,tcontents,treplycontents,userid,boardid,postdate,modifydate)"+
            " values(?,?,'',?,?,now(),now());";
        final Object param[]={t.getTitle(),t.getTcontents(),t.getUserid(),t.getBoardid()};
        tm.update(new PreparedStatementCreator( )
        {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                throws Exception
            {
                PreparedStatement pstmt=conn.prepareStatement(
                    sql,PreparedStatement.RETURN_GENERATED_KEYS);
                for(int i=0;i<param.length;i++){
                    pstmt.setObject(i+1, param[i]);
                }
                return pstmt;
            }
        }, kh);
        return ((Long)kh.getKey()).intValue();
    }
    
	@Override
	/**
	 * 修改主题帖
	 * @param t     主题帖对象
	 * @return      true-修改成功；false-失败      
	 */
	public boolean modifyTopic(Topic t) throws Exception{
		String sql = "UPDATE TOPICS SET title = ?,tcontents = ?,modifydate = ? WHERE topicid = ?";
	    
		Object[] params = new Object[4];
		params[0] = t.getTitle();
		params[1] = t.getTcontents();
		params[2] = t.getModifydate();
		params[3] = t.getTopicId();
		this.templete.update(sql, params);
		return true;
		
		
	}

	@Override
	/**
	 * 删除主题帖
	 * @param topicId   主题帖id
	 * @param userId    用户id
	 * @return          true-删除成功；false-失败
	 */
	public boolean deleteTopic(int topicId, int userId) throws Exception {
		String sql = "update topics set state = 0 where topicId=? and userid = ?";
		Object[] params = new Object[2];
		params[0] = topicId;
		params[1] = userId;
		this.templete.update(sql, params);
		return true; 
	}
	/**
	 * 查找精华帖
	 * @param topicId   主题帖id
	 * @param page      分页对象
	 * @return          
	 */
	@Override
	public List<Topic> findTopicByIsgood(int good, Paginate page) {
		String sql = "SELECT * FROM TOPICS WHERE Isgood = ? AND state = 1 ORDER BY postdate DESC LIMIT ?,?";
        Integer start = (page.getPageNo() - 1) * page.getPageSize();
        Object[] params = new Object[3];
        params[0] = good;
        params[1] = start;
        params[2] = page.getPageSize();
        List<Topic> list = this.templete.query(sql, rowmapper, params);
        return list;
	}
	/**
	 * 查找置顶帖
	 * @param topicId   主题帖id
	 * @param page      分页对象
	 * @return          
	 */
	@Override
	public List<Topic> findTopicByIsTop(int top, Paginate page) {
		String sql = "SELECT * FROM TOPICS WHERE Istop = ? AND state = 1 ORDER BY postdate DESC LIMIT ?,?";
        Integer start = (page.getPageNo() - 1) * page.getPageSize();
        Object[] params = new Object[3];
        params[0] = top;
        params[1] = start;
        params[2] = page.getPageSize();
        List<Topic> list = this.templete.query(sql, rowmapper, params);
        return list;
	}
	/**
	 * 根据版块Istop获取置顶的帖子数
	 * @param top   帖子Istop值
	 * @return
	 */

	@Override
	public int getTopicCountBytop(int top) {
		 String sql="select count(*) from topics where state = 1 and Istop= ? ";
	        ResultSetHandler<Long> rsh = new ScalarHandler<Long>();
	        try
	        {
	            Long ret=qr.query(conn, sql, rsh, top);
	           // return ret==null ? 0 : ret.intValue();
	            
	            if(ret != null){
	            	return ret.intValue();
	            }
	            
	        }
	        catch (SQLException e)
	        {
	           e.printStackTrace();
	        }
	        return 0;
	}
	/**
	 * 根据版块Isgood获取置顶的帖子数
	 * @param top   帖子Isgood值
	 * @return
	 */

	@Override
	public int getTopicCountBygood(int good) {
		 String sql="select count(*) from topics where state = 1 and Isgood= ? ";
	        ResultSetHandler<Long> rsh = new ScalarHandler<Long>();
	        try
	        {
	            Long ret=qr.query(conn, sql, rsh, good);
	           // return ret==null ? 0 : ret.intValue();
	            
	            if(ret != null){
	            	return ret.intValue();
	            }
	            
	        }
	        catch (SQLException e)
	        {
	           e.printStackTrace();
	        }
	        return 0;
	}
	/**
	 * 根据topicid设置帖子置顶
	 * @param id   帖子topicid值
	 * @return
	 */
	@Override
	public boolean totopTopic(int id) {
		 String sql="update topics set Istop = 1 where topicId="
		 		+ "? ";
		 Object[] params = new Object[1];
		 params[0]=id;
		 this.templete.update(sql, params);
		 return true;
	}
	/**
	 * 根据topicid设置帖子精华
	 * @param id   帖子topicid值
	 * @return
	 */
	@Override
	public boolean togoodTopic(int id) {
		 String sql="update topics set Isgood = 1 where topicId="
			 		+ "? ";
			 Object[] params = new Object[1];
			 params[0]=id;
			 this.templete.update(sql, params);
			 return true;
	}

	@Override
	public List<Topic> findTopicByAll(Paginate page) {
		String sql = "SELECT * FROM TOPICS WHERE state = 1 ORDER BY postdate DESC LIMIT ?,?";
        Integer start = (page.getPageNo() - 1) * page.getPageSize();
        Object[] params = new Object[2];
        params[0] = start;
        params[1] = page.getPageSize();
        List<Topic> list = this.templete.query(sql, rowmapper, params);
        return list;
	}

	@Override
	public int getTopicCountAll() {
		 String sql="select count(*) from topics where state = 1";
		 ResultSetHandler<Long> rsh = new ScalarHandler<Long>();
	        try
	        {
	            Long ret=qr.query(conn, sql, rsh);
	           // return ret==null ? 0 : ret.intValue();
	            
	            if(ret != null){
	            	return ret.intValue();
	            }
	            
	        }
	        catch (SQLException e)
	        {
	           e.printStackTrace();
	        }
	        return 0;
	}

	@Override
	public List<Topic> findTopicBybordname(String boardname, Paginate page) {
		String sql = "SELECT * FROM TOPICS WHERE state = 1 and boardid =(select boardId from boards where boardName=?)ORDER BY postdate DESC LIMIT ?,?";
		 Integer start = (page.getPageNo() - 1) * page.getPageSize();
	        Object[] params = new Object[3];
	        params[0] = boardname;
	        params[1] = start;
	        params[2] = page.getPageSize();
	        List<Topic> list = this.templete.query(sql, rowmapper, params);
	        return list;
		
	}

	@Override
	public int getTopicCountByboardname(String boardname) {
		 String sql="select count(*) from topics where state = 1 and boardid =(select boardId from boards where boardName=?)";
		 ResultSetHandler<Long> rsh = new ScalarHandler<Long>();
	        try
	        {
	            Long ret=qr.query(conn, sql, rsh, boardname);
	           // return ret==null ? 0 : ret.intValue();
	            
	            if(ret != null){
	            	return ret.intValue();
	            }
	            
	        }
	        catch (SQLException e)
	        {
	           e.printStackTrace();
	        }
	        return 0;
	}

	@Override
	public boolean xiugaiTopic(Topic t) throws Exception {
		String sql="UPDATE TOPICS SET title = ?,tcontents = ?,istop = ?,isgood = ?,modifydate = now() WHERE topicId = ? ";
	        Object[] params = new Object[5];
	        params[0] = t.getTitle();
	        params[1] = t.getTcontents();
	        params[2] = t.getIstop();
	        params[3] = t.getIsgood();
	        params[4] =t.getTopicId();
	        this.templete.update(sql, params);
	        return true;
	}

	

}
