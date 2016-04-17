package Monkey;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import GetRoScoer.GetTop;
import Outlog.log;



public class Monkey_Menu extends JFrame
	implements ActionListener{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public static String text;
		public static String[] monkeyResult;
		public int exitCode = 1;
		
		public JPanel frame1 = new JPanel();
		ArrayList<String> devices = Monkey.getDevices();
		public static String[] monkey;
		
		String DevicesName;
		public static String path;
		static String time =log.time();
//		public static ArrayList<String> thirdApps;
		public static Boolean screenLocked = false;
       
		public Monkey_Menu() throws IOException{
			
		
			frame1.setLayout(null);
			

		    JLabel lable0 = new JLabel("设备名称:");
		    frame1.add(lable0);
		    lable0.setBounds(30, 30, 75, 25);
       
		    final JComboBox<String> box0 = new JComboBox(); 
//		    System.out.println(devices.size());
		    if(this.devices.size()==0){
		    	box0.addItem("设备未连接，请连接设备！");
		    }
		    else{
		    for(int i=0;i<this.devices.size();++i){
		    	box0.addItem((String)this.devices.get(i));
		    }
		    }

	        box0.setBounds(90, 30, 180, 30);  
	        frame1.add(box0); 
		    
		    JLabel lable = new JLabel("应用:");
		    frame1.add(lable);
		    lable.setBounds(400, 30, 45, 25);
		    
		    final JComboBox apps = new JComboBox();
		    apps.addItem("优顾理财");
		    apps.addItem("优顾炒股");
		    apps.setBounds(460,30,180,30);
		    frame1.add(apps);
		   
			 JLabel lable1 = new JLabel("事件数量:");
			 frame1.add(lable1);
			 lable1.setBounds(30, 80, 75, 25);
			    
		     final JTextField JText1 = new JTextField("500");
			 frame1.add(JText1);
			 JText1.setBounds(90, 80, 180, 30);
			 

			 JLabel lable2 = new JLabel("种子数量:");
			 frame1.add(lable2);
			 lable2.setBounds(400, 80,75, 25);
			    
		     final JTextField JText2 = new JTextField("500");
			 frame1.add(JText2);
			 JText2.setBounds(460, 80, 180, 30);
			 
			 
			 
			 JLabel lable6 = new JLabel("随机事件：");
			 frame1.add(lable6);
			 lable6.setBounds(30,150, 75, 25); 
		     final JTextField JText6 = new JTextField("15");
			 frame1.add(JText6);
			 JText6.setBounds(100, 150, 100, 30);
			 
			 

			 JLabel lable4 = new JLabel("点击事件：");
			 frame1.add(lable4);
			 lable4.setBounds(240,150, 75, 25); 
		     final JTextField JText4 = new JTextField("15");
			 frame1.add(JText4);
			 JText4.setBounds(310, 150, 100, 30);
			 

			 JLabel lable5 = new JLabel("滑动事件：");
			 frame1.add(lable5);
			 lable5.setBounds(450,150, 75, 25);
			    
		     final JTextField JText5 = new JTextField("15");
			 frame1.add(JText5);
			 JText5.setBounds(520, 150, 100, 30);
			 
			 
			 JLabel lable7 = new JLabel("轨迹球事件：");
			 frame1.add(lable7);
			 lable7.setBounds(20,220, 85, 25); 
		     final JTextField JText7 = new JTextField("15");
			 frame1.add(JText7);
			 JText7.setBounds(100, 220, 100, 30);
			 
			 

			 JLabel lable8 = new JLabel("导航事件：");
			 frame1.add(lable8);
			 lable8.setBounds(240,220, 75, 25); 
		     final JTextField JText8 = new JTextField("15");
			 frame1.add(JText8);
			 JText8.setBounds(310, 220, 100, 30);
			 

			 JLabel lable9 = new JLabel("页面切换事件：");
			 frame1.add(lable9);
			 lable9.setBounds(440,220, 105, 25);
			    
		     final JTextField JText9 = new JTextField("15");
			 frame1.add(JText9);
			 JText9.setBounds(520, 220, 100, 30);
			 
			  
			 JLabel lable11 = new JLabel("日志路径：");
			 frame1.add(lable11);
			 lable11.setBounds(20, 325, 200, 35);   
		     final JTextField JText11 = new JTextField("/Users/mac/Desktop/monitorToolLog/");
			 frame1.add(JText11);
			 JText11.setBounds(90, 325, 200, 35); 
			 
			final JButton button1 = new JButton("开始测试");		
//			button1.setBounds(400, 325, 200, 35);     //原位置
			button1.setBounds(440, 350, 200, 35);
		    frame1.add(button1);
		
		    JLabel counts = new JLabel("循环次数:");
			frame1.add(counts);
			counts.setBounds(250,372, 75, 25); 
		    final JTextField count_num = new JTextField("1");
			frame1.add(count_num);
			count_num.setBounds(320, 372, 100, 30);
		    
	        //初始化下拉列表框  
	      /*  final JComboBox<String> box = new JComboBox();  
	        box.addItem("1次");  
	        box.addItem("5次"); 
	        box.addItem("10次");
	        box.addItem("20次"); 
	        box.addItem("50次"); 
	        box.setBounds(320, 135, 55, 25);  
	        frame1.add(box); */
		    JLabel lable13 = new JLabel("延时:");
			frame1.add(lable13);
		    lable13.setBounds(300, 325, 200, 35);   
	        //初始化下拉列表框  
	        final JComboBox<String> box = new JComboBox();  
	        box.addItem("0");  
	        box.addItem("0.1秒");  
	        box.addItem("0.5秒"); 
	        box.addItem("1秒");
	        box.addItem("2秒"); 
	        box.setBounds(340, 325, 75, 35);  
	        frame1.add(box); 
	        box.setSelectedIndex(2);;
	        
	        //显示
            final JTextArea txt = new JTextArea ("monkey运行日志："+"\n");
            JScrollPane scroll = new JScrollPane(txt); 
            scroll.setHorizontalScrollBarPolicy( 
            		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setVerticalScrollBarPolicy( 
            		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(30,420,640,452);     
            //textArea.setColumns(1);
            //textArea.setRows(10);
           // txt.setTabSize (30);
            txt.setLineWrap(true);	
            txt.setWrapStyleWord(true); 
            txt.setBounds(30,420,640,452);
            txt.setEditable(false);
            txt.setCaretPosition(8);
            
//            add(txt);
//            frame.add(new JScrollPane(txt));
//            frame.add(txt);
           frame1.add(scroll);
		    
		    
			button1.addActionListener(new ActionListener(){//匿名类实现ActionListener接口
				public void actionPerformed(ActionEvent e)
				{	
					new Thread(new Runnable(){
			            public void run() {
			        		try {
			        			
					String anyevent;
					String touch;
					String motion;
					String trackball;
					String syskeys;
					String appswitch;
					String throttle = null;
					DevicesName =box0.getSelectedItem().toString();
					String PackageName =null;
					
					if(apps.getSelectedItem().toString()=="优顾理财"){
						PackageName = "jhss.youguu.finance";
					}else if(apps.getSelectedItem().toString()=="优顾炒股"){
						PackageName = "com.jhss.youguu";
					}
					
					String zscount=JText2.getText();
					if(JText6.getText().isEmpty())
					{
					   anyevent=JText6.getText();
					}
					else
					{
						 anyevent=" --pct-anyevent "+JText6.getText();
					}
					
					if(JText4.getText().isEmpty())
					{
						touch=JText4.getText();
					}
					else
					{
						 touch=" --pct-touch "+JText4.getText();
					}
			
					if(JText5.getText().isEmpty())
					{
						motion=JText5.getText();
					}
					else
					{
						motion=" --pct-motion "+JText5.getText();
					}

					if(JText7.getText().isEmpty())
					{
						trackball=JText7.getText();
					}
					else
					{
						trackball=" --pct-trackball "+JText7.getText();
					}
			
					if(JText8.getText().isEmpty())
					{
						syskeys=JText8.getText();
					}
					else
					{
						syskeys=" --pct-syskeys "+JText8.getText();
					}
					if(JText9.getText().isEmpty())
					{
						appswitch=JText9.getText();
					}
					else
					{
						appswitch=" --pct-appswitch "+JText9.getText();
					}
					
					if(box.getSelectedItem().equals("0"))
					{
						throttle="";
					}
					if(box.getSelectedItem().equals("0.1秒"))
					{
						throttle="--throttle 100";
					}
					if(box.getSelectedItem().equals("0.5秒"))
					{
						throttle="--throttle 500";
					}
					if(box.getSelectedItem().equals("1秒"))
					{
						throttle="--throttle 1000";
					}
					if(box.getSelectedItem().equals("2次"))
					{
						throttle="--throttle 2000";
					}
					if(devices.size()==0){
						JOptionPane.showMessageDialog(frame1.getRootPane(), "请选择设备", "系统信息", JOptionPane.WARNING_MESSAGE);
					}	
					else if (Monkey.isMonkeyRunning(DevicesName)){
        				JOptionPane.showMessageDialog(frame1.getRootPane(),
        						"monkey已经开始运行了!", "系统信息", JOptionPane.WARNING_MESSAGE);		//警告提示框。
        			}
					
					else{
						String count=JText1.getText();
					    Monkey_Menu.path =JText11.getText();
					    int counts = Integer.parseInt(count_num.getText());
					    txt.setText("monkey运行日志：\n启动时间："+Outlog.log.time()+"\n");
//					    startListen();
					    
					    for(int i=1;i<=counts;++i){

					    	String cmd="adb -s "+DevicesName+" shell monkey -p "+PackageName+ 
					    			" --ignore-crashes --ignore-timeouts" +" --monitor-native-crashes"
					    			+ " -s "+zscount+" "+throttle+" "+anyevent+" "+touch+" "
					    			+motion +" "+trackball +" "+syskeys+" " +appswitch+" -v-v-v "+count;
//					    			+" > "+path+log.time()+".txt";
					    	txt.append("---------------------------\n总共循环"+counts+"次，第"+i+"次循环monkey\n");
					    	System.out.println("start---------------------");
					    	Monkey.getCrash(cmd, PackageName, txt);
					    	
////					    	Outlog.log.writelogs("D:/log/Monkey_log",time,"monkey运行日志：\n总共循环"+counts+"次，第"+i+"次循环monkey\n"+monkey[0]);
//					    	if(monkey[1]!=null){
//					    		Outlog.log.writelogs("D:/log/Crash_log","ygcg","应用crash发生：\n发生时间：\n"+time+"设备名称："+DevicesName+"\n"+text);
//					    	}else{
//					    		System.out.println("执行完成，没有crash.");
//					    	}
					    	
					    }
						
						button1.setBackground(Color.LIGHT_GRAY);
					}
					
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        }).start();
				}});
			
			setTitle("Monkey-----jhss_peter");
			setAutoRequestFocus(true);
//			setBounds(0, 150, 635, 408);
			setBounds(0, 150, 740, 930);
			add(frame1);
			setVisible(true);
			addWindowListener(new WindowAdapter() {
			      public void windowClosing(WindowEvent e) {
			        try {
						exit();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			      }});
			
			
		}
			
		
		
	



		public void actionPerformed(ActionEvent e){

		 
		}

		 public void exit() throws IOException, InterruptedException {
			 
			 if(Monkey.isMonkeyRunning(DevicesName)){
				 Monkey.killMonkey(DevicesName, Monkey.getMonkeyPid(DevicesName));
			 }
			 
			 setVisible(false);
			 
		 }
			

		
		
		
	public static void Monkey() throws Exception{
	

		Thread j= new Thread();	
		j.start();
		Monkey_Menu m=new Monkey_Menu();
		//m.setState(JFrame.ICONIFIED); 
		m.setVisible(true);
		
		
		}
	
	public static void main(String []args) throws Throwable{
		Monkey();
	}
	


}
