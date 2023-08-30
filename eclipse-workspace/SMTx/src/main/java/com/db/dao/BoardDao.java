package com.db.dao;

import java.util.List;
import java.sql.Connection;
import com.db.dto.BoardDto;

public interface BoardDao { //구현 메서드 집합//connect with db and CRUD, dao+daoImpl
	
	//String selectAllsql = "SELECT * FROM BOARD"; -> 상수 쿼필드로 선언
	String SELECT_ALL_SQL = "SELECT * FROM BOARD";
	
	//게시글 전체 출력 Read
	public List<BoardDto> selectAll(Connection con);	//This line defines a method named selectAll that takes a Connection parameter and returns a list of BoardDto objects.
	
	//게시글 하나 출력 Read 
	public BoardDto selectOne(Connection con, int bd_no);
	
	//글쓰기 Create
	public boolean insert(Connection con, BoardDto dto);
	
	//글수정 Update
	public boolean update(Connection con, BoardDto dto);
	
	//글삭제 Delete
	public boolean delete(Connection con, int bd_no);
	
}