import java.awt.Color;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;

public class drawElement {
	public void as(JLabel label, String sql, Color c)
	{
		Connection con = connectDB.Connect();
		draw dr = new draw();
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<AS> xyList = new ArrayList<AS>();
			
			while(rs.next())
			{
	            int xp = rs.getInt("ASPOINT.SDO_POINT.X");
	            int yp = rs.getInt("ASPOINT.SDO_POINT.Y");
	            int r = rs.getInt("RADIUS");
	            
	            System.out.println("[" + xp + "," + yp + "}");

	            AS temp = new AS(xp,yp,r);
	            xyList.add(temp);

//	            dr.draw(label, xp-r, yp-r, 2*r, c);  
//	            dr.filleddraw(label,x,y,4,c);  
			}
			//add super draw function
			
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		}
	}
	
	public void building(JLabel label, String sql, Color c)
	{
		Connection con = connectDB.Connect();
		draw dr = new draw();
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Building> buildingList= new ArrayList<Building>();
			
			while(rs.next())
			{
				
	            int nn = rs.getInt("NODENUMBER");
	            System.out.println(nn);
	            
	            Array rsArr = rs.getArray("shape.SDO_ORDINATES");
	            Number[] pos = (Number[])rsArr.getArray();
	            Building temp = new Building(nn);
	            for(int k=0;k<pos.length;k+=2){
	            	temp.setNode(0, k/2, pos[k].intValue());
	            	temp.setNode(1, k/2, pos[k].intValue());
	            }
	            buildingList.add(temp);
	            //dr.draw(label,x,y,nn,c);  
			}
			//add a super draw function here
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		} 
	}
	
	public void student(JLabel label, String sql, Color c)
	{
		Connection con = connectDB.Connect();
		draw dr = new draw();
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Student> studentList = new ArrayList<Student>();
			
			while(rs.next())
			{
	            int xp = rs.getInt("PPOINT.SDO_POINT.X");
	            int yp = rs.getInt("PPOINT.SDO_POINT.Y");
	            Student temp = new Student(xp,yp);
	            studentList.add(temp);
	            System.out.println("[" + xp + "," + yp + "}");
	            
	            //dr.filleddraw(label,x,y,4,c);  
			}
			//put super draw function here
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		} 
	}
	
}
