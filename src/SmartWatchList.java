
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/SmartWatchList")

public class SmartWatchList extends HttpServlet{

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        /* Checks the Games type whether it is electronicArts or activision or takeTwoInteractive */

        String name = null;
        String CategoryName = request.getParameter("maker");
        HashMap<String, SmartWatch> hm = new HashMap<String, SmartWatch>();

        if (CategoryName == null) {
            hm.putAll(SaxParserDataStore.smartWatchHashMap);
            name = "";
        }else
		{
 		   if(CategoryName.equalsIgnoreCase("Samsung"))
 		   {
 			 for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.smartWatchHashMap.entrySet())
 			 {
 				if(entry.getValue().getRetailer().equals("Samsung"))
 				 {
 					 hm.put(entry.getValue().getId(),entry.getValue());
 				 }
 			 }
 				name = "Samsung";
 		   }
 		   else if(CategoryName.equals("Casio"))
 		    {
 			for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.smartWatchHashMap.entrySet())
 				{
 				 if(entry.getValue().getRetailer().equals("Casio"))
 				 {
 					 hm.put(entry.getValue().getId(),entry.getValue());
 				 }
 				}
 				 name = "Casio";
 			}
 		   else if(CategoryName.equals("LG"))
 		    {
 			for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.smartWatchHashMap.entrySet())
 				{
 				 if(entry.getValue().getRetailer().equals("LG"))
 				 {
 					 hm.put(entry.getValue().getId(),entry.getValue());
 				 }
 				}
 				 name = "LG";
 			}
 		}

		/* Header, Left Navigation Bar are Printed.

		All the Games and Games information are dispalyed in the Content Section

		and then Footer is Printed*/

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>" + name + " Smart Watches</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");
        int i = 1;
        int size = hm.size();
        for (Map.Entry<String, SmartWatch> entry : hm.entrySet()) {
            SmartWatch smartWatch = entry.getValue();
            if (i % 3 == 1) pw.print("<tr>");
            pw.print("<td><div id='shop_item'>");
            pw.print("<h3>" + smartWatch.getName() + "</h3>");
            pw.print("<strong>$ Orignal Price:  "+smartWatch.getPrice()+"</strong><ul>");
			double orignalprice = (double) smartWatch.getPrice();
			double discountedprice = (double)smartWatch.getDiscount();
			System.out.println(orignalprice-discountedprice);
			discountedprice =orignalprice-discountedprice;
			pw.print("<strong style='color:red'>$ Discounted Price: "+(float)discountedprice+"</strong><ul>");
            pw.print("<li id='item'><img src='images/smartWatch/" + smartWatch.getImage() + "' alt='' /></li>");
            pw.print("<li><form method='post' action='Cart'>" +
                    "<input type='hidden' name='name' value='" + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='smartWatch'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' class='btn btn-success' value='Buy Now'></form></li>");
            pw.print("<li><form method='post' action='WriteReview'>" + "<input type='hidden' name='name' value='" + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='smartWatch'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' value='WriteReview' class='btn btn-primary'></form></li>");
            pw.print("<li><form method='post' action='ViewReview'>" + "<input type='hidden' name='name' value='" + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='smartWatch'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' value='ViewReview' class='btn btn-primary'></form></li>");
           
            if(smartWatch.getWarranty().equals("No")) {
				pw.print("<h6 style='color:red'>Retailer\'s Warranty: "+smartWatch.getWarranty()+"</h6><ul>");
			}
				else {
					pw.print("<h6 style='color:green'>Retailer\'s Warranty: "+smartWatch.getWarranty()+"</h6><ul>");
					}
			if(smartWatch.getMrebate().equals("No")) {
				pw.print("<h6 style='color:red'>Manufacturer Rebate: "+smartWatch.getMrebate()+"</h6><ul>");}
				else {
					pw.print("<h6 style='color:green'>Manufacturer Rebate: "+smartWatch.getMrebate()+"</h6><ul>");
					}
            pw.print("</ul></div></td>");
            if (i % 3 == 0 || i == size) pw.print("</tr>");
            i++;
        }
        pw.print("</table></div></div></div>");
        utility.printHtml("Footer.html");

    }
}
