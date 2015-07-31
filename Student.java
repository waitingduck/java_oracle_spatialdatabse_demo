import java.awt.Color;

import javax.swing.JLabel;

public class Student implements element {
	private int[][] centerSquare = new int[2][4];
	public Student(int xCenter, int yCenter){
        centerSquare[0][0] = xCenter - 5;
        centerSquare[1][0] = yCenter + 5;
        
        centerSquare[0][1] = xCenter - 5;
        centerSquare[1][1] = yCenter - 5;
        
        centerSquare[0][2] = xCenter + 5;
        centerSquare[1][2] = yCenter - 5;
        
        centerSquare[0][3] = xCenter + 5;
        centerSquare[1][3] = yCenter + 5;
	}
	public int[] getXs(){
		return centerSquare[0];
	}
	public int[] getYs(){
		return centerSquare[1];
	}
	public void render(JLabel label, Color c){
		basicDraw dr = new basicDraw();
		dr.filleddraw(label,centerSquare[0],centerSquare[1],4,c);  
	}
}
