package com.db.biz;

import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;

import com.db.dao.BoardDao;
import com.db.dao.BoardDaoImpl;
import com.db.dto.BoardDto;
import com.jdbc.*;
import com.jdbc.JDBCExample;

public class BoardBizImpl implements BoardBiz{

	private BoardDao dao = new BoardDaoImpl();
	
	@Override
	public List<BoardDto> selectAll(con) {
		Connection con = getConnection();
		List<BoardDto> res = dao.selectAll(con);
		close(con);
		
		return res;
	}

	@Override
	public BoardDto selectOne(int bd_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(BoardDto dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(BoardDto dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int bd_no) {
		// TODO Auto-generated method stub
		return false;
	}

}
