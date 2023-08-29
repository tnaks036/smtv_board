package com.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.dto.BoardDto;
import com.jdbc.JDBCExample;

public class BoardDaoImpl implements BoardDao {

	//regit Consturctor
	@Override
	//게시글 전체 출력 > 목록
	public List<BoardDto> selectAll(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BoardDto> res = new ArrayList<BoardDto>();
		
		try { //쿼리 실행
			pstm = con.prepareStatement(SELECT_ALL_SQL);  // This line will cause an error due to access to non-static field.
			rs = pstm.executeQuery();					//Store query execution results in RS.
			
			while(rs.next()) { //Store in order in rs.dto
				BoardDto tmp = new BoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				res.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		return res;
	}

	@Override
	public BoardDto selectOne(Connection con, int bd_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Connection con, BoardDto dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Connection con, BoardDto dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Connection con, int bd_no) {
		// TODO Auto-generated method stub
		return false;
	}

}
