package com.sev.biz;

import java.sql.Connection;
import java.util.List;

import com.jdbc.JDBCTemplate;
import com.sev.dao.SEVAnswerDao;
import com.sev.dao.SEVAnswerDaoImpl;
import com.sev.dto.SEVAnswerDto;

public class SEVAnswerBizImpl implements SEVAnswerBiz{
	private SEVAnswerDao dao = new SEVAnswerDaoImpl();
	
	@Override
	public boolean insert(SEVAnswerDto dto) {
		Connection con = JDBCTemplate.getConnection();
		boolean res = dao.insert(con, dto);
		
		if(res) {
			JDBCTemplate.commit(con);
		}
		JDBCTemplate.close(con);
		return res;
	}
	
	@Override
	public List<SEVAnswerDto> selectAnswer(int board_ID) {
		Connection con = JDBCTemplate.getConnection();
		List<SEVAnswerDto> res = dao.selectAll(con, board_ID);
		JDBCTemplate.close(con);
		return res;
	}
	
	@Override
	public boolean update(SEVAnswerDto dto) {
		Connection con = JDBCTemplate.getConnection();
		boolean res = dao.update(con, dto);
		
		if(res) {
			JDBCTemplate.commit(con);
		}
		JDBCTemplate.close(con);
		return res;
	}
	
	@Override
	public boolean delete(SEVAnswerDto dto) {
		Connection con = JDBCTemplate.getConnection();
		boolean res = dao.delete(con, dto);
		
		if(res) {
			JDBCTemplate.commit(con);
		}
		JDBCTemplate.close(con);
		return res;
	}
}
