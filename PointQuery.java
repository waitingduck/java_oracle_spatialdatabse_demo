import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextArea;


public class PointQuery {
	public int query(JLabel label, int[] button, int x, int y,JTextArea text,int qcount) //normal query call(call as2,building2,student2)
	{
		Draw dr2 = new Draw();
		BasicQuery bq = new BasicQuery();
		
		//button[0:AnnouncementSystem, 1:Building, 2: Student]
		if(button[0] == 1){
			String sql = "SELECT ANS.ASID, ANS.ASPOINT.SDO_POINT.X, ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS, SDO_NN_DISTANCE(1) FROM ANNOUNCESYS ANS WHERE SDO_NN(ANS.ASPOINT,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)<50 ORDER BY SDO_NN_DISTANCE(1)";
			//dr.as2(label,sql,Color.green);
			Color[] colorSet = {Color.yellow,Color.green};
			
			dr2.superDraw(label,bq.ASQuery(sql),colorSet, new HowToColor(){
				@Override
				public Color getColor(int index, Color[] colorSet){
					return (index==0)? colorSet[0]:colorSet[1];
				}
			});
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[1] == 1){
			String sql = "SELECT B.NODENUMBER,B.SHAPE.SDO_ORDINATES, SDO_NN_DISTANCE(1) " + 
		                 "FROM BUILDING B " + 
			             "WHERE SDO_NN(B.SHAPE,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)<50 ORDER BY SDO_NN_DISTANCE(1)";
			//dr.building2(label,sql,Color.green);
			
			Color[] colorSet = {Color.yellow,Color.green};
			
			dr2.superDraw(label,bq.buildingQuery(sql),colorSet, new HowToColor(){
				@Override
				public Color getColor(int index, Color[] colorSet){
					return (index==0)? colorSet[0]:colorSet[1];
				}
			});
			
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[2] == 1){
			String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y, SDO_NN_DISTANCE(1) FROM STUDENT S WHERE SDO_NN(S.PPOINT,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)<50 ORDER BY SDO_NN_DISTANCE(1)";

			
			Color[] colorSet = {Color.yellow,Color.green};
			
			dr2.superDraw(label,bq.studentQuery(sql),colorSet, new HowToColor(){
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
}
