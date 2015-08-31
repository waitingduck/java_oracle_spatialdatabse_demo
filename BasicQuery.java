import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BasicQuery {
	public ArrayList<Element> ASQuery(String sql){
		Connection con = ConnectDB.Connect();
		ArrayList<Element> result = new ArrayList<Element>();
        try{
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()){
	            result.add(new AnnouncementSystem(rs.getInt("ASPOINT.SDO_POINT.X"),rs.getInt("ASPOINT.SDO_POINT.Y"),rs.getInt("RADIUS"), rs.getString("ASID")));
			}
			
        } 
        catch (SQLException e){
			System.out.println("Insertion failed!");
			e.printStackTrace();
		}
        return result;
	}
	
	public ArrayList<Element> buildingQuery(String sql){
		Connection con = ConnectDB.Connect();
		ArrayList<Element> result = new ArrayList<Element>();
		
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
	            int nn = rs.getInt("NODENUMBER");
	            System.out.println(nn);
	            Array rsArr = rs.getArray("shape.SDO_ORDINATES");
	            Number[] pos = (Number[])rsArr.getArray();
	            int[][]xy = new int[2][nn+1];
	            int count=0;
	            for(Number p : pos){
	               xy[(count%2==0)? 0:1][count/2] = p.intValue();
	               count++;
	            }
	            result.add(new Building(nn,xy));
			}
			
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		} 
        return result;
	}
    
	public ArrayList<Element> studentQuery(String sql){
		Connection con = ConnectDB.Connect();
		ArrayList<Element> result = new ArrayList<Element>();
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
	            result.add(new Student(rs.getInt("PPOINT.SDO_POINT.X"),rs.getInt("PPOINT.SDO_POINT.Y")));
			}
			
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		}
        return result;
	}
}
