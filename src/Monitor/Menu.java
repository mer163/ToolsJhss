package Monitor;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import GetRoScoer.GetTop;
import Monkey.Monkey_Menu;
import StartTime.StartTime_Menu;



public class Menu extends JFrame implements ActionListener{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//public static final String r1 = null;
	public static String text;
	public static String log;
	
	public JPanel frame = new JPanel();
	 //Checkbox r1 ;
	public Menu() throws IOException{
	
	
	final JButton button2 = new JButton("Monkey");		
	button2.setBounds(80, 505, 100, 35);   
    frame.add(button2);
	
    final JButton button3 = new JButton("启动耗时");		
	button3.setBounds(260, 505, 100, 35);  
    frame.add(button3);
    
	
	frame.setLayout(null);
	final CpuChart rtcp=new CpuChart("CPU","CPU","%");  
	 rtcp.setBounds(0, 40, 600, 200); 
	frame.add(rtcp);
 
    
    final HeapChart rtcp1=new HeapChart("Memory","Memory","kb");  
    rtcp1.setBounds(605, 40, 600, 200); 
	frame.add(rtcp1); 

    	
    final FlowChart rtcp2=new FlowChart("Flow","Flow","Kb/s");  
	//rtcp2.setSize(620, 200);
    rtcp2.setBounds(0, 260, 600, 200); 
	frame.add(rtcp2);		    
  	
    
    final BatteryChart rtcp3=new BatteryChart("Battery","Battery","%");  
//	rtcp3.setSize(620, 200);
	rtcp3.setBounds(605, 260, 600, 200);  
	frame.add(rtcp3);		    
		 
  
	JLabel lable = new JLabel("包名:");
    frame.add(lable);
    lable.setBounds(520, 510, 45, 25);


    final JComboBox<String> box = new JComboBox();  
    box.addItem("com.jhss.youguu");
    box.addItem("jhss.youguu.finance");
    box.setBounds(580, 510, 180, 30);
    frame.add(box);
	    
	final JButton button1 = new JButton("开始监控");		
	button1.setBounds(860, 505, 200, 35);  

    frame.add(button1);
    
    final Checkbox chk = new Checkbox("Log", true);
    chk.setBounds(800, 518, 105, 15); 
    //r1.setSize(205, 100);
    frame.add(chk);
    
	button1.addActionListener(new ActionListener(){//匿名类实现ActionListener接口
		public void actionPerformed(ActionEvent e)
		{	
								
			if (chk.getState()){
				log="1";
			}
			else{
			 System.out.print("不要日志");
			}
			
			text =box.getSelectedItem().toString();  //获取所选择的包名。
			
			if(text.isEmpty())
		    {
		    	JOptionPane.showMessageDialog(new JFrame(), "伙计，包名不要为空好么？");
		    }
			
			else if (button1.getText().equals("开始监控")){
										
				try {
					if(GetTop.cpu(text)==-0.1)
					{      		
					JOptionPane.showMessageDialog(new JFrame(), "请检查设备是否连接,或者你的设备没有连接好,也可能是你的包名不正确！");

					}
					
					else
					{
						   // System.out.print(text);
//								    button1.setBackground(Color.LIGHT_GRAY);
							button1.setText("停止监控");
						    	//启动四个线程捕抓信息。
							  (new Thread(rtcp)).start(); 
							  (new Thread(rtcp1)).start(); 
							  (new Thread(rtcp2)).start(); 
							  (new Thread(rtcp3)).start(); 
																
					}
				} catch (HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			else if (button1.getText().equals("停止监控")) {
				rtcp.stop=true;
				rtcp1.stop=true;
				rtcp2.stop=true;
				rtcp3.stop=true;
				button1.setText("开始监控");
			}
		}   
		
		});
	
	//monkey按钮绑定监听器
	button2.addActionListener(new ActionListener(){//匿名类实现ActionListener接口
		public void actionPerformed(ActionEvent e)
		{	
		

			try {
				Monkey_Menu.Monkey();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//m.setVisible(true);
		}});
	
	//启动耗时按钮 绑定监听器
	button3.addActionListener(new ActionListener(){//匿名类实现ActionListener接口
		public void actionPerformed(ActionEvent e)
		{	
			StartTime_Menu m = null;
			try {
				m = new StartTime_Menu();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//m.setVisible(true);
			
		}});
	
   
	setTitle("jhss_peter Vesion 1.1");
	setBounds(660, 150, 1249, 622);
	add(frame);
	setVisible(true);
	addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        exit();
	      }});

	}

	public void actionPerformed(ActionEvent e){

	 
	}

	public void exit() {
		    Object[] options = { "确定", "取消" };
		    JOptionPane pane2 = new JOptionPane("真想退出吗?", JOptionPane.QUESTION_MESSAGE,
		    JOptionPane.YES_NO_OPTION, null, options, options[1]);
		    JDialog dialog = pane2.createDialog(this, "警告");
		    dialog.setVisible(true);
		    Object selectedValue = pane2.getValue();
		    if (selectedValue == null || selectedValue == options[1]) {
		      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 这个是关键
		    } else if (selectedValue == options[0]) {
		      setDefaultCloseOperation(EXIT_ON_CLOSE);
		    }
		  }
	
	public static void main(String []args) throws Exception{
		Menu m=new Menu();
		m.setVisible(true);
		}
}
