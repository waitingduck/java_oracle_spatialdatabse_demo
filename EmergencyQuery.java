import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextArea;


public class EmergencyQuery extends BasicQuery{
	public int query(JLabel label, int x, int y,JTextArea text,int qcount, String asid)  // use the recorded asid to call the query4b and draw the result(call as4b)
	{
		Draw dr2 = new Draw();
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
		ArrayList<Element> temp = studentAndAS(sql);
		Color[] colorSet = new Color[temp.size()];
		int colorIndex = 0;
		String currentASid = (temp.size()>0)? ((AnnouncementSystem)(temp.get(0))).getASid():"";

		for(int i=0;i<temp.size();i++){
			if(temp.get(i) instanceof AnnouncementSystem){
				String id = ((AnnouncementSystem)(temp.get(i))).getASid();
				if(!id.equals(currentASid)){
					colorIndex++;
					currentASid = id;
				}
			}
			colorSet[i] = getColor(colorIndex);
		}
		
		dr2.superDraw(label,temp,colorSet, new HowToColor(){
			@Override
			public Color getColor(int index, Color[] colorSet){
				return colorSet[index];
			}
		});
		text.append("Query" + qcount + " " + sql + "\n");
		qcount++;
		return qcount;
	}
	
	private ArrayList<Element> studentAndAS(String sql){
		Connection con = ConnectDB.Connect();
		ArrayList<Element> result = new ArrayList<Element>();
        try{
			con.setAutoCommit(true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()){
	            result.add(new AnnouncementSystem(rs.getInt("ASPOINT.SDO_POINT.X"),rs.getInt("ASPOINT.SDO_POINT.Y"),rs.getInt("RADIUS"), rs.getString("ASID")));
	            result.add(new Student(rs.getInt("PPOINT.SDO_POINT.X"),rs.getInt("PPOINT.SDO_POINT.Y")));
			}
			
        } 
        catch (SQLException e){
			System.out.println("Insertion failed!");
			e.printStackTrace();
		}
        return result;
	}
	
	private Color getColor(int flag)
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
}
