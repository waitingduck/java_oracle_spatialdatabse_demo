
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.SQLException;


public class populateAS implements populate{
	@Override
	public void populateData(Connection con){
		Scanner inputrow = null;
	    String row = null;
	    String insertsql = null;
	    String deletesql = "DELETE  FROM ANNOUNCESYS";
	    int i=0,k=0;
	    int[] Break = new int[3];
	
	    //try to read file
		try{
			inputrow = new Scanner(new FileInputStream("data\\announcementSystems.xy"));
		}
		catch(FileNotFoundException e){
			System.out.println("file is not found");
			System.exit(0);
		}
		
		try {
			con.setAutoCommit(true);
			
			//if the table is already exist, delete the data.
			PreparedStatement delstmt = con.prepareStatement(deletesql);
			delstmt.executeUpdate();
			
			//insert data
			while(inputrow.hasNextLine()){
				row = inputrow.nextLine();
				while(i<3){
					if(row.charAt(k)==','){
						Break[i] = k;
						i++;
					}
					k++;
				}
				insertsql = ("INSERT INTO ANNOUNCESYS VALUES("
						+ "'" + row.substring(0,Break[0]) + "',SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE("
						+ row.substring(Break[0]+1,Break[2]) + ",NULL),NULL,NULL)"
						+ row.substring(Break[2]) + ")");
				PreparedStatement pstmt = con.prepareStatement(insertsql);
				pstmt.executeUpdate();
				i=0;
				k=0;
			}
		} 
		catch (SQLException e) {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		}
	}
}
