package com.sev.dao;

import java.sql.Connection;
import java.util.List;

import com.sev.dto.Criteria;
import com.sev.dto.SEVBoardDto;

public interface SEVBoardDao {
	String selectAllsql = "SELECT * FROM CS_Ques WHERE Del_Yn IS NULL OR (Del_Yn!='Y' AND Del_Yn!='y') ORDER BY board_ID DESC";
	String insertsql = "INSERT INTO CS_Ques (Comment_ID, Title, Contents, File_Name, Ins_Date_Time, Del_Yn) VALUES(?, ?, ?, ?, GETDATE(), 'N')";
	String selectOnesql = "SELECT * FROM CS_QUES WHERE Board_ID=?";
	String updatesql = "UPDATE CS_Ques SET Comment_ID=?, Title=?, Contents=?, Upd_Date_Time=GETDATE() WHERE Board_ID=?";
	String deletesql = "UPDATE CS_Ques SET Del_Date_Time=GETDATE(), Del_Yn='Y' WHERE Board_ID=?";
	String totalsql = "SELECT COUNT(*) AS total FROM CS_Ques WHERE Del_yn IS NULL OR (Del_Yn != 'Y' AND Del_Yn != 'y')";
	String selectPagingsql = "SELECT * FROM CS_Ques WHERE Del_Yn IS NULL OR (Del_Yn!='Y' AND Del_Yn!='y') ORDER BY Board_ID DESC OFFSET ? ROW FETCH NEXT ? ROWS ONLY";
	
	public List<SEVBoardDto> selectAll(Connection con);
	public List<SEVBoardDto> selectPaging(Connection con, Criteria cri);
	public boolean insert(Connection con, SEVBoardDto dto);
	public SEVBoardDto selectOne(Connection con, int board_ID);
	public boolean update(Connection con, SEVBoardDto dto);
	public boolean delete(Connection con, int board_ID);
	public int selectAllTotal(Connection con);
}
