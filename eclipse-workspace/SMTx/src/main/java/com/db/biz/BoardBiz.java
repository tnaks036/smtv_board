package com.db.biz;

import java.util.List;

import com.db.dto.BoardDto;

public interface BoardBiz {	//Business Lobic -> 규칙, 운영 및 상호작용 처리0pre
	// 게시글 전체 출력
	public List<BoardDto> selectAll();
	
	//게시글 하나 출력
	public BoardDto selectOne(int bd_no);
	
	//글쓰기
	public boolean insert(BoardDto dto);
	
	//글수정
	public boolean update(BoardDto dto);
	
	//글삭제
	public boolean delete(int bd_no);
}

// main 요청은 biz의 selectAll() 메소드를 이용하기로 하는데 왜 biz 의 selectAll()을 이용하는지 궁금해. 
//그리고 biz와 bizimpl의 selectAll()은 게시글 전체 출력기능으로 dao의 selectAll() 메소드를 이용하기로 하는데 왜 그런지도 궁금해. 둘 차이가 뭔지.

//그리고 BoardDao.java에선 selectAll()에서 쓸 쿼리문을 작성하고,
//BoardDaoImpl.java에선 selectAll()을 완성해준대.
//미리 작성해둔 쿼리문을 가져와 실행해주고 rs에 그 값을 담고 res로 리턴한대. 순서대로 설명좀 해줘.