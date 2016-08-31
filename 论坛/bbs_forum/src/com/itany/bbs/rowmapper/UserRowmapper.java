package com.itany.bbs.rowmapper;

import java.sql.ResultSet;
import java.util.Date;

import com.itany.bbs.entity.User;
import com.itany.jdbc.util.RowMapper;

public class UserRowmapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs) throws Exception {

		User user = new User();
		user.setUserid(rs.getInt("userid"));
		user.setLoginname(rs.getString("loginname"));
		user.setLoginpwd(rs.getString("loginpwd"));
		user.setEmail(rs.getString("email"));
		user.setHead(rs.getString("head"));
		user.setRegtime(rs.getDate("regtime"));
		user.setState(rs.getInt("state"));
		user.setPoint(rs.getInt("point"));
		return user;

//		String loginname = rs.getString("loginname");
//		String loginpwd = rs.getString("loginpwd");
//		String email = rs.getString("email");
//		String head = rs.getString("head");
//		Date regtime = rs.getDate("regtime");
//		java.sql.Date regtimes = new java.sql.Date(regtime.getTime());
//		Integer state = rs.getInt("state");
//		Integer point = rs.getInt("point");
//		User user = new User(null,loginname, loginpwd, email, head, regtimes, state, point);
//		return user;

	}

}
