
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class mainUI extends JFrame{
	private JFrame frame;
	private JLabel label;
	private JTextArea sqlTextArea;  //textarea which show the sql query
	private JPanel panel;
	private int[] button = {0,0,0,0};  //record current button 
	private int queryCount = 1;  //record current query number
	
	private int pointX = 0;  // record current mouse point
	private int pointY = 0;  
	int[] pointXArr = new int[4]; //to draw the square on the map
    int[] pointYArr = new int[4];
    private String polygon = "";
    private String tempPolygon = "";
    private Queue<Integer> q =new LinkedList<Integer>();;
    private int nodeCount = 0;
    private boolean painted = true;
    private String nearstASid = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainUI window = new mainUI();
					window.frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ButtonGroup Bgroup = new ButtonGroup();
		
		frame = new JFrame("Spatial database demo");
		frame.setBounds(0, 0, 1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		ImageIcon background = new ImageIcon("src/image/map.jpg");
		label = new JLabel();
		label.setBounds(0, 0, 820, 580);
		label.setIcon(background);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(label);
		panel.setBounds(10, 10, 830, 590);
		frame.getContentPane().add(panel);
		
		
///////////////////////CheckBox/////////////////////////////////////////////////////////////////////////
//user can choose what kind of element will show on the map
//button selection will be recorded in array button[0:AS, 1:Buildings, 2:Student]
		String[] checkBox = {"<html> Announcement <br> System</html>", "Buildings", "Students"};
		
		for(int i=0;i<checkBox.length;i++){
			JCheckBox cb = new JCheckBox(checkBox[i]);
			int checkButtonIndex = i;
			cb.setBounds(846, 10+i*50, 132, 50);
			cb.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	//label.repaint();
	            	if(cb.isSelected()){
	            		button[checkButtonIndex] = 1;
	            	}
	            	else{
	            		button[checkButtonIndex] = 0;
	            	}
	            }
	        });
			frame.getContentPane().add(cb);
		}

///////////////////////////RadioButton////////////////////////////////////////////////////////////////////////////////
//different interaction mode with exclusive radio button. 
//button will be recored in array button[3:the index of the choice]
		String[] radioButton = {"Whole Region","Point Query","Range Query","Surrounding Student","Emergency Query"};
		for(int i=0;i<radioButton.length;i++){
			JRadioButton rb = new JRadioButton(radioButton[i]);
			int radioButtonIndex = i+1;
			rb.setBounds(846, 196+i*50, 180, 49);
			rb.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	if(rb.isSelected())
	            	{
	            		label.repaint();
	            		button[3] = radioButtonIndex;
	            		polygon = "";
	            		tempPolygon = "";
	            		painted = true;
	            	}
	            }
	        });
			frame.getContentPane().add(rb);
			Bgroup.add(rb);
		}

//////////////////////////submit button!/////////////////////////////////////////////////////////////////
		JButton btnNewButton = new JButton("Submit Query");
		btnNewButton.setBounds(850, 507, 185, 93);
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	switch(button[3]){
            		case 1: queryCount = new WholeRegion().query(label, button, sqlTextArea, queryCount);
            		        break;
            		        
            		case 2: queryCount = new PointQuery().query(label, button, pointX, pointY, sqlTextArea, queryCount);
            		        break;
            		        
            		case 3: queryCount = new RangeQuery().query(label, button, pointX, pointY, sqlTextArea, queryCount,polygon);
            		        break;
            		        
            		case 4: queryCount = new SurroundingStudent().query(label, pointX, pointY, sqlTextArea, queryCount,nearstASid);
            		        break;
            		        
            		case 5: queryCount = new EmergencyQuery().query(label, pointX, pointY, sqlTextArea, queryCount, nearstASid);
            			    break;
            	}
            }
        });
		frame.getContentPane().add(btnNewButton);
		
