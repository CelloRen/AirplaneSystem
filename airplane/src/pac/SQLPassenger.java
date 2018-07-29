package pac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;

public class SQLPassenger {

    // ��������������ַ���
    private static String USERNAMR = "system";
    private static String PASSWORD = "cello325786";
    private static String DRVIER = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
    
    // ����һ�����ݿ�����
    Connection connection = getConnection();
    
    // ����Ԥ����������һ�㶼�������������Statement
    PreparedStatement pstm = null;
    
    // ����һ�����������
    ResultSet rs = null;
    
    //The primary key the passenger can influence
    int tID=0;
   
    //Initialize for the database about tID
    SQLPassenger(){
	  String sql="select max(tid) from ticket";
	  try {
			pstm=connection.prepareStatement(sql);
			rs=pstm.executeQuery();
			if(rs.next()){
		    System.out.println("test:");
			this.tID = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("init error");
		}
   }

    
   
//Execute operation
    
    /**
     * Return true if Register a account on login
     * @param name
     * @param password
     * @return
     */
    public boolean register(String name,String password){
    	String sql="insert into login values ('"+name+"','"+password+"')";
    	System.out.println(sql);
    	try {
			pstm = connection.prepareStatement(sql);
			pstm.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
    }

    /**
     * Return true if logged in
     * @param name
     * @param password
     * @return
     */
    public boolean login(String name,String password){
    	String sql="select pw from login where counter='"+name+"'";
    	try {
			pstm=connection.prepareStatement(sql);
			rs=pstm.executeQuery();
			rs.next();
			String s= rs.getString("pw");
			if(s.equals(password)) return true;
			else return false;
		} catch (SQLException e) {
			return false;
		}
    }
    
    /**
     * Return true if inserted into passenger OK
     * @param pID
     * @param pName
     * @param tel
     * @return
     */
    public boolean insertPassenger(String pID,String pName,String tel){
    	if(!isNormal(pID)) return false;
    	String sql = "insert into passenger values('"+pID+"','"+pName+"','"+tel+"')";
    	try {
			pstm = connection.prepareStatement(sql);
			pstm.executeQuery();
			return true;
		} catch (SQLException e) {
			return false;
		}
    	
    }
    
    /**
     *BookFirstTicket
     * @return
     */
    public String bookFirstTicket(String fID,String pID){
    //insert into ticket values('000001','000002','500233199610060818',1,820,to_date('2018-04-12','YYYY-MM-DD'),to_date('2018-04-09','YYYY-MM-DD'));
    //                              tID ,   fID  , pID        ,seatClass,price, start date,                      bookedDate
    String tID=creatTID();
    String startDate="",price="";
   
    String bookedDate=today();
	String sql1="select flyDate,firstClassPrice,leftFirstClass from flight where fID='"+fID+"'";
      try {
		pstm = connection.prepareStatement(sql1);
		rs=pstm.executeQuery();
		if(rs.next()){
			startDate=rs.getString("flyDate").substring(0, 10);
			price=rs.getString("firstClassPrice");    
			String left=rs.getString("leftFirstClass");
			if(Integer.parseInt(left)<=0) return "ͷ�Ȳ���λ������";
		}
	} catch (SQLException e) {
		return "δ�ҵ��˺�����Ϣ";
	}
      
    //Insert
     String sql2="insert into ticket values('"+tID+"','"+fID+"','"+pID+"',1,"+price+",to_date('"+startDate+"','YYYY-MM-DD'),to_date('"+bookedDate+"','YYYY-MM-DD'))";
     try {
		pstm = connection.prepareStatement(sql2);
		System.out.println(sql2);
	    rs=pstm.executeQuery();
	    return "�Ѿ�Ԥ���˶��������ڰ�Сʱ��֧��";
	} catch (SQLException e) {
		return "Ԥ��ʧ��";
	}
 

    }

    /**
     *BookFirstTicket
     * @return
     */
    public String bookSecondTicket(String fID,String pID){
    //insert into ticket values('000001','000002','500233199610060818',1,820,to_date('2018-04-12','YYYY-MM-DD'),to_date('2018-04-09','YYYY-MM-DD'));
    //                              tID ,   fID  , pID        ,seatClass,price, start date,                      bookedDate
    String tID=creatTID();
    String startDate="",price="";
   
    String bookedDate=today();
	String sql1="select flyDate,secondClassPrice,leftSecondClass from flight where fID='"+fID+"'";
      try {
		pstm = connection.prepareStatement(sql1);
		rs=pstm.executeQuery();
		if(rs.next()){
			startDate=rs.getString("flyDate").substring(0, 10);
			price=rs.getString("secondClassPrice");    
			String left=rs.getString("leftSecondClass");
			if(Integer.parseInt(left)<=0) return "���ò���λ������";
		}
	} catch (SQLException e) {
		return "δ�ҵ��˺�����Ϣ";
	}
      
    //Insert
     String sql2="insert into ticket values('"+tID+"','"+fID+"','"+pID+"',2,"+price+",to_date('"+startDate+"','YYYY-MM-DD'),to_date('"+bookedDate+"','YYYY-MM-DD'))";
     try {
		pstm = connection.prepareStatement(sql2);
		System.out.println(sql2);
	    rs=pstm.executeQuery();
	    return "�Ѿ�Ԥ���˶��������ڰ�Сʱ��֧��";
	} catch (SQLException e) {
		return "Ԥ��ʧ��";
	}
 

    }
    
    /**
     * Refund the ticket according to pID password and fID
     * @param pID
     * @param password
     * @param fID
     * @return
     */
    public String refundTicket(String pID,String password,String fID){
    	if(!login(pID, password)) return "�˻����������";
    	String sql ="delete from ticket where pid='"+pID+"' and fid='"+fID+"'";
    	try {
			pstm = connection.prepareStatement(sql);
			pstm.executeQuery();
			return "���Ѿ��˵�������Ʊ���������ڶ����в鿴���˿���������ڷ���";
		} catch (SQLException e) {
			return "��Ʊʧ�ܣ����Ȳ�ѯ��Ķ�����ȷ�������Ƿ���ȷ";
		}
    }
    
   
    
//Query operation

    /**
     * Check the ticket according to the idCard
     * @param idCard
     * @return
     */
    public String checkTicket(String idCard){
    	String res="";
    	String sql="select * from passengerCheck where pid='"+idCard+"'";
    	try {
			pstm = connection.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				res+=   "����:"+rs.getString("fid")+" "+
						"���֤:"+rs.getString("pid")+" "+
						"���:"+rs.getString("fromCity")+" "+
						"�յ�:"+rs.getString("toCity")+" "+
						"����ʱ��:"+rs.getString("flyDate")+" "+
						"�۸�:"+rs.getString("price")+" "+
						"��λ�ȼ�:"+rs.getString("seatClass")+" "+
						"���չ�˾:"+rs.getString("cName")+"\n";
			}
			if(res.length()==0) return "Nothing found";
			return res;
		} catch (SQLException e) {
    		return "Nothing found";
		}		  	
    }
    
    /**
     * Check the flight according to the fromCity and toCity
     * @param from
     * @param to
     * @return
     */
    public String checkFlight(String fromCity,String toCity){
    	String res="";
    	String sql="select * from flightCheck where fromCity='"+fromCity+"' and toCity='"+toCity+"'";
    	try {
			pstm = connection.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				res+=   "����:"+rs.getString("fid")+" "+
						"���:"+rs.getString("fromCity")+" "+
						"�յ�:"+rs.getString("toCity")+" "+
						"����ʱ��:"+rs.getString("flyDate")+" "+
						"ͷ�Ȳռ۸�:"+rs.getString("firstClassPrice")+" "+
						"���òռ۸�:"+rs.getString("secondClassPrice")+" "+
						"ͷ�Ȳ�ʣ��:"+rs.getString("leftFirstClass")+" "+
						"���ò�ʣ��:"+rs.getString("leftSecondClass")+" "+
						"���չ�˾:"+rs.getString("cName")+"\n";
			}
			if(res.length()==0) return "Nothing found";
			return res;
		} catch (SQLException e) {
    		return "Nothing found";
		}		  	
	}
    
