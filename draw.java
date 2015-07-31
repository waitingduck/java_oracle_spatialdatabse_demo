
import java.awt.Color;
import java.awt.Graphics;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;


public class draw {
	// I will put this code here to remind me never to code like this again!
	
	
	
///////////////////////////////normal draw function//////////////////////////////////////
	public void as(JLabel label, String sql, Color c)
	{
		Connection con = connectDB.Connect();
		draw dr = new draw();
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next())
			{
				int xp = 0;
				int yp = 0;
				int r = 0;
				
				int[] x = new int[4];
	            int[] y = new int[4];
	            xp = rs.getInt("ASPOINT.SDO_POINT.X");
	            yp = rs.getInt("ASPOINT.SDO_POINT.Y");
	            r = rs.getInt("RADIUS");
	            
	            System.out.println("[" + xp + "," + yp + "}");
	            x[0] = xp - 7;
	            y[0] = yp + 7;
	            
	            x[1] = xp - 7;
	            y[1] = yp - 7;
	            
	            x[2] = xp + 7;
	            y[2] = yp - 7;
	            
	            x[3] = xp + 7;
	            y[3] = yp + 7;
	            

	            dr.draw(label, xp-r, yp-r, 2*r, c);  
	            dr.filleddraw(label,x,y,4,c);  
			}
			con.close();
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
			
			while(rs.next())
			{
				int i = 0;
				int nn = 0;
				boolean isX = true;
	            nn = rs.getInt("NODENUMBER");
	            System.out.println(nn);
	            Array rsArr = rs.getArray("shape.SDO_ORDINATES");
	            Number[] pos = (Number[])rsArr.getArray();
	            int[] x = new int[pos.length];
	            int[] y = new int[pos.length];
	            for(Number p : pos)
	            {
	               if (isX)
	               {
	                  x[i] = p.intValue();
	                  isX = false;
	                }
	                else 
	                {
	                   y[i] = p.intValue();
	                   isX = true;
	                   i++; // next point
	                }
	            }
	            dr.draw(label,x,y,nn,c);  
			}
			con.close();
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
			
			while(rs.next())
			{
				int xp = 0;
				int yp = 0;
				
				int[] x = new int[4];
	            int[] y = new int[4];
	            xp = rs.getInt("PPOINT.SDO_POINT.X");
	            yp = rs.getInt("PPOINT.SDO_POINT.Y");
	            
	            
	            System.out.println("[" + xp + "," + yp + "}");
	            x[0] = xp - 5;
	            y[0] = yp + 5;
	            
	            x[1] = xp - 5;
	            y[1] = yp - 5;
	            
	            x[2] = xp + 5;
	            y[2] = yp - 5;
	            
	            x[3] = xp + 5;
	            y[3] = yp + 5;
	            
	            dr.filleddraw(label,x,y,4,c);  
			}
			con.close();
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		} 
	}
	
