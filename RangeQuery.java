import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextArea;


public class RangeQuery extends BasicQuery{
	public int query(JLabel label, int[] button, int x, int y,JTextArea text,int qcount,String polygon)  // one more varibale polygon that indicate the polygon user draw, then draw features according the button
	{
		Draw dr2 = new Draw();
		BasicQuery bq = new BasicQuery();
		//button[0:AnnouncementSystem, 1:Building, 2: Student]
		if(button[0] == 1){
			String sql = "SELECT ANS.ASID, ANS.ASPOINT.SDO_POINT.X, ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS FROM ANNOUNCESYS ANS WHERE SDO_ANYINTERACT(ANS.ASPOINT,SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(" + polygon + ")))= 'TRUE'";
            
			Color[] colorSet = {Color.red};
			dr2.superDraw(label,bq.ASQuery(sql),colorSet);
			
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[1] == 1){
			String sql = "SELECT B.NODENUMBER,B.SHAPE.SDO_ORDINATES FROM BUILDING B WHERE SDO_ANYINTERACT(B.SHAPE,SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(" + polygon + ")))= 'TRUE'";
	
			
			Color[] colorSet = {Color.yellow};
			dr2.superDraw(label,bq.buildingQuery(sql),colorSet);
			
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[2] == 1){
			String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y FROM STUDENT S WHERE SDO_ANYINTERACT(S.PPOINT,SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(" + polygon + ")))= 'TRUE'";

			
			Color[] colorSet = {Color.green};
			dr2.superDraw(label,bq.studentQuery(sql),colorSet);
			
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		return qcount;
	}
}
