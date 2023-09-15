package com.sev.biz;

import java.util.List;

import com.sev.dto.SEVAnswerDto;

public interface SEVAnswerBiz {
	public boolean insert(SEVAnswerDto dto);
	public List<SEVAnswerDto> selectAnswer(int board_ID);
	public boolean update(SEVAnswerDto dto);
	public boolean delete(SEVAnswerDto dto);
}
