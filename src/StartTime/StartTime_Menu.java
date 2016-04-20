package StartTime;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
import Monitor.Menu;
import Monkey.Monkey;
import Monkey.Monkey_Menu;
import Outlog.log;

/**
 * 测试启动耗时主界面
 * @author mac
 *
 */

public class StartTime_Menu extends JFrame
	implements ActionListener{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public static String text;
		public JPanel frame = new JPanel();
		ArrayList<String> devices = Monkey.getDevices();
		static String time =Outlog.log.time();
		String path = Monkey_Menu.path; //获取页面上的日志路径。
		
		public StartTime_Menu() throws IOException{

			frame.setLayout(null);
            final JTextArea txt = new JTextArea ("以下为详细内容："+"\n");
            JScrollPane scroll = new JScrollPane(txt); 
            scroll.setHorizontalScrollBarPolicy( 
            		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setVerticalScrollBarPolicy( 
            		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
            //textArea.setColumns(1);
            //textArea.setRows(10);
           // txt.setTabSize (30);
            txt.setLineWrap(true);
            txt.setWrapStyleWord(true);  
            txt.setBounds(10,10,600,452);
            add(txt);
     
            frame.add(txt);
		    JLabel lable = new JLabel("包名:");
		    frame.add(lable);
		    lable.setBounds(70, 510, 45, 25);
		    
		    final JTextField JText = new JTextField("com.jhss.youguu");
		    frame.add(JText);
		    JText.setBounds(130, 510, 180, 30);
		   
			 JLabel lable1 = new JLabel("启动Activity名:");
			 frame.add(lable1);
			 lable1.setBounds(15, 550, 95, 25);
			    
		     final JTextField JText1 = new JTextField(".SplashActivity");
			 frame.add(JText1);
			 JText1.setBounds(130, 550, 180, 30);
		   		    
			final JButton button1 = new JButton("开始测试");		
			button1.setBounds(450, 530, 150, 35);   
		    frame.add(button1);
		
	        //初始化下拉列表框  
	        final JComboBox<String> box = new JComboBox();  
	        box.addItem("1次");  
	        box.addItem("5次"); 
	        box.addItem("10次");
	        box.addItem("20次"); 
	        box.addItem("50次"); 
	        box.setBounds(320, 550, 120, 25);  
	        frame.add(box); 
	      
	        final JComboBox<String> box1 = new JComboBox();  
	        if(devices==null){
	        	box1.addItem("未发现设备");
	        }else {
	        	for(String s:devices){
		        	box1.addItem(s);
		        }
	        }
	        
//	        for (int x=0;x<devices.size()-1;++x){
//	        box1.addItem(devices.get(x));  
//	        box1.addItem("aaa");  
//	        box1.addItem("全部shebe"); 
//	        }
	        box1.setBounds(320, 510, 120, 25);  
	        frame.add(box1); 
	        
	        //将下拉列表框放入表格编辑器  

			button1.addActionListener(new ActionListener(){//匿名类实现ActionListener接口
				public void actionPerformed(ActionEvent e){	
					new Thread(new Runnable() {
							
							@Override
							public void run() {
								txt.setText(("以下为详细内容："+"\n"));
								int j = 1;
								if(box.getSelectedItem().equals("10次"))
								{
									j=10;
								}
								if(box.getSelectedItem().equals("5次"))
								{
									j=5;
								}
								if(box.getSelectedItem().equals("20次"))
								{
									j=20;
								}
								if(box.getSelectedItem().equals("50次"))
								{
									j=50;
								}
								//判断路径是否存在，不存在则创建。
								if(path==null){
									path = "/Users/mac/Desktop/monitorToolLog/";
								}

						        String file = path + "StartTime_log" ;
								//判断设备是否链接，未链接则直接返回。
								String devicename = box1.getSelectedItem().toString();
			                     if(devicename.equals("未发现设备")){
			                    	 return;
			                     }
			                     String pkg=JText.getText();
			                     //判断pkg在设备上是否安装，未安装则提示该应用未安装，请先安装应用。
			                     try {
									if(!AppUtils.isAppInstalled(devicename, pkg)){
										JOptionPane.showMessageDialog(new JFrame(), "应用未安装，请先安装应用！");
										return;
									 }
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			                     
			                     String activity=JText1.getText();
			                     button1.setBackground(Color.LIGHT_GRAY);
								 button1.setEnabled(false);
								
								try {
									int total =0;
									String text ="";
									for(int i=1;i<=j+1;i++)
									{
										if(i==j+1){
											text ="\n平均启动时间："+total/j+"ms";
											button1.setBackground(new Color(238,238,238));
											button1.setEnabled(true);
										}
										else{
											String starttime= GetPackageStartTime.GetstartTime(pkg,activity,devicename);
											text = "设备"+devicename+"第"+i+"次启动时间："+starttime+"ms"+"\n";
											int temp = Integer.parseInt(starttime);
											total +=temp;
										}
				
										txt.append(text);
							            Outlog.log.writelogs(file,time,text);
							            Thread.currentThread();
										Thread.sleep(100);
										
					            
									}	
									} catch (IOException | InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								
							}
						}).start();

				}   
				});
			
			
		/*	//JMenu =new JMenu(rtcp);
			
			JButton button1 = new JButton("1");		
			button1.setBounds(80, 320, 60, 25);
			JTextField JF1 = new JTextField("");
			JF1.setBounds(120, 220, 120, 25);
			JF1.setHorizontalAlignment(0);
			frame.add(JF1);
			
		
			button1.addActionListener(new ActionListener(){//匿名类实现ActionListener接口
				public void actionPerformed(ActionEvent e){
				}   
			});*/
		    
			
			
		
			setTitle("Android启动时间V1.0-----jhss_peter");
			setBounds(0,150, 632, 628);
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
			   
			    setVisible(false);
			  }
			

	public static void StartTime() throws Exception{
		
		StartTime_Menu m=new StartTime_Menu();
		m.setVisible(true);
		
		}
	
	public static void main(String []args) throws Exception{
		
		StartTime_Menu m=new StartTime_Menu();
		m.setVisible(true);
		
		}
		

}
