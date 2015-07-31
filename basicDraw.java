import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class basicDraw {
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