/////////////////////////draw function can separate the nearest feature from others/////////////////////////////////////////////////
	public void as2(JLabel label, String sql, Color c)
	{
		boolean first = true;
		Connection con = connectDB.Connect();
		draw dr = new draw();
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				int xp = 0;
				int yp = 0;
				int r = 0;
				int[] x = new int[4];
	            int[] y = new int[4];
	            
	            xp = rs.getInt("ASPOINT.SDO_POINT.X");
	            yp = rs.getInt("ASPOINT.SDO_POINT.Y");
	            r = rs.getInt("RADIUS");
	            
	            System.out.println("[" + xp + "," + yp + "}");
	            x[0] = xp - 7;
	            y[0] = yp + 7;
	            
	            x[1] = xp - 7;
	            y[1] = yp - 7;
	            
	            x[2] = xp + 7;
	            y[2] = yp - 7;
	            
	            x[3] = xp + 7;
	            y[3] = yp + 7;
	            
                if(first)
                {
                	dr.draw(label, xp-r, yp-r, 2*r, Color.yellow);  
    	            dr.filleddraw(label,x,y,4,Color.yellow);
    	            first = false;
                }
                else
                {
                    dr.draw(label, xp-r, yp-r, 2*r, c);  
	                dr.filleddraw(label,x,y,4,c);
                }
			}
			con.close();
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		}
	}
	
	public void building2(JLabel label, String sql, Color c)
	{
		boolean first = true;
		Connection con = connectDB.Connect();
		draw dr = new draw();
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				int i = 0;
				int nn = 0;
				boolean isX = true;
	            nn = rs.getInt("NODENUMBER");
	            System.out.println(nn);
	            Array rsArr = rs.getArray("shape.SDO_ORDINATES");
	            Number[] pos = (Number[])rsArr.getArray();
	            int[] x = new int[pos.length];
	            int[] y = new int[pos.length];
	            for(Number p : pos)
	            {
	               if (isX)
	               {
	                  x[i] = p.intValue();
	                  isX = false;
	                }
	                else 
	                {
	                   y[i] = p.intValue();
	                   isX = true;
	                   i++; // next point
	                }
	            }
	            if(first)
	            {
	            	dr.draw(label,x,y,nn,Color.yellow);
	            	first = false;
	            }
	            else
	            {
	            	dr.draw(label,x,y,nn,c);
	            }
			}
			con.close();
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		} 
	}
	
	public void student2(JLabel label, String sql, Color c)
	{
		boolean first = true;
		Connection con = connectDB.Connect();
		draw dr = new draw();
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				int xp = 0;
				int yp = 0;
				
				int[] x = new int[4];
	            int[] y = new int[4];
	            xp = rs.getInt("PPOINT.SDO_POINT.X");
	            yp = rs.getInt("PPOINT.SDO_POINT.Y");
	            
	            
	            System.out.println("[" + xp + "," + yp + "}");
	            x[0] = xp - 5;
	            y[0] = yp + 5;
	            
	            x[1] = xp - 5;
	            y[1] = yp - 5;
	            
	            x[2] = xp + 5;
	            y[2] = yp - 5;
	            
	            x[3] = xp + 5;
	            y[3] = yp + 5;
	            
	            if(first)
	            {
	            	dr.filleddraw(label,x,y,4,Color.yellow);
	            	first = false;
	            }
	            else
	            {
	            	dr.filleddraw(label,x,y,4,c);
	            }
			}
			con.close();
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		} 
	}
	
/////////////////////only draw the nearest AS/////////////////////////////////////////////////////////
	public String as4a(JLabel label, String sql, Color c)
	{
		boolean first = true;
		Connection con = connectDB.Connect();
		draw dr = new draw();
		String asid = "";
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next())
			{
				int xp = 0;
				int yp = 0;
				int r = 0;
				int[] x = new int[4];
	            int[] y = new int[4];
	            
	            
	            xp = rs.getInt("ASPOINT.SDO_POINT.X");
	            yp = rs.getInt("ASPOINT.SDO_POINT.Y");
	            r = rs.getInt("RADIUS");
	            asid = rs.getString("ASID");
	            
	            System.out.println("[" + xp + "," + yp + "}");
	            x[0] = xp - 7;
	            y[0] = yp + 7;
	            
	            x[1] = xp - 7;
	            y[1] = yp - 7;
	            
	            x[2] = xp + 7;
	            y[2] = yp - 7;
	            
	            x[3] = xp + 7;
	            y[3] = yp + 7;
	            System.out.println("access into as4!");
                if(first)
                {
                	dr.draw(label, xp-r, yp-r, 2*r, c);  
    	            dr.filleddraw(label,x,y,4,c);
    	            first = false;
    	            break;
                }
			}
			con.close();
			
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		}
        return asid;
	}
	
