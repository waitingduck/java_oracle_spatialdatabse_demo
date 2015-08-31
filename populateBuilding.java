
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.SQLException;

public class populateBuilding implements populate{
	@Override
	public void populateData(Connection con){
		Scanner inputrow = null;
	    String row = null;
	    String insertsql = null;
	    String deletesql = "DELETE  FROM BUILDING";
	    int i=0,k=0;
	    int[] Break = new int[5];
	
	    //try to read file
		try{
			inputrow = new Scanner(new FileInputStream("src\\data\\buildings.xy"));
		}
		catch(FileNotFoundException e){
			System.out.println("file is not found");
			System.exit(0);
		}
		
		try {
			con.setAutoCommit(true);
			
			//if table is already exist, delete the data.
			PreparedStatement delstmt = con.prepareStatement(deletesql);
			delstmt.executeUpdate();
			
			//insert data
			while(inputrow.hasNextLine()){
				row = inputrow.nextLine();
				while(i<5){
					if(row.charAt(k)==','){
						Break[i] = k;
						i++;
						}
					k++;
				}
				insertsql = ("INSERT INTO BUILDING VALUES("
					+ "'" + row.substring(0,Break[0]) + "','"
					+ row.substring(Break[0]+2,Break[1]) + "'," 
					+ row.substring(Break[1] + 2,Break[2]) +",SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY("
					+ row.substring(Break[2]+1) + row.substring(Break[2],Break[4]) + ")))");
				PreparedStatement pstmt = con.prepareStatement(insertsql);
				pstmt.executeUpdate();
				System.out.println(insertsql);
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
