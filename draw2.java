import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;

public class draw2 {
	
	public void superDraw(JLabel label, ArrayList<element> list, Color[] colorSet){
		superDraw(label,list,colorSet, new howToColor(){
			@Override
			public Color getColor(int index, Color[] colorSet){
				return colorSet[0];
			}
		});
	}
	
	public void superDraw(JLabel label, ArrayList<element> list, Color[] colorSet, howToColor htc){
		for(element obj:list){
			obj.render(label,htc.getColor(list.indexOf(obj), colorSet));
		}
	}
	
//	public void drawASList(JLabel label, ArrayList<AS> ASs, Color[] colorSet){
//		drawASList(label,ASs,colorSet, new howToColor(){
//			@Override
//			public Color getColor(int index, Color[] colorSet){
//				return colorSet[0];
//			}
//		});
//	}
//	public void drawBuildingList(JLabel label, ArrayList<Building> buildings, int nodeNumber, Color[] colorSet){
//		drawBuildingList(label,buildings,colorSet,new howToColor(){
//			@Override
//			public Color getColor(int index, Color[] colorSet){
//				return colorSet[0];
//			}
//		});
//	}
//	public void drawStudentList(JLabel label, ArrayList<Student> students, Color[] colorSet){
//		drawStudentList(label,students,colorSet,new howToColor(){
//			@Override
//			public Color getColor(int index, Color[] colorSet){
//				return colorSet[0];
//			}
//		});
//	}
//	
//    public void drawASList(JLabel label, ArrayList<AS> ASs, Color[] colorSet, howToColor htc){
//		for(AS as:ASs){
//            draw(label, as.getXLU(), as.getYLU(), as.getSR(), htc.getColor(ASs.indexOf(as), colorSet));  
//            filleddraw(label,as.getXs(),as.getYs(),4,htc.getColor(ASs.indexOf(as), colorSet));
//		}
//	}
//    public void drawBuildingList(JLabel label, ArrayList<Building> buildings,Color[] colorSet, howToColor htc){
//		for(Building building:buildings){
//			draw(label,building.getXs(),building.getYs(),building.getNodeNumber(),htc.getColor(buildings.indexOf(building), colorSet)); 
//		}
//	}
//	public void drawStudentList(JLabel label, ArrayList<Student> students,Color[] colorSet, howToColor htc){
//		for(Student student:students){
//			filleddraw(label,student.getXs(),student.getYs(),4,htc.getColor(students.indexOf(student), colorSet));  
//		}
//	}
	
//	public void draw(JLabel label,int[] x,int[] y, int nn, Color c)  //draw a polygon
//	{
//		Graphics g = label.getGraphics();
//	    g.setColor(c);
//	    g.drawPolygon(x,y,nn);
//	    g.dispose(); 
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
//	
//	public Color getColor(int flag)
//	{
//		Color c = Color.black;
//		switch(flag)
//		{
//			case 0:
//				c = Color.blue;
//				break;
//			case 1:
//				c = Color.cyan;
//				break;
//			case 2:
//				c = Color.green;
//				break;
//			case 3:
//				c = Color.yellow;
//				break;
//			case 4:
//				c = Color.white;
//				break;
//			case 5:
//				c = Color.orange;
//				break;
//			case 6:
//				c = Color.pink;
//				break;
//		}
//		return c;
//	}
//	public int checkAS(String[] ass, String as)
//	{
//		int position = 0;
//		
//		for(int i = 0; i < 7; i++)
//		{
//			position = i;
//			if(ass[i].equals(as))
//			{
//				break;
//			}
//		}
//		return position;
//	}
	
}
