package pac;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import oracle.net.aso.s;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class PassengerWindows {
	
	
	    SQLPassenger passenger=new SQLPassenger();
	
	    public static JFrame loginWindow,window;  
	    JTextField accounter,password,
	               fromCity,toCity,	             
	               pID,pName,tel,
	               fID,password2;
	    
	    JButton          register,login,
	                     addPassenger,//pID,pName,tel//ok
	                     bookFirst,bookSecond,//fID,pID//ok
	                     refundTicket,//pID,password,fID
	                     checkTicket,//pID//ok
	                     checkFlight,//fromCity,toCity//ok
	                     help;
	    JTextArea showInfo;
	 
	    
	  
	    /**
	     * Main function
	     * @param args
	     */
	    public static void main(String[] args) {
		   new PassengerWindows(); 
    	}
	    
	    /**
	     * Initialize the window 
	     */
	    PassengerWindows() {
			init();
		} 
	    /**
	     * The initialize function about layout
	     */
	    public void init(){
	    	
	    	  loginWindow = new JFrame("登陆");
	    	  loginWindow.setLayout(null);  
	          loginWindow.setBounds(200, 200, 300, 280);  
	          loginWindow.setResizable(false);
	          //Make the window's size unchangeable  
	    	  
	          login =new JButton("login");
	          login.setBounds(180, 150, 90, 30);
	          loginWindow.add(login);
	        	          
	          register =new JButton("register");
	          register.setBounds(50, 150, 90, 30);
	          loginWindow.add(register);	        
	       	          
	          JLabel ac=new JLabel("密码:");
	          ac.setBounds(50,100,30,20);
	          loginWindow.add(ac);
	          
	          JLabel pw=new JLabel("账户:");
	          pw.setBounds(50, 50, 30, 20);
	          loginWindow.add(pw);
	          
	          accounter =new JTextField();
	          accounter.setBounds(90, 50, 150, 20);	          
	          loginWindow.add(accounter);
	          
	          password =new JTextField();
	          password.setBounds(90, 100, 150, 20);	          
	          loginWindow.add(password);
	          //
	    	  window = new JFrame("乘客中心");  
	          window.setLayout(null);  
	          window.setBounds(200, 200, 500, 480);  
	          window.setResizable(false);
	          //Make the window's size unchangeable  
	          
	          //
	          showInfo = new JTextArea("提示信息：");
	          showInfo.setBounds(10, 10, 460, 150);
	          window.add(showInfo);
	          JScrollPane scrollPane1 = new JScrollPane(showInfo);
	          scrollPane1.setBounds(10, 10, 460, 150);  
	          window.add(scrollPane1);
	        //
	          checkFlight = new JButton("查询航线");
	          checkFlight.setBounds(10, 180, 90, 30);
	          window.add(checkFlight);
	      
	          JLabel from =new JLabel("起点:");
	          from.setBounds(110,180, 50, 30);
	          window.add(from);
	          fromCity = new JTextField();
	          fromCity.setBounds(150,180, 80, 30);
	          window.add(fromCity);
	          
	          JLabel to =new JLabel("终点:");
	          to.setBounds(260,180, 50, 30);
	          window.add(to);
	          toCity = new JTextField();
	          toCity.setBounds(300,180, 80, 30);
	          window.add(toCity);
	       // 
	          checkTicket =new JButton("查询订单:");
	          checkTicket.setBounds(10, 220, 90, 30);
	          window.add(checkTicket);
	          
	          JLabel pid1 = new JLabel("身份证:");
	          pid1.setBounds(105,220, 50, 30);
	          window.add(pid1);
	          
	          pID= new JTextField();
	          pID.setBounds(150,220, 150, 30);
	          window.add(pID);
	          
	          addPassenger = new JButton("添加乘客:");
	          addPassenger.setBounds(10, 260, 90, 30);
	          window.add(addPassenger);
	          
	          JLabel tele = new JLabel("电话:");
	          tele.setBounds(110, 260, 90, 30);
	          window.add(tele);
	          
	          tel =new JTextField();
	          tel.setBounds(150,260, 150, 30);
	          window.add(tel);
	          
	          JLabel name = new JLabel("姓名:");
	          name.setBounds(320,260, 80, 35);
	          window.add(name);
	          
	          pName = new JTextField();
	          pName.setBounds(350,260, 80, 30);
	          window.add(pName);	          
	         
	          bookFirst = new JButton("购头等舱:");
	          bookFirst.setBounds(10, 300, 90, 30);
	          window.add(bookFirst);
	          
	          JLabel flightID = new JLabel("航班号:");
	          flightID.setBounds(110, 300, 90, 30);
	          window.add(flightID);
	          
	          fID =new JTextField();
	          fID.setBounds(160,300, 100, 30);
	          window.add(fID);
	          
	          bookSecond = new JButton("购经济舱:");
	          bookSecond.setBounds(10, 340, 90, 30);
	          window.add(bookSecond);
	          
	          refundTicket = new JButton("退票:");
	          refundTicket.setBounds(10, 380, 90, 30);
	          window.add(refundTicket);
	          
	          JLabel newPw = new JLabel("密码:");
	          newPw.setBounds(120, 380, 30, 30);
	          window.add(newPw);
	          
	          password2 = new JTextField();
	          password2.setBounds(160, 380, 90, 30);
	          window.add(password2);
	          
	          help = new JButton("帮助!!");
	          help.setBounds(400, 400, 80, 40);
	          window.add(help);
	          
	          myEvent();
	          window.setVisible(false); 
	          loginWindow.setVisible(true);
	    }
	    
	    /**
	     * The function about event of listener
	     */
	    public void myEvent(){
	    	login.addActionListener(new ActionListener() {
	    		@Override
				public void actionPerformed(ActionEvent arg0) {
	    			String name=accounter.getText();
					String pwd=password.getText();
					if(passenger.login(name, pwd)){
	    				window.setVisible(true);
		    			loginWindow.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(loginWindow, "登陆失败");
					}
					
				}
			});
	    	
	    	register.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
				    String name=accounter.getText();
				    String pwd=password.getText();
					if(passenger.register(name, pwd))
					   JOptionPane.showMessageDialog(loginWindow, "注册成功");
					else 						
					   JOptionPane.showMessageDialog(loginWindow, "注册失败");
				}
			});
	    	
	    	checkFlight.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String from=fromCity.getText();
					String to=toCity.getText();
					showInfo.setText(passenger.checkFlight(from, to));
				}
			});
	    	
	    	checkTicket.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String idCard=pID.getText();
					showInfo.setText(passenger.checkTicket(idCard));					
				}
			});
	    	
	    	addPassenger.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String pid=pID.getText();
					String pname=pName.getText();
					String tele=tel.getText();
					if(passenger.insertPassenger(pid, pname, tele)){
						  JOptionPane.showMessageDialog(loginWindow, "添加成功");
					}
					else
						  JOptionPane.showMessageDialog(loginWindow, "添加失败");
				}
			});
	    	
	    	//bookFirst
	    	bookFirst.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String pid=pID.getText();
					String fid=fID.getText();
					showInfo.setText(passenger.bookFirstTicket(fid, pid));
				}
			});
	    	//bookScond
	    	bookSecond.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String pid=pID.getText();
					String fid=fID.getText();
					showInfo.setText(passenger.bookSecondTicket(fid, pid));
				}
			});
	    	//refundTicket
	    	refundTicket.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String pid=pID.getText();
					String pword=password2.getText();
					String fid=fID.getText();
					showInfo.setText(passenger.refundTicket(pid, pword, fid));
				}
			});
	    	
	    	help.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(loginWindow, "查询航线：起点、终点"+"\n"+
                                                   "查询订单：身份证"+"\n"+
                                                 "添加乘客：身份证、姓名、电话"+"\n"+
                                              "购头等舱、购经济舱：航班号、身份证"+"\n"+
                                                   "退票：身份证、航班号、密码");
				}
			});
	    }
	    
	    
	  
	 
}
