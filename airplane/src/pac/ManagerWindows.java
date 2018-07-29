package pac;

import javax.swing.*;
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

public class ManagerWindows {
	
	
	    SQLSystem sys=new SQLSystem();
	
	    public static JFrame loginWindow,window,deleteWindow;
	    JTextField id,type;
	    JTextField accounter,password;	              
        
	    JButton register,login,help,delHelp,delete,del;
	    JButton checkAirline,
	            checkCompany,
	            checkFlight,
	            checkPlane;
	    
	    JTextField from,to,distance,//
	               companyName,
	               firstPrice,secondPrice,date,airlineID,planeID1,
	               planeID2,planeModel,firstSeat,secondSeat,companyID;
//System.out.println(t.addAirline("��ɳ", "�Ͼ�", 883));
//System.out.println(t.addCompany("�¿����չ�˾"));
//System.out.println(t.addFlight(876, 438, "2018-04-13", "000002", "G54018"));
//System.out.println(t.addPlane("G56663", "�տ�321", 9, 40, "000003"));
	  		
	    JButton addAirline,
	            addCompany,
	            addFlight,
	            addPlane;
	    
	    
	    JTextArea showInfo;
	 
	    
	  
	    /**
	     * Main function
	     * @param args
	     */
	    public static void main(String[] args) {
		   new ManagerWindows(); 
    	}
	    
