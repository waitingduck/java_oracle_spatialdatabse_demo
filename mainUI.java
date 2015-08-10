
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
	private int QueryCount = 1;  //record current query number
	private int pointx = 0;  // record current mouse point
	private int pointy = 0;  
	int[] pxarr = new int[4]; //to draw the square on the map
    int[] pyarr = new int[4];
    private String polygon = "";
    private String temppolygon = "";
    private Queue<Integer> q=new LinkedList<Integer>();;
    private int NodeCount = 0;
    private boolean painted = true;
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
		
		frame = new JFrame("CHENG, WEI-TING  8181471882");
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
		
		final query query = new query();
		
///////////////////////CheckBox/////////////////////////////////////////////////////////////////////////
		String[] checkBox = {"AS", "Buildings", "Students"};
		
		for(int i=0;i<checkBox.length;i++){
			final JCheckBox cb = new JCheckBox(checkBox[i]);
			final int checkButtonIndex = i;
			cb.setBounds(846, 10+i*50, 132, 49);
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
		String[] radioButton = {"Whole Region","Point Query","Range Query","Surrounding Student","Emergency Query"};
		for(int i=0;i<radioButton.length;i++){
			final JRadioButton rb = new JRadioButton(radioButton[i]);
			final int radioButtonIndex = i+1;
			rb.setBounds(846, 196+i*50, 132, 49);
			rb.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	if(rb.isSelected())
	            	{
	            		label.repaint();
	            		button[3] = radioButtonIndex;
	            		polygon = "";
	            		temppolygon = "";
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
            		case 1: QueryCount = query.query1(label,button,sqlTextArea,QueryCount);
            		        break;
            		        
            		case 2: QueryCount = query.query2(label, button, pointx, pointy,sqlTextArea,QueryCount);
            		        break;
            		        
            		case 3: QueryCount = query.query3(label, button, pointx, pointy,sqlTextArea,QueryCount,polygon);
            		        break;
            		        
            		case 4: QueryCount = query.query4b(label, pointx, pointy,sqlTextArea,QueryCount);
            		        break;
            		        
            		case 5: QueryCount = query.query5(label, pointx, pointy,sqlTextArea,QueryCount);
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
					draw dr = new draw();
					
					System.out.println("[" + e.getX() + "," + e.getY() + "]");
					pointx = e.getX();
					pointy = e.getY();
					
					pxarr[0] = pointx - 2;
					pyarr[0] = pointy + 2;
		            
					pxarr[1] = pointx - 2;
					pyarr[1] = pointy - 2;
		            
					pxarr[2] = pointx + 2;
					pyarr[2] = pointy - 2;
		            
					pxarr[3] = pointx + 2;
					pyarr[3] = pointy + 2;
		            
					//need to schedule otherwise will have collision with previous repaint function
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						  @Override
						  public void run() {
							  dr.filleddraw(label,pxarr,pyarr,4,Color.red);
								dr.draw(label, pointx-50, pointy-50, 100, Color.red);
						  }
						}, 30);
					
//					dr.filleddraw(label,pxarr,pyarr,4,Color.red);
//					dr.draw(label, pointx-50, pointy-50, 100, Color.red);
					}
				}
				else if(button[3] == 3)// draw a polygon,and record the polygon in temppolygon which will be cleared after click right mouse button
				{
					if(SwingUtilities.isLeftMouseButton(e))
					{
				    
						draw dr = new draw();
						if(painted){
							label.repaint();
							painted = false;
						}
						System.out.println("[" + e.getX() + "," + e.getY() + "]");
						pointx = e.getX();
						pointy = e.getY();
						if(temppolygon == "")
						{
							temppolygon = (temppolygon + " " + pointx +",  " + pointy);
						}
						else
						{
							temppolygon = (temppolygon + " , " + pointx +",  " + pointy);
						}
						
						
						q.offer(pointx);
						q.offer(pointy);
						NodeCount++;
					
						pxarr[0] = pointx - 2;
						pyarr[0] = pointy + 2;
		            
						pxarr[1] = pointx - 2;
						pyarr[1] = pointy - 2;
		            
						pxarr[2] = pointx + 2;
						pyarr[2] = pointy - 2;
		            
						pxarr[3] = pointx + 2;
						pyarr[3] = pointy + 2;
		            
					
						Timer timer = new Timer();
						timer.schedule(new TimerTask() {
							  @Override
							  public void run() {
								  dr.filleddraw(label,pxarr,pyarr,4,Color.red);
							  }
							}, 30);
						
//						dr.filleddraw(label,pxarr,pyarr,4,Color.red);
						System.out.println(temppolygon);
					}
					if(SwingUtilities.isRightMouseButton(e))  //transfer the tempolygon to polygon and add the first node to it in order to close it.  
					{                                         // then clear the temppolygon
				    
						draw dr = new draw();
						painted = true;
						int[] xarr = new int[NodeCount]; 
					    int[] yarr = new int[NodeCount];
					    int i = 0;
					    while(!q.isEmpty())
					    {
					    	xarr[i] = q.poll();
					    	yarr[i] = q.poll();
					    	i++;
					    }
						dr.draw(label,xarr,yarr,NodeCount,Color.red);
						NodeCount = 0;
						polygon = temppolygon + "," + xarr[0] +",  " + yarr[0];
						temppolygon = "";
					}
				}
				else if(button[3] == 4 || button[3] == 5) //directly call query4a to draw the nearest AS,used by query4 and query5
				{
					if(SwingUtilities.isLeftMouseButton(e))
					{
				    
					draw dr = new draw();
					
					System.out.println("[" + e.getX() + "," + e.getY() + "]");
					pointx = e.getX();
					pointy = e.getY();
					
					pxarr[0] = pointx - 2;
					pyarr[0] = pointy + 2;
		            
					pxarr[1] = pointx - 2;
					pyarr[1] = pointy - 2;
		            
					pxarr[2] = pointx + 2;
					pyarr[2] = pointy - 2;
		            
					pxarr[3] = pointx + 2;
					pyarr[3] = pointy + 2;
		            
					
					dr.filleddraw(label,pxarr,pyarr,4,Color.red);
					QueryCount = query.query4a(label, pointx, pointy,sqlTextArea,QueryCount);
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