//////////////////////////////TextArea/////////////////////////////////////////////////////////////////////////////
		final JTextArea XYTextArea = new JTextArea();
		XYTextArea.setBounds(850, 455, 185, 42);
		XYTextArea.setEditable(false);
		frame.getContentPane().add(XYTextArea);
		
		sqlTextArea = new JTextArea();
		sqlTextArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(sqlTextArea);
		scrollPane.setBounds(10, 600, 830, 60);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);

//////////////////////////////mouseListener/////////////////////////////////////////////////////////////////////////////
		panel.addMouseMotionListener(new MouseMotionAdapter(){
		   public void mouseMoved(MouseEvent e){
		    int x=e.getX(); 
		    int y=e.getY(); 
		    String s="  " + x + ","  + y ;
		    XYTextArea.setText(s);     
		   }
		   
		  });
		
		panel.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{}

			@Override
			public void mousePressed(MouseEvent e) {
				if(button[3] == 2){
					//point query. draw a point and a range of it
					
					if(SwingUtilities.isLeftMouseButton(e)){
					label.repaint();
					
					System.out.println("[" + e.getX() + "," + e.getY() + "]");
					pointX = e.getX();
					pointY = e.getY();

         
					//need to schedule otherwise will have collision with previous repaint function
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						  @Override
						  public void run() {
							  new CircleMousePoint(pointX,pointY).render(label,Color.red);
						  }
						}, 30);
					}
				}
				else if(button[3] == 3)// draw a polygon,and record the polygon in temppolygon which will be cleared after click right mouse button
				{
					if(SwingUtilities.isLeftMouseButton(e)){
				    
						if(painted){
							label.repaint();
							painted = false;
						}
						System.out.println("[" + e.getX() + "," + e.getY() + "]");
						pointX = e.getX();
						pointY = e.getY();
						if(tempPolygon == ""){
							tempPolygon = (tempPolygon + " " + pointX +",  " + pointY);
						}
						else{
							tempPolygon = (tempPolygon + " , " + pointX +",  " + pointY);
						}
						
						q.offer(pointX);
						q.offer(pointY);
						nodeCount++;

						Timer timer = new Timer();
						timer.schedule(new TimerTask() {
							  @Override
							  public void run() {
								  new DotMousePoint(pointX, pointY).render(label,Color.red);
							  }
							}, 30);
						System.out.println(tempPolygon);
					}
					if(SwingUtilities.isRightMouseButton(e))  //transfer the tempolygon to polygon and add the first node to it in order to close it.  
					{                                         // then clear the temppolygon
				    
						BasicDraw draw = new BasicDraw(label);
						painted = true;
						int[] xarr = new int[nodeCount]; 
					    int[] yarr = new int[nodeCount];
					    int i = 0;
					    while(!q.isEmpty())
					    {
					    	xarr[i] = q.poll();
					    	yarr[i] = q.poll();
					    	i++;
					    }
						draw.draw(xarr,yarr,nodeCount,Color.red);
						nodeCount = 0;
						polygon = tempPolygon + "," + xarr[0] +",  " + yarr[0];
						tempPolygon = "";
					}
				}else if(button[3] == 4 || button[3] == 5) {//directly call query4a to draw the nearest AS,used by query4 and query5
					label.repaint();
					if(SwingUtilities.isLeftMouseButton(e)){
						System.out.println("[" + e.getX() + "," + e.getY() + "]");
						pointX = e.getX();
						pointY = e.getY();
						
						Timer timer = new Timer();
						timer.schedule(new TimerTask() {
							  @Override
							  public void run() {
								  new DotMousePoint(pointX, pointY).render(label, Color.red);
								  nearstASid = new NearstAS().query(label, pointX, pointY);
							  }
							}, 30);
						
						}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{}

			@Override
			public void mouseEntered(MouseEvent e) 
			{}

			@Override
			public void mouseExited(MouseEvent e) 
			{}
		});
		
		
	}
	
	
}
