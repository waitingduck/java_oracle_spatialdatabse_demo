import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextArea;


public class SurroundingStudent extends BasicQuery{
	public int query(JLabel label, int x, int y,JTextArea text,int qcount, String asid)  // use the recorded asid to call the query4b and draw the result(call as4b)
	{
		BasicQuery bq = new BasicQuery();
		Draw dr2 = new Draw();
		String sql = "SELECT S.PPOINT.SDO_POINT.X, S.PPOINT.SDO_POINT.Y FROM ANNOUNCESYS ANS,STUDENT S WHERE SDO_NN(S.PPOINT,ANS.ASPOINT,'sdo_batch_size=0', 1) = 'TRUE' AND SDO_NN_DISTANCE(1)< ANS.RADIUS AND ANS.ASID = '" + asid + "'";
		
		Color[] colorSet = {Color.green};
		dr2.superDraw(label,bq.studentQuery(sql),colorSet);
		
		text.append("Query" + qcount + " " + sql + "\n");
		qcount++;
		return qcount;
	}
}
