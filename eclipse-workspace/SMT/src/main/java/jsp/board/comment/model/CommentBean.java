package jsp.board.comment.model;
import java.sql.Date;

public class CommentBean {
	private int Board_ID;	//게시글 굴번호
	private int Comment_ID;	// 댓글 글번호
	private String Answer_ID;	// 댓글 작성자
	private String Contents;	//내용
	private String File_Name;	// 부모글
	private Date Ins_Date_Time;	//댓글 작성일
	private String Upd_Date_Time;
	private String Del_Date_Time;
	private String Del_Yn;			
	
	 private int comment_level;        // 댓글- 답변글 깊이 ????
	 
	
	public int getBoard_ID() {
		return Board_ID;
	}
	public void setBoard_ID(int board_ID) {
		Board_ID = board_ID;
	}
	public int getComment_ID() {
		return Comment_ID;
	}
	public void setComment_ID(int comment_ID) {
		Comment_ID = comment_ID;
	}
	public String getAnswer_ID() {
		return Answer_ID;
	}
	public void setAnswer_ID(String answer_ID) {
		Answer_ID = answer_ID;
	}
	public String getContents() {
		return Contents;
	}
	public void setContents(String contents) {
		Contents = contents;
	}
	public String getFile_Name() {
		return File_Name;
	}
	public void setFile_Name(String file_Name) {
		File_Name = file_Name;
	}
	public Date getIns_Date_Time() {
		return Ins_Date_Time;
	}
	public void setIns_Date_Time(Date date) {
		Ins_Date_Time = date;
	}
	public String getUpd_Date_Time() {
		return Upd_Date_Time;
	}
	public void setUpd_Date_Time(String upd_Date_Time) {
		Upd_Date_Time = upd_Date_Time;
	}
	public String getDel_Date_Time() {
		return Del_Date_Time;
	}
	public void setDel_Date_Time(String del_Date_Time) {
		Del_Date_Time = del_Date_Time;
	}
	public String getDel_Yn() {
		return Del_Yn;
	}
	public void setDel_Yn(String del_Yn) {
		Del_Yn = del_Yn;
	}
	public int getComment_level() {
		return comment_level;
	}
	public void setComment_level(int comment_level) {
		this.comment_level = comment_level;
	}
}
