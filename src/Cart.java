import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Cart")

public class Cart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        /* From the HttpServletRequest variable name,type,maker and accessories information are obtained.*/

        Utilities utility = new Utilities(request, pw);
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String maker = request.getParameter("maker");
        String access = request.getParameter("access");

        /* StoreProduct Function stores the Purchased product in Orders HashMap.*/

        utility.storeProduct(name, type, maker, access);
        displayCart(request, response);
    }


    /* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

    protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        Carousel carousel = new Carousel();
        if (!utility.isLoggedin()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to add items to cart");
            response.sendRedirect("Login");
            return;
        }

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        //System.out.println("Cart count: " + utility.CartCount());
        pw.print("<a style='font-size: 24px;'>Cart(" + utility.CartCount() + ")</a>");
        pw.print("</h2><div class='entry'>");
        if (utility.CartCount() > 0) {
            pw.print("<table  class='table table-striped'>");
            int i = 1;
            double total = 0;
            
            for (OrderItem oi : utility.getCustomerOrders()) {
            	double originalprice = (double)oi.getPrice() -(double) oi.getDiscount();
                pw.print("<form name ='CartForm' action='RemoveCart' method='post'>");
                pw.print("<tr>");
                pw.print("<td>" + i + "</td><td>" + oi.getName() + "</td><td>: " + originalprice + "</td>");
                pw.print("<td><input type='submit' name='RemoveCart' value='Delete' class='btn btn-primary' /></td>");
                pw.print("<input type='hidden' name='orderName' value='" + oi.getName() + "'>");
                pw.print("<input type='hidden' name='orderPrice' value='" + oi.getPrice() + "'>");
                pw.print("</tr>");
                pw.print("</form>");
                total = total + originalprice;
                i++;
            }

            pw.print("</table></form >");
            pw.print("<form action='CheckOut' method='post'>");

            pw.print("<input type='hidden' name='orderTotal' value='" + total + "'>");
            pw.print("<tr><th></th><th>Total: </th><th>" + total + "</th>");
            pw.print("<tr><td></td><td></td><td>");
            pw.print("&nbsp&nbsp<input type='submit' name='CheckOut' value='CheckOut' class='btn btn-primary' /></td>");
//            pw.print("</form>");
            /* This code is calling Carousel.java code to implement carousel feature*/
            pw.print(carousel.carouselfeature(utility));
        } else {
            pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
        }
        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);

        displayCart(request, response);
    }
}
