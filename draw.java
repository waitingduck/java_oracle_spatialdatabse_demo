import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Draw {
	
	public void superDraw(JLabel label, ArrayList<Element> list, Color[] colorSet){
		superDraw(label,list,colorSet, new HowToColor(){
			@Override
			public Color getColor(int index, Color[] colorSet){
				return colorSet[0];
			}
		});
	}
	
	public void superDraw(JLabel label, ArrayList<Element> list, Color[] colorSet, HowToColor htc){
		for(Element obj:list){
			obj.render(label,htc.getColor(list.indexOf(obj), colorSet));
		}
	}
}
