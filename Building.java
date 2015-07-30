
public class Building {
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
}
