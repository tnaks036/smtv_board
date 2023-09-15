package com.sev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.JDBCTemplate;
import com.sev.dto.SEVAnswerDto;

public class SEVAnswerDaoImpl implements SEVAnswerDao{
	@Override
	public boolean insert(Connection con, SEVAnswerDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(insertsql);
			pstm.setInt(1, dto.getBoard_ID());
			pstm.setString(2, dto.getComment_ID());
			pstm.setString(3, dto.getAnswer_ID());
			pstm.setString(4, dto.getContents());
			pstm.setBytes(5, dto.getFile_Name());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
		}
		return res > 0 ? true : false;
	}
	
	@Override
	public List<SEVAnswerDto> selectAll(Connection con, int board_ID) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<SEVAnswerDto> res = new ArrayList<SEVAnswerDto>();
		
		try {
			pstm = con.prepareStatement(selectAnswersql);
			pstm.setInt(1, board_ID);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				SEVAnswerDto tmp = new SEVAnswerDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getDate(6), rs.getDate(7), rs.getDate(8), rs.getString(9));
				res.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(con);
		}
		return res;
	}
	
	@Override
	public boolean update(Connection con, SEVAnswerDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(updatesql);
			pstm.setString(1, dto.getAnswer_ID());
			pstm.setString(2, dto.getContents());
			pstm.setInt(3, dto.getBoard_ID());
			pstm.setString(4, dto.getComment_ID());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(con);
		}
		return res > 0 ? true : false;
	}
	
	@Override
	public boolean delete(Connection con, SEVAnswerDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(deletesql);
			pstm.setInt(1, dto.getBoard_ID());
			pstm.setString(2, dto.getComment_ID());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(con);
		}
		return res > 0 ? true : false;
	}
}
