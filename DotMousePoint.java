import java.awt.Color;

import javax.swing.JLabel;


public class DotMousePoint implements Element{
	private int[][] centerSquare = new int[2][4];
	
	public DotMousePoint(int x, int y){
		centerSquare[0][0] = x - 2;
		centerSquare[1][0] = y + 2;
    
		centerSquare[0][1] = x - 2;
		centerSquare[1][1] = y - 2;
    
		centerSquare[0][2] = x + 2;
		centerSquare[1][2] = y - 2;
    
		centerSquare[0][3] = x + 2;
		centerSquare[1][3] = y + 2;
	}
	@Override
	public void render(JLabel label, Color c) {
		BasicDraw bd = new BasicDraw(label);
		bd.filleddraw(centerSquare[0],centerSquare[1],4,c);
	}

}
