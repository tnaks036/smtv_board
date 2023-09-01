package dto;

public class BoardDTO {
    private int Board_ID;
    private String Comment_ID;
    private String Title;
    private String Contents;
    private byte[] File_Name; // 바이트 배열로 변경
    private String Ins_Date_Time;
    private String Upd_Date_Time;
    private String Del_Date_Time;
    private String Del_Yn;
    
    //private int recent; //게시글 댓글의 수
   
    public BoardDTO(int board_ID, String comment_ID, String title, String contents, String file_name, String ins_Date_Time, String upd_Date_Time, String del_Date_Time, String del_Yn) {
        super();
        this.Board_ID = board_ID;
        this.Comment_ID = comment_ID;
        this.Title = title;
        this.Contents = contents;
        
        // String을 byte[]로 변환
        if (file_name != null) {
            this.File_Name = file_name.getBytes();
        }
        
        this.Ins_Date_Time = ins_Date_Time;
        this.Upd_Date_Time = upd_Date_Time;
        this.Del_Date_Time = del_Date_Time;
        this.Del_Yn = del_Yn;
    }
	public int getBoard_ID() {
		return Board_ID;
	}

	public void setBoard_ID(int board_ID) {
		Board_ID = board_ID;
	}

	public String getComment_ID() {
		return Comment_ID;
	}

	public void setComment_ID(String comment_ID) {
		Comment_ID = comment_ID;
	}
	
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContents() {
		return Contents;
	}

	public void setContents(String contents) {
		Contents = contents;
	}

	public byte[] getFile_Name() {
		return File_Name;
	}

	public void setFile_Name(byte[] file_Name) {
		File_Name = file_Name;
	}

	public String getIns_Date_Time() {
		return Ins_Date_Time;
	}

	public void setIns_Date_Time(String ins_Date_Time) {
		Ins_Date_Time = ins_Date_Time;
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

	@Override
	public String toString() {
		return "BoardDAO [board_ID=" + Board_ID + ", Comment_ID=" + Comment_ID + ", Title=" + Title + ", Contents=" + Contents + 
				", File_Name" + File_Name + ", Ins_Date_Time" + Ins_Date_Time + ", Upd_Date_Time" + Upd_Date_Time + ", Del_Date_Time" + Del_Date_Time + ", Del_Yn" + Del_Yn +  "]";
	}
	
	/*
	 * recent=" + recent + "]";
	 * public int getRecent() {
		return recent;
	}
	public void setRecent(int recent) {
		this.recent = recent;
	}
	 */
	
}