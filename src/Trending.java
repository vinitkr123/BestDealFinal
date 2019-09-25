import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

    /* Games Page Displays all the Games and their Information in Game Speed */

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        /* Checks the Games type whether it is electronicArts or activision or takeTwoInteractive */

        String name = null;
        String CategoryName = request.getParameter("maker");
        HashMap<String, Phone> hm = new HashMap<String, Phone>();

        if (CategoryName == null) {
            hm.putAll(SaxParserDataStore.mapPhoneList);
            name = "";
        } else {
            if (CategoryName.equals("Apple")) {
                for (Map.Entry<String, Phone> entry : SaxParserDataStore.mapPhoneList.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Apple")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Apple";
            } else if (CategoryName.equals("Samsung")) {
                for (Map.Entry<String, Phone> entry : SaxParserDataStore.mapPhoneList.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Samsung")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Samsung";
            }
            else if (CategoryName.equals("Motorola")) {
                for (Map.Entry<String, Phone> entry : SaxParserDataStore.mapPhoneList.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Motorola")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Motorola";
            }
            else if (CategoryName.equals("Nokia")) {
                for (Map.Entry<String, Phone> entry : SaxParserDataStore.mapPhoneList.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Nokia")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Nokia";
            }
        }

		/* Header, Left Navigation Bar are Printed.

		All the Games and Games information are dispalyed in the Content Section

		and then Footer is Printed*/

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>" + name + " Trending</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");
        int i = 1;
        int size = hm.size();
        for (Map.Entry<String, Phone> entry : hm.entrySet()) {
            Phone phone = entry.getValue();
            if (i % 3 == 1) pw.print("<tr>");
            pw.print("<td><div id='shop_item'>");
            pw.print("<h3>" + phone.getName() + "</h3>");
            pw.print("<strong>$ Orignal Price:  "+phone.getPrice()+"</strong><ul>");
			double orignalprice = (double) phone.getPrice();
			double discountedprice = (double)phone.getDiscount();
			System.out.println(orignalprice-discountedprice);
			discountedprice =orignalprice-discountedprice;
			pw.print("<strong style='color:red'>$ Discounted Price:  "+(float)discountedprice+"</strong><ul>");
            pw.print("<li id='item'><img src='images/phone/" + phone.getImage() + "' alt='' /></li>");
            pw.print("<li><form method='post' action='Cart'>" +
                    "<input type='hidden' name='name' value='" + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='phone'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' class='btn btn-success' value='Buy Now'></form></li>");
            pw.print("<li><form method='post' action='WriteReview'>" + "<input type='hidden' name='name' value='" + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='phone'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' value='WriteReview' class='btn btn-primary'></form></li>");
            pw.print("<li><form method='post' action='ViewReview'>" + "<input type='hidden' name='name' value='" + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='phone'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' value='ViewReview' class='btn btn-primary'></form></li>");
        
            if(phone.getWarranty().equals("No")) {
				pw.print("<h6 style='color:red'>Retailer\'s Warranty: "+phone.getWarranty()+"</h6><ul>");
			}
				else {
					pw.print("<h6 style='color:green'>Retailer\'s Warranty: "+phone.getWarranty()+"</h6><ul>");
					}
			if(phone.getMrebate().equals("No")) {
				pw.print("<h6 style='color:red'>Manufacturer Rebate: "+phone.getMrebate()+"</h6><ul>");}
				else {
					pw.print("<h6 style='color:green'>Manufacturer Rebate: "+phone.getMrebate()+"</h6><ul>");
					}
            pw.print("</ul></div></td>");
            if (i % 3 == 0 || i == size) pw.print("</tr>");
            i++;
        }
        pw.print("</table></div></div></div>");
        utility.printHtml("Footer.html");

    }

}
