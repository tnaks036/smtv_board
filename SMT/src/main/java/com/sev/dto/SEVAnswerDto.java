package com.sev.dto;

import java.sql.Date;

public class SEVAnswerDto {
	private int board_ID;
	private String comment_ID;
	private String answer_ID;
	private String contents;
	private byte[] file_Name;
	private Date ins_Date_Time;
	private Date upd_Date_Time;
	private Date del_Date_Time;
	private String del_Yn;
	
	public SEVAnswerDto() {
		super();
	}
	
	public SEVAnswerDto(int board_ID, String comment_ID, String answer_ID, String contents, byte[] file_Name,
			Date ins_Date_Time, Date upd_Date_Time, Date del_Date_Time, String del_Yn) {
		super();
		this.board_ID = board_ID;
		this.comment_ID = comment_ID;
		this.answer_ID = answer_ID;
		this.contents = contents;
		this.file_Name = file_Name;
		this.ins_Date_Time = ins_Date_Time;
		this.upd_Date_Time = upd_Date_Time;
		this.del_Date_Time = del_Date_Time;
		this.del_Yn = del_Yn;
	}

	public SEVAnswerDto(int board_ID, String comment_ID, String answer_ID, String contents, byte[] file_Name) {
		super();
		this.board_ID = board_ID;
		this.comment_ID = comment_ID;
		this.answer_ID = answer_ID;
		this.contents = contents;
		this.file_Name = file_Name;
	}

	public SEVAnswerDto(int board_ID, String comment_ID, String answer_ID, String contents) {
		super();
		this.board_ID = board_ID;
		this.comment_ID = comment_ID;
		this.answer_ID = answer_ID;
		this.contents = contents;
	}

	public SEVAnswerDto(int board_ID, String comment_ID) {
		super();
		this.board_ID = board_ID;
		this.comment_ID = comment_ID;
	}

	public int getBoard_ID() {
		return board_ID;
	}

	public void setBoard_ID(int board_ID) {
		this.board_ID = board_ID;
	}

	public String getComment_ID() {
		return comment_ID;
	}

	public void setComment_ID(String comment_ID) {
		this.comment_ID = comment_ID;
	}

	public String getAnswer_ID() {
		return answer_ID;
	}

	public void setAnswer_ID(String answer_ID) {
		this.answer_ID = answer_ID;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public byte[] getFile_Name() {
		return file_Name;
	}

	public void setFile_Name(byte[] file_Name) {
		this.file_Name = file_Name;
	}

	public Date getIns_Date_Time() {
		return ins_Date_Time;
	}

	public void setIns_Date_Time(Date ins_Date_Time) {
		this.ins_Date_Time = ins_Date_Time;
	}

	public Date getUpd_Date_Time() {
		return upd_Date_Time;
	}

	public void setUpd_Date_Time(Date upd_Date_Time) {
		this.upd_Date_Time = upd_Date_Time;
	}

	public Date getDel_Date_Time() {
		return del_Date_Time;
	}

	public void setDel_Date_Time(Date del_Date_Time) {
		this.del_Date_Time = del_Date_Time;
	}

	public String getDel_Yn() {
		return del_Yn;
	}

	public void setDel_Yn(String del_Yn) {
		this.del_Yn = del_Yn;
	}
	
	
}
