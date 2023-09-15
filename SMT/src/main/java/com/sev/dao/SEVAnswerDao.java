package com.sev.dao;

import java.sql.Connection;
import java.util.List;

import com.sev.dto.SEVAnswerDto;

public interface SEVAnswerDao {
	String insertsql = "INSERT INTO CS_Ans (Board_ID, Comment_ID, Answer_ID, Contents, File_Name, Ins_Date_Time, Del_Yn) VALUES (?, ?, ?, ?, ?, GETDATE(), 'N')";
	String selectAnswersql = "SELECT * FROM CS_Ans WHERE Board_ID=? AND (Del_Yn IS NULL OR (Del_Yn!='Y' AND Del_Yn!='y')) ORDER BY board_ID ASC";
	String updatesql = "UPDATE CS_Ans SET Answer_ID=?, Contents=?, Upd_Date_Time=GETDATE() WHERE Board_ID=? AND Comment_ID=?";
	String deletesql = "UPDATE CS_ANS SET Del_Date_Time=GETDATE(), Del_Yn='Y' WHERE Board_ID=? AND Comment_ID=?";
	
	public boolean insert(Connection con, SEVAnswerDto dto);
	public List<SEVAnswerDto> selectAll(Connection con, int board_ID);
	public boolean update(Connection con, SEVAnswerDto dto);
	public boolean delete(Connection con, SEVAnswerDto dto);

}
