package com.itany.bbs.rowmapper;

import java.sql.ResultSet;
import java.util.Date;

import com.itany.bbs.entity.Board;
import com.itany.jdbc.util.RowMapper;

public class BoardRowmapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs) throws Exception {
		/**
		 * private Integer boardId; private String boardName; private Integer
		 * creatorId; private Integer masterId; private Date createtime; private
		 * Integer parentId; private Integer point; private Integer state;
		 */
		// Board board = new Board();
		// board.setBoardId(rs.getInt("boardId"));
		// board.setBoardName(rs.getString("boardName"));
		// board.setCreatorId(rs.getInt("creatorId"));
		// board.setMasterId(rs.getInt("masterId"));
		// board.setCreatetime(rs.getDate("createtime"));
		// board.setParentId(rs.getInt("parentId"));
		// board.setPoint(rs.getInt("point"));
		// board.setState(rs.getInt("state"));
		// return board;

		Integer boardId = rs.getInt("boardId");
		String boardName = rs.getString("boardName");
		Integer creatorId = rs.getInt("creatorId");
		Integer masterId = rs.getInt("masterId");
		Date createtime = rs.getDate("createtime");
		java.sql.Date regtimes = new java.sql.Date(createtime.getTime());
		Integer parentId = rs.getInt("parentId");
		Integer state = rs.getInt("state");
		Integer point = rs.getInt("point");
		Board board = new Board(boardId, boardName, creatorId, masterId,
				regtimes, parentId, point, state);
		return board;

	}

}