///////////////////////draw the student in the selected AS range////////////////////////////////////////////////////////
	public void as4b(JLabel label, String sql)
	{
		Connection con = connectDB.Connect();
		draw dr = new draw();
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next())
			{
				int sxp = 0;
				int syp = 0;
				int asxp = 0;
				int asyp = 0;
				int r = 0;
				
				
	            
	            int[] sx = new int[4];
	            int[] sy = new int[4];
	            
	            
	            sxp = rs.getInt("PPOINT.SDO_POINT.X");
	            syp = rs.getInt("PPOINT.SDO_POINT.Y");
	            
	            
	            sx[0] = sxp - 5;
	            sy[0] = syp + 5;
	            
	            sx[1] = sxp - 5;
	            sy[1] = syp - 5;
	            
	            sx[2] = sxp + 5;
	            sy[2] = syp - 5;
	            
	            sx[3] = sxp + 5;
	            sy[3] = syp + 5;
	            
	            int[] asx = new int[4];
	            int[] asy = new int[4];
	            
	            asxp = rs.getInt("ASPOINT.SDO_POINT.X");
	            asyp = rs.getInt("ASPOINT.SDO_POINT.Y");
	            r = rs.getInt("RADIUS");
	            
	            asx[0] = asxp - 7;
	            asy[0] = asyp + 7;
	            
	            asx[1] = asxp - 7;
	            asy[1] = asyp - 7;
	            
	            asx[2] = asxp + 7;
	            asy[2] = asyp - 7;
	            
	            asx[3] = asxp + 7;
	            asy[3] = asyp + 7;
	            
	            dr.draw(label, asxp-r, asyp-r, 2*r, Color.red);  
	            dr.filleddraw(label,asx,asy,4,Color.red); 
	            
	            dr.filleddraw(label,sx,sy,4,Color.green);
			}
			con.close();
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		}
	}
	
	public void q5(JLabel label, String sql)
	{
		String ass[] = new String[7];
		int asnumber = 0;
		Color c;
		for(int i = 0; i<7; i++)  // initialize the ass[]
		{
			ass[i] = "";
		}
		
		Connection con = connectDB.Connect();
		draw dr = new draw();
        try 
        {
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next())
			{
				String asid = "";
				int check = 0;
				int asxp,asyp,sxp,syp,r;
				int asx[] = new int[4];
				int asy[] = new int[4];
				int sx[] = new int[4];
				int sy[] = new int[4];
				
				
				asid = rs.getString("ASID");
				check = checkAS(ass,asid);
				
				if(check > asnumber)
				{
					ass[asnumber] = asid;
					c = getColor(asnumber);
					asnumber++;
					asxp = rs.getInt("ASPOINT.SDO_POINT.X");
		            asyp = rs.getInt("ASPOINT.SDO_POINT.Y");
		            r = rs.getInt("RADIUS");
		            
		            asx[0] = asxp - 7;
		            asy[0] = asyp + 7;
		            
		            asx[1] = asxp - 7;
		            asy[1] = asyp - 7;
		            
		            asx[2] = asxp + 7;
		            asy[2] = asyp - 7;
		            
		            asx[3] = asxp + 7;
		            asy[3] = asyp + 7;
		            
		            dr.draw(label, asxp-r, asyp-r, 2*r, c);  
		            dr.filleddraw(label,asx,asy,4,c); 
		            System.out.println(asid + "'s color is " + asnumber);
		            
				}
				else
				{
					c = getColor(check);
				}
				
				sxp = rs.getInt("PPOINT.SDO_POINT.X");
	            syp = rs.getInt("PPOINT.SDO_POINT.Y");
	            
	            
	            sx[0] = sxp - 5;
	            sy[0] = syp + 5;
	            
	            sx[1] = sxp - 5;
	            sy[1] = syp - 5;
	            
	            sx[2] = sxp + 5;
	            sy[2] = syp - 5;
	            
	            sx[3] = sxp + 5;
	            sy[3] = syp + 5;
				
	            dr.filleddraw(label,sx,sy,4,c);
			}
			con.close();
        } 
        catch (SQLException e)
        {
			System.out.println("Insertion failed!");
			e.printStackTrace();
		}
	}
	
////////////////////////////basic draw polygon function/////////////////////////////////////////////////////////////////////////////////
	public void draw(JLabel label,int[] x,int[] y, int nn, Color c)  //draw a polygon
	{
		Graphics g = label.getGraphics();
	    g.setColor(c);
	    g.drawPolygon(x,y,nn);
	    g.dispose(); 
	}
	
	public void filleddraw(JLabel label,int[] x,int[] y, int nn, Color c)  //draw a filled polygon
	{
		Graphics g = label.getGraphics();
	    g.setColor(c);
	    g.fillPolygon(x,y,nn);
	    g.dispose(); 
	}
	
	public void draw(JLabel label,int x,int y, int r, Color c)  //draw a circle
	{
		Graphics g = label.getGraphics();
	    g.setColor(c);
	    g.drawOval(x,y,r,r);
	    g.dispose(); 
	}
	
	public Color getColor(int flag)
	{
		Color c = Color.black;
		switch(flag)
		{
			case 0:
				c = Color.blue;
				break;
			case 1:
				c = Color.cyan;
				break;
			case 2:
				c = Color.green;
				break;
			case 3:
				c = Color.yellow;
				break;
			case 4:
				c = Color.white;
				break;
			case 5:
				c = Color.orange;
				break;
			case 6:
				c = Color.pink;
				break;
		}
		return c;
	}
	public int checkAS(String[] ass, String as)
	{
		int position = 0;
		
		for(int i = 0; i < 7; i++)
		{
			position = i;
			if(ass[i].equals(as))
			{
				break;
			}
		}
		return position;
	}
	
}