    /**
     * Check all the Airline
     * @return
     */
    public String checkAirline(){
    	String res="";
    	String sql="select * from airline";
    	try {
			pstm = connection.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				res+=   "���ߺ�:"+rs.getString("aid")+" "+
						"���:"+rs.getString("fromCity")+" "+
						"�յ�:"+rs.getString("toCity")+" "+
						"����:"+rs.getString("distanceKM")+"km"+"\n";					
			}
			if(res.length()==0) return "Nothing found";
			return res;
		} catch (SQLException e) {
    		return "Nothing found";
		}		  	
    }
//Link and release functions
    /**
     * ��ȡConnection���� OK
     * @return the connection
     */
    public Connection getConnection() {
        try {
            Class.forName(DRVIER);
            connection = DriverManager.getConnection(URL, USERNAMR, PASSWORD);
            System.out.println("�ɹ��������ݿ�");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return connection;
    }

    /**
     * �ͷ���Դ OK
     */
    public void ReleaseResource() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
//Functions for help   
    /**
     * Check whether the pID is normal or not,actually we should check
     *   in public security system,but we don't have the right  
     * @param idCard
     * @return
     */
    public boolean isNormal(String idCard){   	
    	if(idCard.length()!=18) return false;
    	for(int i=0;i<idCard.length();i++){
    		if(idCard.charAt(i)>'9'||idCard.charAt(i)<'0'||Character.isLetter(idCard.charAt(i)))
    			return false;
    	}
    	return true;
    }
     
    /**
     * Create a new tID for ticket
     * @return
     */
    public String creatTID(){
    	tID++;
    	return String.format("%0"+6+"d",tID);   	
    }
    
    public String today(){
     	Calendar cal=Calendar.getInstance();      
    	String y=Integer.toString(cal.get(Calendar.YEAR));      
    	String m=Integer.toString(cal.get(Calendar.MONTH)+1);      
    	String d=Integer.toString(cal.get(Calendar.DATE)); 
    	return y+"-"+m+"-"+d;
    }
    
 
}