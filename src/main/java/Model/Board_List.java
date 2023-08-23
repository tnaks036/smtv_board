package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.DataBase;

public class Board_List {

	DataBase db = new DataBase();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public String testQuery() {
		String query = "select * from select * from CS_Ans";
		try 
		{
			con = db.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			rs.close();
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			return "Board/board_List.jsp";
		}
	}
}
