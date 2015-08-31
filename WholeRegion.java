import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextArea;


public class WholeRegion {
	public int query(JLabel label, int[] button,JTextArea text,int qcount)  // normal query call
	{
		BasicQuery bq = new BasicQuery();
		Draw dr2 = new Draw();
		//button[0:AnnouncementSystem, 1:Building, 2: Student]
		if(button[0] == 1){
			String sql = "SELECT ANS.ASID, ANS.ASPOINT.SDO_POINT.X,ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS FROM ANNOUNCESYS ANS";
			Color[] colorSet = {Color.red};
			dr2.superDraw(label,bq.ASQuery(sql),colorSet);
			
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[1] == 1){
			String sql = "SELECT B.NODENUMBER,B.SHAPE.SDO_ORDINATES FROM BUILDING B";
			//dr.building(label,sql,Color.yellow);
			Color[] colorSet= {Color.yellow};
			dr2.superDraw(label, bq.buildingQuery(sql), colorSet);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[2] == 1){
			String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y FROM STUDENT S";
			//dr.student(label,sql,Color.green);
			Color[] colorSet = {Color.green};
			dr2.superDraw(label, bq.studentQuery(sql), colorSet);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		return qcount;
	}
}
