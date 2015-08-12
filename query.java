
import java.awt.Color;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextArea;


public class query {
	String asid;
	AS as;
	public int query1(JLabel label, int[] button,JTextArea text,int qcount)  // normal query call
	{
		draw2 dr2 = new draw2();
		if(button[0] == 1)
		{
			String sql = "SELECT ANS.ASID, ANS.ASPOINT.SDO_POINT.X,ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS FROM ANNOUNCESYS ANS";
			Color[] colorSet = {Color.red};
			dr2.superDraw(label,ASQuery(sql),colorSet);
			
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[1] == 1)
		{
			String sql = "SELECT B.NODENUMBER,B.SHAPE.SDO_ORDINATES FROM BUILDING B";
			//dr.building(label,sql,Color.yellow);
			Color[] colorSet= {Color.yellow};
			dr2.superDraw(label, buildingQuery(sql), colorSet);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[2] == 1)
		{
			String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y FROM STUDENT S";
			//dr.student(label,sql,Color.green);
			Color[] colorSet = {Color.green};
			dr2.superDraw(label, studentQuery(sql), colorSet);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		return qcount;
	}
	
	public int query2(JLabel label, int[] button, int x, int y,JTextArea text,int qcount) //normal query call(call as2,building2,student2)
	{
		draw2 dr2 = new draw2();
		if(button[0] == 1)
		{
			String sql = "SELECT ANS.ASID, ANS.ASPOINT.SDO_POINT.X, ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS, SDO_NN_DISTANCE(1) FROM ANNOUNCESYS ANS WHERE SDO_NN(ANS.ASPOINT,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)<50 ORDER BY SDO_NN_DISTANCE(1)";
			//dr.as2(label,sql,Color.green);
			Color[] colorSet = {Color.yellow,Color.green};
			
			dr2.superDraw(label,ASQuery(sql),colorSet, new howToColor(){
				@Override
				public Color getColor(int index, Color[] colorSet){
					return (index==0)? colorSet[0]:colorSet[1];
				}
			});
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[1] == 1)
		{
			String sql = "SELECT B.NODENUMBER,B.SHAPE.SDO_ORDINATES, SDO_NN_DISTANCE(1) " + 
		                 "FROM BUILDING B " + 
			             "WHERE SDO_NN(B.SHAPE,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)<50 ORDER BY SDO_NN_DISTANCE(1)";
			//dr.building2(label,sql,Color.green);
			
			Color[] colorSet = {Color.yellow,Color.green};
			
			dr2.superDraw(label,buildingQuery(sql),colorSet, new howToColor(){
				@Override
				public Color getColor(int index, Color[] colorSet){
					return (index==0)? colorSet[0]:colorSet[1];
				}
			});
			
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[2] == 1)
		{
			String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y, SDO_NN_DISTANCE(1) FROM STUDENT S WHERE SDO_NN(S.PPOINT,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)<50 ORDER BY SDO_NN_DISTANCE(1)";

			
			Color[] colorSet = {Color.yellow,Color.green};
			
			dr2.superDraw(label,studentQuery(sql),colorSet, new howToColor(){
				@Override
				public Color getColor(int index, Color[] colorSet){
					return (index==0)? colorSet[0]:colorSet[1];
				}
			});
			
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		return qcount;
	}
	
	public int query3(JLabel label, int[] button, int x, int y,JTextArea text,int qcount,String polygon)  // one more varibale polygon that indicate the polygon user draw, then draw features according the button
	{
		draw2 dr2 = new draw2();
		if(button[0] == 1)
		{
			String sql = "SELECT ANS.ASID, ANS.ASPOINT.SDO_POINT.X, ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS FROM ANNOUNCESYS ANS WHERE SDO_ANYINTERACT(ANS.ASPOINT,SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(" + polygon + ")))= 'TRUE'";
            
			Color[] colorSet = {Color.red};
			dr2.superDraw(label,ASQuery(sql),colorSet);
			
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[1] == 1)
		{
			String sql = "SELECT B.NODENUMBER,B.SHAPE.SDO_ORDINATES FROM BUILDING B WHERE SDO_ANYINTERACT(B.SHAPE,SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(" + polygon + ")))= 'TRUE'";
	
			
			Color[] colorSet = {Color.yellow};
			dr2.superDraw(label,buildingQuery(sql),colorSet);
			
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[2] == 1)
		{
			String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y FROM STUDENT S WHERE SDO_ANYINTERACT(S.PPOINT,SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(" + polygon + ")))= 'TRUE'";

			
			Color[] colorSet = {Color.green};
			dr2.superDraw(label,studentQuery(sql),colorSet);
			
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		return qcount;
	}
	public int query4a(JLabel label, int x, int y,JTextArea text,int qcount)  //call as4a to darw the nearest AS and record the ASID in global variable asid(call as4a)
	{
		draw2 dr2 = new draw2();
		String sql = "SELECT ANS.ASPOINT.SDO_POINT.X, ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS,ANS.ASID FROM ANNOUNCESYS ANS WHERE SDO_NN(ANS.ASPOINT,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_num_res=1', 1) = 'TRUE' ";
		
		Color[] colorSet = {Color.red};
		ArrayList temp = ASQuery(sql);
		asid = ((AS)(temp.get(0))).getASid();
		dr2.superDraw(label, temp, colorSet);
		text.append("Query" + qcount + " " + sql + "\n");
		qcount++;
		return qcount;
	}
	public int query4b(JLabel label, int x, int y,JTextArea text,int qcount)  // use the recorded asid to call the query4b and draw the result(call as4b)
	{
		
		draw2 dr2 = new draw2();
		String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y FROM ANNOUNCESYS ANS,STUDENT S WHERE SDO_NN(S.PPOINT,ANS.ASPOINT,'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)< ANS.RADIUS AND ANS.ASID = '" + asid + "'";
		
		Color[] colorSet = {Color.green};
		dr2.superDraw(label,studentQuery(sql),colorSet);
		
		text.append("Query" + qcount + " " + sql + "\n");
		qcount++;
		return qcount;
	}
	
	public int query5(JLabel label, int x, int y,JTextArea text,int qcount)  // use the recorded asid to call the query4b and draw the result(call as4b)
	{
		draw dr = new draw();
		String sql = "SELECT S2.PPOINT.SDO_POINT.X,S2.PPOINT.SDO_POINT.Y,ANS2.ASID,ANS2.ASPOINT.SDO_POINT.X,ANS2.ASPOINT.SDO_POINT.Y,ANS2.RADIUS,SDO_GEOM.SDO_DISTANCE(ANS2.ASPOINT,S2.PPOINT,0.05)" +
                     "FROM (SELECT S1.PID,MIN(SDO_GEOM.SDO_DISTANCE(ANS1.ASPOINT,S1.PPOINT,0.05))AS D " +
                           "FROM ANNOUNCESYS ANS, STUDENT S,ANNOUNCESYS ANS1, STUDENT S1 "+
                           "WHERE ANS.ASID = '" + asid + "' AND "+
                           "SDO_GEOM.SDO_DISTANCE(ANS.ASPOINT,S.PPOINT,0.05) < ANS.RADIUS AND "+
                           "S1.PID = S.PID AND "+
                           "ANS1.ASID <> ANS.ASID "+
                           "GROUP BY S1.PID) T, ANNOUNCESYS ANS2, STUDENT S2  "+
                     "WHERE S2.PID = T.PID AND " +
                     "SDO_GEOM.SDO_DISTANCE(ANS2.ASPOINT,S2.PPOINT,0.05) = T.D ";
		dr.q5(label,sql);
		text.append("Query" + qcount + " " + sql + "\n");
		qcount++;
		return qcount;
	}
	
	public ArrayList<element> ASQuery(String sql){
		Connection con = connectDB.Connect();
		ArrayList<element> result = new ArrayList<element>();
        try{
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()){
	            result.add(new AS(rs.getInt("ASPOINT.SDO_POINT.X"),rs.getInt("ASPOINT.SDO_POINT.Y"),rs.getInt("RADIUS"), rs.getString("ASID")));
			}
			
        } 
        catch (SQLException e){
			System.out.println("Insertion failed!");
			e.printStackTrace();
		}
        return result;
	}
	
	public ArrayList<element> buildingQuery(String sql){
		Connection con = connectDB.Connect();
		ArrayList<element> result = new ArrayList<element>();
		
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
    
	public ArrayList<element> studentQuery(String sql){
		Connection con = connectDB.Connect();
		ArrayList<element> result = new ArrayList<element>();
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