	    /**
	     * Initialize the window 
	     */
	    ManagerWindows() {
			init();
		} 
	    /**
	     * The initialize function about layout
	     */
	    public void init(){
	    	
	    	
	    	  
	    	  loginWindow = new JFrame("��½");
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
	       	          
	          JLabel ac=new JLabel("����:");
	          ac.setBounds(50,100,30,20);
	          loginWindow.add(ac);
	          
	          JLabel pw=new JLabel("�˻�:");
	          pw.setBounds(50, 50, 30, 20);
	          loginWindow.add(pw);
	          
	          accounter =new JTextField();
	          accounter.setBounds(90, 50, 150, 20);	          
	          loginWindow.add(accounter);
	          
	          password =new JTextField();
	          password.setBounds(90, 100, 150, 20);	          
	          loginWindow.add(password);
	          //
	    	  window = new JFrame("ϵͳ����");  
	          window.setLayout(null);  
	          window.setBounds(200, 200, 500, 480);  
	          window.setResizable(false);
	          //Make the window's size unchangeable  
	          
	          deleteWindow = new JFrame("ɾ��");
	          deleteWindow.setLayout(null);
	          deleteWindow.setBounds(700, 200, 350, 250);
	          deleteWindow.setResizable(false);
	          //
	          JLabel ID = new JLabel("ID:");
	          ID.setBounds(20,30,30,30);
	          deleteWindow.add(ID);
	          id = new JTextField();
	          id.setBounds(50, 30, 90, 30);
	          deleteWindow.add(id);
	          
	          JLabel TYPE = new JLabel("tpye:");
	          TYPE.setBounds(160,30,30,30);
	          deleteWindow.add(TYPE);
	          type = new JTextField();
	          type.setBounds(200, 30, 90, 30);
	          deleteWindow.add(type);
	        
	          del = new JButton("delete");
	          del.setBounds(120, 80, 90, 30);
	          deleteWindow.add(del);
	          
	          delHelp = new JButton("Tips");
	          delHelp.setBounds(120, 120, 90, 30);
	          deleteWindow.add(delHelp);
	          //
	          showInfo = new JTextArea("��ʾ��Ϣ��");
	          showInfo.setBounds(10, 10, 460, 150);
	          window.add(showInfo);
	          JScrollPane scrollPane1 = new JScrollPane(showInfo);
	          scrollPane1.setBounds(10, 10, 460, 150);  
	          window.add(scrollPane1);
	          //
	          
	          checkAirline = new JButton("������Ϣ");
	          checkAirline.setBounds(10, 170, 90, 30);
	          window.add(checkAirline);
	          	          
	          checkCompany = new JButton("��˾��Ϣ");
	          checkCompany.setBounds(120, 170, 90, 30);
	          window.add(checkCompany);
	          
	          checkFlight = new JButton("������Ϣ");
	          checkFlight.setBounds(230, 170, 90, 30);
	          window.add(checkFlight);
	          
	          checkPlane = new JButton("�ɻ���Ϣ");
	          checkPlane.setBounds(350, 170, 90, 30);
	          window.add(checkPlane);
	          
	          //
	          addAirline = new JButton("��Ӻ���");
	          addAirline.setBounds(10, 210, 90, 30);
	          window.add(addAirline);
	          	          
	          addCompany = new JButton("��ӹ�˾");
	          addCompany.setBounds(120, 210, 90, 30);
	          window.add(addCompany);
	          
	          addFlight = new JButton("��Ӻ���");
	          addFlight.setBounds(230, 210, 90, 30);
	          window.add(addFlight);
	          
	          addPlane = new JButton("��ӷɻ�");
	          addPlane.setBounds(350, 210, 90, 30);
	          window.add(addPlane);
	          //
	          
	          from = new JTextField("���:");
	          from.setBounds(10, 250, 90, 30);
	          window.add(from);
	          
	          to = new JTextField("�յ�:");
	          to.setBounds(10, 290, 90, 30);
	          window.add(to);
	          
	          distance = new JTextField("����:");
	          distance.setBounds(10, 330, 90, 30);
	          window.add(distance);
	          //
	          
	          companyName = new JTextField("��˾����:");
	          companyName.setBounds(120, 250, 90, 30);
	          window.add(companyName);

	          // firstPrice,secondPrice,date,airlineID,planeID1,
	          firstPrice = new JTextField("ͷ�Ȳռ۸�:");
	          firstPrice.setBounds(230, 250, 90, 30);
	          window.add(firstPrice);

	          secondPrice = new JTextField("���òռ۸�:");
	          secondPrice.setBounds(230, 290, 90, 30);
	          window.add(secondPrice);
	          
	          date = new JTextField("����:");
	          date.setBounds(230, 330, 90, 30);
	          window.add(date);
	          
	          airlineID = new JTextField("����ID:");
	          airlineID.setBounds(230, 370, 90, 30);
	          window.add(airlineID);
	          
	          planeID1 = new JTextField("�ɻ�ID:");
	          planeID1.setBounds(230, 410, 90, 30);
	          window.add(planeID1);
	          
	          // planeID2,planeModel,firstSeat,secondSeat,companyID;
	          planeID2=new JTextField("�ɻ�ID:");
	          planeID2.setBounds(350, 250, 90, 30);
	          window.add(planeID2);
	          
	          planeModel=new JTextField("�ɻ��ͺ�:");
	          planeModel.setBounds(350, 290, 90, 30);
	          window.add(planeModel);
	          
	          firstSeat=new JTextField("ͷ�Ȳ���λ��:");
	          firstSeat.setBounds(350, 330, 90, 30);
	          window.add(firstSeat);
	          
	          secondSeat=new JTextField("���ò���λ��:");
	          secondSeat.setBounds(350, 370, 90, 30);
	          window.add(secondSeat);
	          
	          companyID=new JTextField("��˾ID:");
	          companyID.setBounds(350, 410, 90, 30);
	          window.add(companyID);
	          //
	          help = new JButton("����");
	          help.setBounds(10, 400, 90, 30);
	          window.add(help);
	          //
	          delete = new JButton("ɾ��");
	          delete.setBounds(100, 400, 90, 30);
	          window.add(delete);
	          //
	          myEvent();
	          window.setVisible(false); 
	          loginWindow.setVisible(true);
	          deleteWindow.setVisible(false);
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
					if(sys.login(name, pwd)){
	    				window.setVisible(true);
		    			loginWindow.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(loginWindow, "��½ʧ��");
					}
					
				}
			});
	    	
	    	delete.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					deleteWindow.setVisible(true);
					JOptionPane.showMessageDialog(window, "ɾ��ʱ��ͬʱɾ�����������ֶ�!!"
							+ "������־�б����¼��������!!");
				}
			});
	    	
	    	register.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
				    String name=accounter.getText();
				    String pwd=password.getText();
					if(sys.register(name, pwd))
					   JOptionPane.showMessageDialog(loginWindow, "ע��ɹ�");
					else 						
					   JOptionPane.showMessageDialog(loginWindow, "ע��ʧ��");
				}
			});
	    	
	  
	    	checkAirline.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					showInfo.setText(sys.checkAllAirline());
				}
			});
	    	
	    	
	    	checkCompany.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					showInfo.setText(sys.checkAllCompany());
				}
			});
	    	
	    	
	    	checkFlight.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					showInfo.setText(sys.checkAllFlight());
				}
			});
	    	
	    	
	    	checkPlane.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					showInfo.setText(sys.checkAllPlane());
				}
			});
	    	
	    	//addAirline
	    	addAirline.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					String fromCity=from.getText();
					String toCity=to.getText();
					int dis =Integer.parseInt(distance.getText());
					if(sys.addAirline(fromCity, toCity, dis))
			JOptionPane.showMessageDialog(window, "��ӳɹ�");
					else
			JOptionPane.showMessageDialog(window, "���ʧ�ܣ������ʽ!");
				}
			});
	    	
	     	addCompany.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					String cName=companyName.getText();
					if(sys.addCompany(cName))
			JOptionPane.showMessageDialog(window, "��ӳɹ�");
					else
			JOptionPane.showMessageDialog(window, "���ʧ�ܣ������ʽ!");
				}
			});
	     	
	    	addFlight.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {	
					try{
					int fiPrice=Integer.parseInt(firstPrice.getText());
					int sePrice=Integer.parseInt(secondPrice.getText());
					String flyDate=date.getText();
					String aid=airlineID.getText();
					String planeID=planeID1.getText();
					if(sys.addFlight(fiPrice, sePrice, flyDate, aid, planeID))
			JOptionPane.showMessageDialog(window, "��ӳɹ�");
					else
			JOptionPane.showMessageDialog(window, "���ʧ�ܣ������ʽ!");
					}catch(Exception e){
			JOptionPane.showMessageDialog(window, "�����ʽ!");			
					}		
				}
			});
	    	
	    	addPlane.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					try{
					    String planeID=planeID2.getText();
					    String model=planeModel.getText();
					    int fi=Integer.parseInt(firstSeat.getText());
					    int se=Integer.parseInt(secondSeat.getText());
					    String cID=companyID.getText();
						if(sys.addPlane(planeID, model, fi, se, cID))
				JOptionPane.showMessageDialog(window, "��ӳɹ�");
						else
				JOptionPane.showMessageDialog(window, "���ʧ�ܣ������ʽ!");
						}catch(Exception e){
				JOptionPane.showMessageDialog(window, "�����ʽ!");			
						}		
				}
			});
	    	
	    	del.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String nowId=id.getText();
					String nowTpye=type.getText();
					if(sys.delete(nowId, nowTpye))
			JOptionPane.showMessageDialog(deleteWindow, "ɾ���ɹ�");
					else	
			JOptionPane.showMessageDialog(deleteWindow, "ɾ��ʧ��,��ȷ������");						
				}
			});
	    	
	    	help.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String tips="��Ӻ��ߣ���㡢�յ㡢����"+"\n"+
		                              "��ӹ�˾����˾����"+"\n"+
           "��Ӻ��ࣺͷ�Ȳռ۸񡢾��òռ۸����ڡ�����ID���ɻ�ID"+"\n"+
           "��ӷɻ����ɻ�ID���ɻ��ͺš�ͷ�Ȳ���λ�������ò���λ������˾ID";
					showInfo.setText(tips);
					JOptionPane.showMessageDialog(window, tips);		
				}
			});
	    	
	    	delHelp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String tips="ID:������Ҫɾ����id"+"\n"+
					            "tpye:��һ��Сд��ĸ����ɾ��������"+"\n"+
							            "����(a) �ɻ�(p) ����(f) ��˾(c)";
					JOptionPane.showMessageDialog(deleteWindow, tips);							
				}
			});
	    }
	    
	    
	  
	 
}
