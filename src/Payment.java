import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.helpers.DefaultValidationEventHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

@WebServlet("/Payment")

public class Payment extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        TimeZone obj = TimeZone.getTimeZone("CST");
        formatter.setTimeZone(obj);
        Date today = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, 14);
        today = cal.getTime();
        
        
        Utilities utility = new Utilities(request, pw);
        if (!utility.isLoggedin()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to Pay");
            response.sendRedirect("Login");
            return;
        }

        String userAddress = request.getParameter("userAddress");
        String creditCardNo = request.getParameter("creditCardNo");
        String cust_name = request.getParameter("cust_name");
        
        if (!userAddress.isEmpty() && !creditCardNo.isEmpty()) {
            SimpleDateFormat df = new SimpleDateFormat("HHmmss");
        	int orderId=utility.getOrderPaymentSize()+1;
            for (OrderItem oi : utility.getCustomerOrders()) {
            	utility.storePayment(orderId, oi.getName(), oi.getPrice()-oi.getDiscount(), userAddress, creditCardNo, formatter.format(today).toString() );
            }

            //remove the order details from cart after processing

            OrdersHashMap.orders.remove(utility.username());
            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Order</a>");
            pw.print("</h2><div class='entry'>");

            pw.print("<h2>Your Order is processed");
            pw.print("<br>Your Order No is " + (orderId));


           
            
            Date canceltoday = new Date(System.currentTimeMillis());
            cal = Calendar.getInstance();
            cal.setTime(canceltoday);
            cal.add(Calendar.DATE, 9);
            canceltoday = cal.getTime();
            
            
            
           
       
            pw.print("<br>Estimated delivery date: " + formatter.format(today));

            pw.print("<br><div style='color:red'>You can cancel your order before: " + formatter.format(canceltoday));
            pw.print("</h2></div></div></div></div>");
            utility.printHtml("Footer.html");
        } else {
            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Order</a>");
            pw.print("</h2><div class='entry'>");

            pw.print("<h4 style='color:red'>Please enter valid address and credit card number</h4>");
            pw.print("</h2></div></div></div>");
            utility.printHtml("Footer.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
    }
}
