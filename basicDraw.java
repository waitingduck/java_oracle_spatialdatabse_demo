import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class BasicDraw {
	
	JLabel label;
	Graphics graphics;
	
	public BasicDraw(JLabel label){
		this.label = label;
		graphics = this.label.getGraphics();
	}
	
	public void draw(int[] x,int[] y, int nn, Color c)  //draw a polygon
	{
		graphics.setColor(c);
		graphics.drawPolygon(x,y,nn);
		//graphics.dispose(); 
	}
	
	public void filleddraw(int[] x,int[] y, int nn, Color c)  //draw a filled polygon
	{
		graphics.setColor(c);
		graphics.fillPolygon(x,y,nn);
		//graphics.dispose(); 
	}
	
	public void draw(int x,int y, int r, Color c)  //draw a circle
	{
		graphics.setColor(c);
		graphics.drawOval(x,y,r,r);
		//graphics.dispose(); 
	}
	
//	public void draw(JLabel label,int[] x,int[] y, int nn, Color c)  //draw a polygon
//	{
//		Graphics graphics = label.getGraphics();
//		graphics.setColor(c);
//		graphics.drawPolygon(x,y,nn);
//		graphics.dispose(); 
//	}
//	
//	public void filleddraw(JLabel label,int[] x,int[] y, int nn, Color c)  //draw a filled polygon
//	{
//		Graphics g = label.getGraphics();
//	    g.setColor(c);
//	    g.fillPolygon(x,y,nn);
//	    g.dispose(); 
//	}
//	
//	public void draw(JLabel label,int x,int y, int r, Color c)  //draw a circle
//	{
//		Graphics g = label.getGraphics();
//	    g.setColor(c);
//	    g.drawOval(x,y,r,r);
//	    g.dispose(); 
//	}
	
	
	
}
