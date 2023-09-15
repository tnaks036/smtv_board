package com.sev.biz;

import java.sql.Connection;
import java.util.List;

import com.jdbc.JDBCTemplate;
import com.sev.dao.SEVBoardDao;
import com.sev.dao.SEVBoardDaoImpl;
import com.sev.dto.Criteria;
import com.sev.dto.SEVBoardDto;

public class SEVBoardBizImpl implements SEVBoardBiz{
	private SEVBoardDao dao = new SEVBoardDaoImpl();
	
	@Override
	public List<SEVBoardDto> selectAll() {
		Connection con = JDBCTemplate.getConnection();
		List<SEVBoardDto> res = dao.selectAll(con);
		JDBCTemplate.close(con);
		return res;
	}
	
	@Override
	public List<SEVBoardDto> selectPaging(Criteria cri) {
		Connection con = JDBCTemplate.getConnection();
		List<SEVBoardDto> res = dao.selectPaging(con, cri);
		JDBCTemplate.close(con);
		return res;
	}
	
	@Override
	public boolean insert(SEVBoardDto dto) {
		Connection con = JDBCTemplate.getConnection();
		boolean res = dao.insert(con, dto);
		if(res) {
			JDBCTemplate.commit(con);
		}
		JDBCTemplate.close(con);
		return res;
	}
	
	@Override
	public SEVBoardDto selectOne(int board_ID) {
		Connection con = JDBCTemplate.getConnection();
		SEVBoardDto dto = dao.selectOne(con, board_ID);
		JDBCTemplate.close(con);
		
		return dto;
	}
	
	@Override
	public boolean update(SEVBoardDto dto) {
		Connection con = JDBCTemplate.getConnection();
		boolean res = dao.update(con, dto);
		
		if(res) {
			JDBCTemplate.commit(con);
		}
		JDBCTemplate.close(con);
		
		return res;
	}
	
	@Override
	public boolean delete(int board_ID) {
		Connection con = JDBCTemplate.getConnection();
		boolean res = dao.delete(con, board_ID);
		
		if(res) {
			JDBCTemplate.commit(con);
		}
		JDBCTemplate.close(con);
		
		return res;
	}
	
	@Override
	public int selectAllTotal() {
		Connection con = JDBCTemplate.getConnection();
		int res = dao.selectAllTotal(con);
		
		JDBCTemplate.close(con);
		return res;
	}
}
