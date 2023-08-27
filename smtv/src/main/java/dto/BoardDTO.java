package dto;

public class BoardDTO {
	   private int Board_ID;
	   private String Comment_ID;
	   private String Answer_ID;
	   private String Contents;
	   private String File_Name;
	   private String Ins_Date_Time;
	   private String Upd_Date_Time;
	   private String Del_Date_Time;
	   private String Del_Yn;
	   
	   
	   
	   
		public BoardDTO(int board_ID, String comment_ID, String answer_ID, String contents, String ins_Date_Time, 
						String file_name, String upd_Date_Time, String del_Date_Time, String del_Yn) {
		      super();
		      this.Board_ID = board_ID;
		      this.Comment_ID = comment_ID;
		      this.Answer_ID = answer_ID;
		      this.Contents = contents;
		      this.File_Name = file_name;
		      this.Ins_Date_Time = ins_Date_Time;
		      this.Upd_Date_Time = upd_Date_Time;
		      this.Del_Date_Time = del_Date_Time;
		      this.Del_Yn = del_Yn;
		   }




		public String getFile_Name() {
			return File_Name;
		}




		public void setFile_Name(String file_name) {
			File_Name = file_name;
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
	
}