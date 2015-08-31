import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;


public class NearstAS extends BasicQuery{
	public String query(JLabel label, int x, int y)  //call as4a to darw the nearest AS and record the ASID in global variable asid(call as4a)
	{
		Draw dr2 = new Draw();
		BasicQuery bq = new BasicQuery();
		String sql = "SELECT ANS.ASPOINT.SDO_POINT.X, ANS.ASPOINT.SDO_POINT.Y, ANS.RADIUS,ANS.ASID FROM ANNOUNCESYS ANS WHERE SDO_NN(ANS.ASPOINT,  sdo_geometry(2001, NULL, sdo_point_type(" + x + "," + y + ",NULL), NULL,  NULL),'sdo_num_res=1', 1) = 'TRUE' ";
		
		Color[] colorSet = {Color.red};
		ArrayList<Element> temp = bq.ASQuery(sql);
		String asid = ((AnnouncementSystem)(temp.get(0))).getASid();
		dr2.superDraw(label, temp, colorSet);
		return asid;
	}
	
	
}
