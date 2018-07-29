package pac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;

import oracle.net.aso.c;

public class SQLSystem {

	public static void main(String[] args) {
		SQLSystem t = new SQLSystem();
		//System.out.println(t.aID+" "+t.cID+" "+t.fID);//ok
		//System.out.println(t.addAirline("��ɳ", "�Ͼ�", 883));//ok
		//System.out.println(t.addCompany("�¿����չ�˾"));//ok
		//System.out.println(t.addFlight(876, 438, "2018-04-13", "000002", "G54018"));//ok
		//System.out.println(t.addPlane("G56663", "�տ�321", 9, 40, "000003"));//ok 
		//System.out.println(t.checkAllAirline());//ok
		//System.out.println(t.checkAllCompany());//ok
		//System.out.println(t.checkAllFlight());//ok
		//System.out.println(t.checkAllPlane());//ok
	}
	
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
    
    //The primary key 
    int aID=0;
    int cID=0;
    int fID=0;
    //Initialize for the database about tID
    SQLSystem(){
      //aID
	  String sqlA="select max(aID) from airline";
	  try {
			pstm=connection.prepareStatement(sqlA);
			rs=pstm.executeQuery();
			if(rs.next()){
			this.aID = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("init error");
		}
	  //cID
	  String sqlC="select max(cID) from company";
	  try {
			pstm=connection.prepareStatement(sqlC);
			rs=pstm.executeQuery();
			if(rs.next()){
			this.cID = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("init error");
		}
	  //fID 
	  String sqlF="select max(fID) from flight";
	  try {
			pstm=connection.prepareStatement(sqlF);
			rs=pstm.executeQuery();
			if(rs.next()){
			this.fID = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("init error");
		}
   }

    
   
//Login operation  
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
    
   //Add operation    
    /**
     * Add a company
     * @param companyName
     * @return
     */
    public boolean addCompany(String companyName){
    	String cid=createCID();
    	String sql="insert into company values ('"+cid+"','"+companyName+"')";
    	try {
			pstm = connection.prepareStatement(sql);
			pstm.executeQuery();
			return true;
		} catch (SQLException e) {
			return false;
		}
    }
    
    /**
     * Add a plane
     * @param companyName
     * @return
     */
    public boolean addPlane(String planeID,String planeModel,int fi,int se,String cID){
    	String sql="insert into plane values ('"+planeID+"','"+planeModel+"',"+fi+","+se+",'"+cID+"')";
    	try {
			pstm = connection.prepareStatement(sql);
			pstm.executeQuery();
			return true;
		} catch (SQLException e) {
			return false;
		}
    }
    
    /**
     *Add a flight
     * @return
     */
    public boolean addFlight(int fiPrice,int sePrice,String flyDate,String aid,String planeID){
        int leftFi=0;
        int leftSe=0;
        String fid=createFID();
        String sql1="select * from plane where planeID='"+planeID+"'";

		try {
			pstm = connection.prepareStatement(sql1);
			rs=pstm.executeQuery();
			if(rs.next()){				
				leftFi=Integer.parseInt(rs.getString("firstClass"));
			    leftSe=Integer.parseInt(rs.getString("secondClss"));
			}
	
			if(leftFi==0||leftSe==0) return false;
		} catch (SQLException e1) {
			return false;
		}

        String sql="insert into flight values('"+fid+"',"+leftFi+","+leftSe+","
        +fiPrice+","+sePrice+",to_date('"+flyDate+"','YYYY-MM-DD'),'"+aid+
        "','"+planeID+"')";
      
    	try {
			pstm = connection.prepareStatement(sql);
			pstm.executeQuery();
			return true;
		} catch (SQLException e) {
			return false;
		}
    }
    
    /**
     * Add an airline
     * @param from
     * @param to
     * @param dis
     * @return
     */
    public boolean addAirline(String from,String to,int dis){
    	String aid=createAID();
        String sql="insert into airline values('"+aid+"','"+from+"','"+to+"','"+dis+"')";
    	try {
			pstm = connection.prepareStatement(sql);
			pstm.executeQuery();
			return true;
		} catch (SQLException e) {
			return false;
		}
    }
    
//Delete operation
     public boolean delete(String id,String tpye){
    	 String sql="";
    	 if(tpye.equals("a")){
    		 sql+="delete from airline where aid='"+id+"'"; 
    	 }
    	 else if(tpye.equals("c")){
    		 sql+="delete from company where cid='"+id+"'"; 
    	 }
    	 else if(tpye.equals("p")){
    		 sql+="delete from plane where planeID='"+id+"'"; 
    	 }
    	 else if(tpye.equals("f")){
    		 sql+="delete from flight where fid='"+id+"'"; 
    	 }
    	 
    	 try {
			pstm = connection.prepareStatement(sql);
			pstm.executeQuery();
			if(sql.length()!=0)
			return true;
		} catch (SQLException e) {
			return false;
		}
    	 return true;
     }
//Query operation

    /**
     * Check all the company
     * @return
     */
    public String checkAllCompany(){
    	String res="";
    	String sql="select * from company";
    	try {
			pstm = connection.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				res+=   "��˾ID:"+rs.getString("cID")+
						" ��˾����:"+rs.getString("cName")+"\n";
			}
			if(res.length()==0) return "not found";
			return res;
			} catch (SQLException e) {
				return "not found";
		}
    }
    
    /**
     * Check all the plane
     * @return
     */
    public String checkAllPlane(){
    	String res="";
    	String sql="select * from plane";
    	try {
			pstm = connection.prepareStatement(sql);			
			rs=pstm.executeQuery();
			while(rs.next()){
				res+=   "�ɻ�ID:"+rs.getString("planeID")+
						" �ɻ��ͺ�:"+rs.getString("planeModel")+
						" ͷ�Ȳ�:"+rs.getString("firstClass")+
						" ���ò�:"+rs.getString("secondClss")+
						" ��˾ID:"+rs.getString("cID")+"\n";
			}
			if(res.length()==0) return "not found";
			return res;
			} catch (SQLException e) {
				return "not found";
		}
    }
    
  
    /**
     * Check the flight according to the fromCity and toCity
     * @return
     */
    public String checkAllFlight(){
    	String res="";
    	String sql="select * from flightCheck";
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
    public String checkAllAirline(){
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
     * Create a new aID for airline
     * @return
     */
    public String createAID(){
    	aID++;
    	return String.format("%0"+6+"d",aID);   
    }
    
    /**
     * Create a new cID for company
     * @return
     */
    public String createCID(){
    	cID++;
    	return String.format("%0"+6+"d",cID);   
    }
    
    /**
     * Create a new fID for flight
     * @return
     */
    public String createFID(){
    	fID++;
    	return String.format("%0"+6+"d",fID);   
    }
    
    
    public String today(){
     	Calendar cal=Calendar.getInstance();      
    	String y=Integer.toString(cal.get(Calendar.YEAR));      
    	String m=Integer.toString(cal.get(Calendar.MONTH)+1);      
    	String d=Integer.toString(cal.get(Calendar.DATE)); 
    	return y+"-"+m+"-"+d;
    }
    
 
}