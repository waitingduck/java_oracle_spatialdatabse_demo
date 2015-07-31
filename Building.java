import java.awt.Color;

import javax.swing.JLabel;

public class Building implements element {
	private int[][] xy;
	public Building(int nodeNumber){
		xy = new int[2][nodeNumber];
	}
	public void setNode(int i, int j, int value){
		xy[i][j] = value;
	}
	public int[] getXs(){
		return xy[0];
	}
	public int[] getYs(){
		return xy[1];
	}
	public int getNodeNumber(){
		return xy[0].length;
	}
	public void render(JLabel label, Color c){
		basicDraw dr = new basicDraw();
		dr.draw(label,xy[0],xy[1],xy[0].length,c); 
	}
}
