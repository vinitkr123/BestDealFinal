import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/Account")

public class Account extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	/* Display Account Details of the Customer (Name and Usertype) */

	protected void displayAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try {
			response.setContentType("text/html");
			if (!utility.isLoggedin()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session = request.getSession();
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Account</a>");
			pw.print("</h2><div class='entry'>");
			User user = utility.getUser();
		//	pw.print("<table class='gridtable'>");
			pw.print("<table class='table table-striped'>");
			pw.print("<tr>");
			pw.print("<td class='thead-dark'> User Name: </td>");
			pw.print("<td>" + user.getName() + "</td>");
			pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td class='thead-dark'> User Type: </td>");
			pw.print("<td>" + user.getUsertype() + "</td>");
			pw.print("</tr>");

			HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
			try {
				FileInputStream fileInputStream = new FileInputStream(
						new File(TOMCAT_HOME + "/webapps/vinit/PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				orderPayments = (HashMap) objectInputStream.readObject();
			} catch (Exception e) {

			}
			int size = 0;
			for (Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()) {
				for (OrderPayment od : entry.getValue())
					if (od.getUserName().equals(user.getName()))
						size = size + 1;
			}

			if (size > 0) {

				pw.print("<tr class='dark'>");
				pw.print("<td>Order Id</td>");
				pw.print("<td>UserName</td>");
				pw.print("<td>Product Ordered</td>");
				pw.print("<td>Delivery Date</td>");
				pw.print("<td>Product Price</td></tr>");
				for (Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()) {
					for (OrderPayment oi : entry.getValue())
						if (oi.getUserName().equals(user.getName())) {
							pw.print("<form method='post' action='RemoveUpdateOrder'>");
							pw.print("<tr>");
							// pw.print("<td><input type='radio' name='orderName' value='" +
							// oi.getOrderName() + "'></td>");
							pw.print("<td>" + oi.getOrderId() + "</td><td>" + oi.getUserName() + "</td><td>"
									+ oi.getOrderName() + "</td><td>"+oi.getDeliverydate()+"</td><td>Price: "
									+ oi.getOrderPrice() + "</td>");
							pw.print("<input type='hidden' name='orderId' value='" + oi.getOrderId() + "'>");
							pw.print("<td><input type='submit' name='Order' value='Cancel' class='btn btn-danger'></td>");
							pw.print("<td><input type='submit' name='Status' value='Status' class='btn btn-info'></td>");
							pw.print("<input type='hidden' name='orderId' value='" + oi.getOrderId() + "'>");
							pw.print("<input type='hidden' name='productName' value='" + oi.getOrderName() + "'>");
							pw.print("<input type='hidden' name='username' value='" + oi.getUserName() + "'>");
							pw.print("<input type='hidden' name='deliverydate' value='" + oi.getDeliverydate() + "'>");
							pw.print("<input type='hidden' name='userType' value='customer'>");
							pw.print("</tr>");
							pw.print("</form>");
						}

				}
				pw.print("</table>");
			} else {
				pw.print("<h4 style='color:red'>You have not placed any order with this account</h4>");
			}
			pw.print("</table>");
			pw.print("</h2></div></div></div>");
			utility.printHtml("Footer.html");
		} catch (Exception e) {
		}
	}

	public void displayAccountStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try {
			response.setContentType("text/html");
			if (!utility.isLoggedin()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session = request.getSession();
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Account</a>");
			pw.print("</h2><div class='entry'>");
			User user = utility.getUser();
			pw.print("<table class='gridtable'>");
			pw.print("<tr>");
			pw.print("<td> User Name: </td>");
			pw.print("<td>" + user.getName() + "</td>");
			pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td> User Type: </td>");
			pw.print("<td>" + user.getUsertype() + "</td>");
			pw.print("</tr>");

			HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
			try {
				FileInputStream fileInputStream = new FileInputStream(
						new File(TOMCAT_HOME + "/webapps/vinit/PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				orderPayments = (HashMap) objectInputStream.readObject();
			} catch (Exception e) {

			}
			int size = 0;
			for (Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()) {
				for (OrderPayment od : entry.getValue())
					if (od.getUserName().equals(user.getName()))
						size = size + 1;
			}

			if (size > 0) {

				pw.print("<tr>");
				pw.print("<td>OrderId:</td>");
				pw.print("<td>UserName:</td>");
				pw.print("<td>productOrdered:</td>");
				pw.print("<td>DeliveryDate:</td>");
				pw.print("<td>productPrice:</td></tr>");

				for (Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()) {
					for (OrderPayment oi : entry.getValue())
						if (oi.getUserName().equals(user.getName())) {
							pw.print("<form method='post' action='RemoveUpdateOrder'>");
							pw.print("<tr>");
							// pw.print("<td><input type='radio' name='orderName' value='" +
							// oi.getOrderName() + "'></td>");
							pw.print("<td>" + oi.getOrderId() + "</td><td>" + oi.getUserName() + "</td><td>"
									+ oi.getOrderName() + "</td><td>" + oi.getDeliverydate() + "</td><td>Price: "
									+ oi.getOrderPrice() + "</td>");
							pw.print("<input type='hidden' name='orderId' value='" + oi.getOrderId() + "'>");
							pw.print("<td><input type='submit' name='Order' value='Cancel' class='btnbuy'></td>");
							pw.print("<td><input type='button' name='Status' value='Status' class='btn btn-info'></td>");
							pw.print("<input type='hidden' name='orderId' value='" + oi.getOrderId() + "'>");
							pw.print("<input type='hidden' name='productName' value='" + oi.getOrderName() + "'>");
							pw.print("<input type='hidden' name='username' value='" + oi.getUserName() + "'>");
							pw.print("<input type='hidden' name='userType' value='customer'>");
							pw.print("</tr>");

							pw.print(
									"<h2 style='color:red'>Status: Your order is prcessed and will be delivered soon by "
											+ oi.getDeliverydate() + "</h2>");

							pw.print("</form>");
						}

				}
				pw.print("</table>");
			} else {
				pw.print("<h4 style='color:red'>You have not placed any order with this account</h4>");
			}
			pw.print("</table>");
			pw.print("</h2></div></div></div>");
			utility.printHtml("Footer.html");
		} catch (Exception e) {
		}

	}
}
