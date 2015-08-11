import java.awt.Color;

import javax.swing.JLabel;

public class AS implements element {
	private int xLeftUpper=0;
	private int yLeftUpper=0;
	private int squareRadius=0;
	private int[][] centerSquare = new int[2][4];
	private String ASID = "";
	
	public AS(int xCenter,int yCenter,int r, String asid){
		xLeftUpper = xCenter-r;
		yLeftUpper = yCenter-r;
		squareRadius = r*2;
		
		centerSquare[0][0] = xCenter-7;
		centerSquare[1][0] = yCenter+7;
        
		centerSquare[0][1] = xCenter-7;
		centerSquare[1][1] = yCenter-7;
        
		centerSquare[0][2] = xCenter+7;
		centerSquare[1][2] = yCenter-7;
        
		centerSquare[0][3] = xCenter+7;
		centerSquare[1][3] = yCenter+7;
		
		ASID = asid;
		//System.out.println("Create new AS with x: " + xCenter + ", y: " + yCenter + ", r: " + r);
	}
	public int getXLU(){
		return xLeftUpper;
	}
	public int getYLU(){
		return yLeftUpper;
	}
	public int getSR(){
		return squareRadius;
	}
	public int[] getXs(){
		return centerSquare[0];
	}
	public int[] getYs(){
		return centerSquare[1];
	}
	public String getASid(){
		return ASID;
	}
	public void render(JLabel label, Color c){
		basicDraw dr = new basicDraw();
		dr.draw(label, xLeftUpper, yLeftUpper, squareRadius, c);  
        dr.filleddraw(label,centerSquare[0],centerSquare[1],4,c);
	}
}
