
import java.awt.Color;


import javax.swing.JLabel;
import javax.swing.JTextArea;


public class query {
	String asid;
	public int query1(JLabel label, int[] button,JTextArea text,int qcount)  // normal query call
	{
		draw dr = new draw();
		if(button[0] == 1)
		{
			String sql = "SELECT ANS.ASPOINT.SDO_POINT.X,ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS FROM ANNOUNCESYS ANS";
			dr.as(label,sql,Color.red);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[1] == 1)
		{
			String sql = "SELECT B.NODENUMBER,B.SHAPE.SDO_ORDINATES FROM BUILDING B";
			dr.building(label,sql,Color.yellow);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[2] == 1)
		{
			String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y FROM STUDENT S";
			dr.student(label,sql,Color.green);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		return qcount;
	}
	
	public int query2(JLabel label, int[] button, int x, int y,JTextArea text,int qcount) //normal query call(call as2,building2,student2)
	{
		draw dr = new draw();
		if(button[0] == 1)
		{
			String sql = "SELECT ANS.ASPOINT.SDO_POINT.X, ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS, SDO_NN_DISTANCE(1) FROM ANNOUNCESYS ANS WHERE SDO_NN(ANS.ASPOINT,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)<50 ORDER BY SDO_NN_DISTANCE(1)";
			dr.as2(label,sql,Color.green);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[1] == 1)
		{
			String sql = "SELECT B.NODENUMBER,B.SHAPE.SDO_ORDINATES, SDO_NN_DISTANCE(1) " + 
		                 "FROM BUILDING B " + 
			             "WHERE SDO_NN(B.SHAPE,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)<50 ORDER BY SDO_NN_DISTANCE(1)";
			dr.building2(label,sql,Color.green);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[2] == 1)
		{
			String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y, SDO_NN_DISTANCE(1) FROM STUDENT S WHERE SDO_NN(S.PPOINT,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)<50 ORDER BY SDO_NN_DISTANCE(1)";
			dr.student2(label,sql,Color.green);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		return qcount;
	}
	
	public int query3(JLabel label, int[] button, int x, int y,JTextArea text,int qcount,String polygon)  // one more varibale polygon that indicate the polygon user draw, then draw features according the button
	{
		draw dr = new draw();
		if(button[0] == 1)
		{
			String sql = "SELECT ANS.ASPOINT.SDO_POINT.X, ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS FROM ANNOUNCESYS ANS WHERE SDO_ANYINTERACT(ANS.ASPOINT,SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(" + polygon + ")))= 'TRUE'";
            
			dr.as(label,sql,Color.red);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[1] == 1)
		{
			String sql = "SELECT B.NODENUMBER,B.SHAPE.SDO_ORDINATES FROM BUILDING B WHERE SDO_ANYINTERACT(B.SHAPE,SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(" + polygon + ")))= 'TRUE'";
			dr.building(label,sql,Color.yellow);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		if(button[2] == 1)
		{
			String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y FROM STUDENT S WHERE SDO_ANYINTERACT(S.PPOINT,SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(" + polygon + ")))= 'TRUE'";
			dr.student(label,sql,Color.green);
			text.append("Query" + qcount + " " + sql + "\n");
			qcount++;
		}
		return qcount;
	}
	public int query4a(JLabel label, int x, int y,JTextArea text,int qcount)  //call as4a to darw the nearest AS and record the ASID in global variable asid(call as4a)
	{
		draw dr = new draw();
		String sql = "SELECT ANS.ASPOINT.SDO_POINT.X, ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS,ANS.ASID FROM ANNOUNCESYS ANS WHERE SDO_NN(ANS.ASPOINT,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_num_res=1', 1) = 'TRUE' ";
		asid = dr.as4a(label,sql,Color.red);
		text.append("Query" + qcount + " " + sql + "\n");
		qcount++;
		return qcount;
	}
	public int query4b(JLabel label, int x, int y,JTextArea text,int qcount)  // use the recorded asid to call the query4b and draw the result(call as4b)
	{
		draw dr = new draw();
		String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y, ANS.ASPOINT.SDO_POINT.X, ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS FROM ANNOUNCESYS ANS,STUDENT S WHERE SDO_NN(S.PPOINT,ANS.ASPOINT,'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)< ANS.RADIUS AND ANS.ASID = '" + asid + "'";
		dr.as4b(label,sql);
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
}
