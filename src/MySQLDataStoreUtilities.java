import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Current;

@Resource
public class MySQLDataStoreUtilities {
	public static Connection conn = null;
	public String query = null;

	public static void connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bestdeal" + "?useUnicode=true&characterEncoding=utf8", "root",
					"vinit");
			System.out.println("Connection Successful.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void deleteOrder(int orderId,String orderName,String customername)
	{
		try
		{
			
			connection();
			String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=? and username=?";
			PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
			pst.setInt(1,orderId);
			pst.setString(2,orderName);
			pst.setString(3, customername);
			pst.executeUpdate();
		}
		catch(Exception e)
		{
				
		}
	}	
	
	
	public static void insertUser(String username, String password, String repassword, String usertype) {
		try {

			connection();
			String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
					+ "VALUES (?,?,?,?);";

			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, repassword);
			pst.setString(4, usertype);
			boolean x = pst.execute();
			System.out.println("Value inserted:  " + x);
		} catch (Exception e) {

		}
	}

	public static HashMap<String, User> selectloginUser(String username, String table,String userType) {
		HashMap<String, User> hm = new HashMap<String, User>();
		try {
			connection();
			Statement stmt = conn.createStatement();
			String selectCustomerQuery = "select * from  " + table + " where username='" + username + "' and usertype='"+userType+"'";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while (rs.next()) {
				User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
			}
		} catch (Exception e) {
		}
		return hm;
	}
	
	public static HashMap<String, User> selectUser(String username, String table) {
		HashMap<String, User> hm = new HashMap<String, User>();
		try {
			connection();
			Statement stmt = conn.createStatement();
			String selectCustomerQuery = "select * from  " + table + " where username='" + username + "'";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while (rs.next()) {
				User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
			}
		} catch (Exception e) {
		}
		return hm;
	}

	@SuppressWarnings("null")
	public static String readData(String user, String pass) throws SQLException {
		Connection conn = null;
		String query = null;
		String user_id = null;
		query = "select username, password from bestdeal.login where username='" + user + "' and password='" + pass
				+ "'";
		System.out.println("Fromed Query " + query);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			user_id = rs.getString("username");
		}
		if (user_id.equals(user)) {
			user_id = user;
		} else {
			user_id = "Not Found";
		}
		return user_id;
	}

//Customer Order Function
	public static void insertCustomerOrder(int orderid, String username, String ordername, Double orderprice,
			String useraddress, String creditcardnumber, String date) {
		try {
			connection();
			String insertCustomerOrder = "INSERT INTO customerorders VALUES (?,?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertCustomerOrder);
			pst.setInt(1, orderid);
			pst.setString(2, username);
			pst.setString(3, ordername);
			pst.setDouble(4, orderprice);
			pst.setString(5, useraddress);
			pst.setString(6, creditcardnumber);
			pst.setString(7, date);
			
			boolean x = pst.execute();
			System.out.println("Value inserted: " + x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//Select Order
	public static HashMap<Integer, ArrayList<OrderPayment>> selectCustomerOrder(String username, int orderid,String flag) {
		//HashMap<String, User> hm = new HashMap<String, User>();
		 HashMap<Integer, ArrayList<OrderPayment>> hm = new  HashMap<Integer, ArrayList<OrderPayment>>();
		try {
			connection();
			Statement stmt = conn.createStatement();
			int counter =0;
			String selectCustomerQuery = null;
			if(flag.equals("View"))
			{
				selectCustomerQuery = "select * from  customerorders where username='" + username + "' and orderid='"+orderid+"'";
					
			}
			else if(flag.equals("Sales"))
			{

				selectCustomerQuery = "select * from  customerorders";
				
			}
			else
				
				selectCustomerQuery = "select * from  customerorders where username='" + username + "'";
				
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while (rs.next()) {
				OrderPayment orderpayment = new OrderPayment(rs.getInt("orderid"),rs.getString("username"),rs.getString("ordername"),rs.getDouble("orderprice"),
						rs.getString("useraddress"),rs.getString("creditcardno"),rs.getString("orderdate"));
				
				ArrayList<OrderPayment> orderpaymentList = new ArrayList<OrderPayment>();
				orderpaymentList.add(orderpayment);
				hm.put(rs.getInt("orderid"), orderpaymentList);
			}
		} catch (Exception e) {
		}
		return hm;
	}

	public static void updateOrder(int orderId, String productName, String username,String useraddress,String creditcardno) {
		try {
			connection();
			String insertCustomerOrder = "UPdATE customerorders SET USERADDRESS = ? , CREDITCARDNO =?  WHERE ORDERID = ? AND USERNAME =? AND ORDERNAME =  ?;";
			PreparedStatement pst = conn.prepareStatement(insertCustomerOrder);
			pst.setString(1, useraddress);
			pst.setString(2, creditcardno);
			pst.setInt(3, orderId);
			pst.setString(4, username);
			pst.setString(5, productName);
			
			int x = pst.executeUpdate();
			System.out.println("Value inserted: " + x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}