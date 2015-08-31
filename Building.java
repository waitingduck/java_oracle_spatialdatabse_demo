import java.awt.Color;

import javax.swing.JLabel;

public class Building implements Element {
	private int[][] xy;
public Building(int nodeNumber, int[][] nodes){
		xy = nodes;
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
		BasicDraw dr = new BasicDraw(label);
		dr.draw(xy[0],xy[1],xy[0].length,c); 
	}
}
