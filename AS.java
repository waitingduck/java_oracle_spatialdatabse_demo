
public class AS {
	private int xLeftUpper=0;
	private int yLeftUpper=0;
	private int squareRadius=0;
	private int[][] centerSquare = new int[2][4];
	
	public AS(int xCenter,int yCenter,int r){
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
}
