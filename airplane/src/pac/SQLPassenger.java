package pac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;

public class SQLPassenger {

    // 定义连接所需的字符串
    private static String USERNAMR = "system";
    private static String PASSWORD = "cello325786";
    private static String DRVIER = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
    
    // 创建一个数据库连接
    Connection connection = getConnection();
    
    // 创建预编译语句对象，一般都是用这个而不用Statement
    PreparedStatement pstm = null;
    
    // 创建一个结果集对象
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
			if(Integer.parseInt(left)<=0) return "头等舱座位已售完";
		}
	} catch (SQLException e) {
		return "未找到此航班信息";
	}
      
    //Insert
     String sql2="insert into ticket values('"+tID+"','"+fID+"','"+pID+"',1,"+price+",to_date('"+startDate+"','YYYY-MM-DD'),to_date('"+bookedDate+"','YYYY-MM-DD'))";
     try {
		pstm = connection.prepareStatement(sql2);
		System.out.println(sql2);
	    rs=pstm.executeQuery();
	    return "已经预定此订单，请在半小时内支付";
	} catch (SQLException e) {
		return "预定失败";
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
			if(Integer.parseInt(left)<=0) return "经济舱座位已售完";
		}
	} catch (SQLException e) {
		return "未找到此航班信息";
	}
      
    //Insert
     String sql2="insert into ticket values('"+tID+"','"+fID+"','"+pID+"',2,"+price+",to_date('"+startDate+"','YYYY-MM-DD'),to_date('"+bookedDate+"','YYYY-MM-DD'))";
     try {
		pstm = connection.prepareStatement(sql2);
		System.out.println(sql2);
	    rs=pstm.executeQuery();
	    return "已经预定此订单，请在半小时内支付";
	} catch (SQLException e) {
		return "预定失败";
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
    	if(!login(pID, password)) return "账户或密码错误";
    	String sql ="delete from ticket where pid='"+pID+"' and fid='"+fID+"'";
    	try {
			pstm = connection.prepareStatement(sql);
			pstm.executeQuery();
			return "你已经退掉了这张票，您可以在订单中查看，退款会在三天内返还";
		} catch (SQLException e) {
			return "退票失败，请先查询你的订单，确认输入是否正确";
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
				res+=   "航班:"+rs.getString("fid")+" "+
						"身份证:"+rs.getString("pid")+" "+
						"起点:"+rs.getString("fromCity")+" "+
						"终点:"+rs.getString("toCity")+" "+
						"出发时间:"+rs.getString("flyDate")+" "+
						"价格:"+rs.getString("price")+" "+
						"座位等级:"+rs.getString("seatClass")+" "+
						"航空公司:"+rs.getString("cName")+"\n";
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
				res+=   "航班:"+rs.getString("fid")+" "+
						"起点:"+rs.getString("fromCity")+" "+
						"终点:"+rs.getString("toCity")+" "+
						"出发时间:"+rs.getString("flyDate")+" "+
						"头等舱价格:"+rs.getString("firstClassPrice")+" "+
						"经济舱价格:"+rs.getString("secondClassPrice")+" "+
						"头等舱剩余:"+rs.getString("leftFirstClass")+" "+
						"经济舱剩余:"+rs.getString("leftSecondClass")+" "+
						"航空公司:"+rs.getString("cName")+"\n";
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
				res+=   "航线号:"+rs.getString("aid")+" "+
						"起点:"+rs.getString("fromCity")+" "+
						"终点:"+rs.getString("toCity")+" "+
						"距离:"+rs.getString("distanceKM")+"km"+"\n";					
			}
			if(res.length()==0) return "Nothing found";
			return res;
		} catch (SQLException e) {
    		return "Nothing found";
		}		  	
    }
//Link and release functions
    /**
     * 获取Connection对象 OK
     * @return the connection
     */
    public Connection getConnection() {
        try {
            Class.forName(DRVIER);
            connection = DriverManager.getConnection(URL, USERNAMR, PASSWORD);
            System.out.println("成功连接数据库");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return connection;
    }

    /**
     * 释放资源 OK
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