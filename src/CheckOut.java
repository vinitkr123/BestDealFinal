import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities Utility = new Utilities(request, pw);
        storeOrders(request, response);
    }

    protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter printwiter = response.getWriter();
            Utilities utility = new Utilities(request, printwiter);
            if (!utility.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to add items to cart");
                response.sendRedirect("Login");
                return;
            }
            HttpSession session = request.getSession();

            //get the order product details	on clicking submit the form will be passed to submitorder page

            String userName = session.getAttribute("username").toString();
            String orderTotal = request.getParameter("orderTotal");
            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            printwiter.print("<form name ='CheckOut' action='Payment' method='post'>");
            printwiter.print("<div id='content'><div class='post'><h2 class='title meta'>");
            printwiter.print("<a style='font-size: 24px;'>Order</a>");
            printwiter.print("</h2><div class='entry'>");
            printwiter.print("<table  class='table table-striped'><tr><td>Customer Name:</td><td>");
            printwiter.print(userName);
            printwiter.print("</td></tr>");
            // for each order iterate and display the order name price
            for (OrderItem oi : utility.getCustomerOrders()) {
                printwiter.print("<tr><td> Product Purchased:</td><td>");
                printwiter.print(oi.getName() + "</td></tr><tr><td>");
                printwiter.print("<input type='hidden' name='orderPrice' value='" + oi.getPrice() + "'>");
                printwiter.print("<input type='hidden' name='orderName' value='" + oi.getName() + "'>");
                printwiter.print("Product Price:</td><td>" + oi.getPrice());
                printwiter.print("</td></tr>");
                printwiter.print("<tr><td>Discount on Product:</td><td>" + oi.getDiscount());
                printwiter.print("</td></tr>");
            }
            printwiter.print("<tr><td>");
            printwiter.print("Total Order Cost</td><td>" + orderTotal);
            printwiter.print("<input type='hidden'  name='orderTotal' value='" + orderTotal + "'>");
            printwiter.print("</td></tr></table><table><tr></tr><tr></tr>");
            printwiter.print("<tr><td>");
            printwiter.print("Credit/accountNo</td>");
            printwiter.print("<td><input type='text' name='creditCardNo' class ='form-control'> ");
            printwiter.print("</td></tr>");
            printwiter.print("<tr><td>");
            printwiter.print("Customer Address</td>");
            printwiter.print("<td><input type='text' name='userAddress' class ='form-control'>");
            printwiter.print("</td></tr>");
            printwiter.print("<tr><td colspan='2'>");
            printwiter.print("<input type='submit' name='submit' class='btn btn-success'>");
            printwiter.print("</td></tr></table></form>");
            printwiter.print("</div></div></div>");
            utility.printHtml("Footer.html");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
    }
}
