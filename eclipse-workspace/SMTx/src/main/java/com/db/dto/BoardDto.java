package com.db.dto;
import java.sql.Date;

public class BoardDto { //Data Transfer Object 데이터 전송 save container
	private int bd_no;
	private String bd_name;
	private String bd_title;
	private String bd_content;
	private Date bd_date;
	
	
	//"default constructor" - no -argument
	public BoardDto() {	
		super();	
	}
	
	//"parameter constructor" that accepts parameters to initialize the instance variables.
	public BoardDto(int bd_no, String bd_name, String bd_title, String bd_content, Date bd_date) {
		super();
		this.bd_no = bd_no;
		this.bd_name = bd_name;
		this.bd_title = bd_title;
		this.bd_content = bd_content;
		this.bd_date = bd_date;
	}
	
	//The dto allows you to set and get data from the DB.
	public int getBd_no() {	//bd_no search
		return bd_no;
	}
	
	public void setBd_no(int bd_no) { //bd_no sett
		this.bd_no = bd_no;
	}
}


