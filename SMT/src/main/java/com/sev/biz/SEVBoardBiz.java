package com.sev.biz;

import java.util.List;

import com.sev.dto.Criteria;
import com.sev.dto.SEVBoardDto;

public interface SEVBoardBiz {
	// 전체 조회
	public List<SEVBoardDto> selectAll();
	// 페이징 적용 조회
	public List<SEVBoardDto> selectPaging(Criteria cri);
	// 입력
	public boolean insert(SEVBoardDto dto);
	// 상세보기
	public SEVBoardDto selectOne(int board_ID);
	// 수정
	public boolean update(SEVBoardDto dto);
	// 삭제
	public boolean delete(int board_ID);
	// 전체 조회 총 개수
	public int selectAllTotal();
}
