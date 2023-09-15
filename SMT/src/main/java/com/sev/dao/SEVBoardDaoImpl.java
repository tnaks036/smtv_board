package com.sev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.JDBCTemplate;
import com.sev.dto.Criteria;
import com.sev.dto.SEVBoardDto;

public class SEVBoardDaoImpl implements SEVBoardDao{
	@Override
	public List<SEVBoardDto> selectAll(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<SEVBoardDto> res = new ArrayList<SEVBoardDto>();
		
		try {
			pstm = con.prepareStatement(selectAllsql);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				SEVBoardDto tmp = new SEVBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getDate(7), rs.getString(8), rs.getString(9));
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
	public List<SEVBoardDto> selectPaging(Connection con, Criteria cri) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<SEVBoardDto> res = new ArrayList<SEVBoardDto>();
		
		try {
			pstm = con.prepareStatement(selectPagingsql);
			pstm.setInt(1, cri.getPageNum() * cri.getAmount() - cri.getAmount());
			pstm.setInt(2, cri.getAmount());
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				SEVBoardDto tmp = new SEVBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getDate(7), rs.getString(8), rs.getString(9));
				res.add(tmp);
				tmp.toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(con);
		}
		return res;
	}
	
	@Override
	public boolean insert(Connection con, SEVBoardDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(insertsql);
			pstm.setString(1, dto.getComment_ID());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContents());
			pstm.setString(4, dto.getFile_Name());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
		}
		return res > 0 ? true : false;
	}
	
	@Override
	public SEVBoardDto selectOne(Connection con, int board_ID) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		SEVBoardDto res = null;
		
		try {
			pstm = con.prepareStatement(selectOnesql);
			pstm.setInt(1, board_ID);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				res = new SEVBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getDate(7), rs.getString(8), rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstm);
		}
		return res;
	}
	
	@Override
	public boolean update(Connection con, SEVBoardDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(updatesql);
			pstm.setString(1, dto.getComment_ID());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContents());
			pstm.setInt(4, dto.getBoard_ID());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
		}
		
		return res > 0 ? true : false;
	}
	
	@Override
	public boolean delete(Connection con, int board_ID) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(deletesql);
			pstm.setInt(1, board_ID);
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
		}
		return res > 0 ? true : false;
	}
	
	@Override
	public int selectAllTotal(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstm = con.prepareStatement(totalsql);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(con);
		}
		return result;
	}
	
	
}
