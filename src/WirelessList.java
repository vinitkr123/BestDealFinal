import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WirelessList")

public class WirelessList extends HttpServlet {

	/* Console Page Displays all the Consoles and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, Wireless> hm = new HashMap<String, Wireless>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.mapWireless);
			name = "";
		}
		else
		{
		   if(CategoryName.equalsIgnoreCase("Mint"))
		   {
			 for(Map.Entry<String,Wireless> entry : SaxParserDataStore.mapWireless.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Mint"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Mint";
		   }
		   else if(CategoryName.equals("ATT"))
		    {
			for(Map.Entry<String,Wireless> entry : SaxParserDataStore.mapWireless.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("ATT"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "ATT";
			}
		   else if(CategoryName.equals("Xfinity"))
		    {
			for(Map.Entry<String,Wireless> entry : SaxParserDataStore.mapWireless.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Xfinity"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Xfinity";
			}
		}

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Consoles</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Wireless> entry : hm.entrySet())
		{
			Wireless wireless= entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+wireless.getName()+"</h3>");
			pw.print("<strong>$ Orignal Price:  "+wireless.getPrice()+"</strong><ul>");
			double orignalprice = (double) wireless.getPrice();
			double discountedprice = (double)wireless.getDiscount();
			System.out.println(orignalprice-discountedprice);
			discountedprice =orignalprice-discountedprice;
			pw.print("<strong style='color:red'>$ Discounted Price:  "+(float)discountedprice+"</strong><ul>");
			pw.print("<li id='item' ><img src='images/wireless/"+wireless.getImage()+"' alt='' style=\"height: 150px;width:100px\"/></li>");
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='Wireless'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-success' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='Wireless'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btn btn-primary'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='Wireless'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btn btn-primary'></form></li>");
		
			if(wireless.getWarranty().equals("No")) {
				pw.print("<h6 style='color:red'>Retailer\'s Warranty: "+wireless.getWarranty()+"</h6><ul>");
			}
				else {
					pw.print("<h6 style='color:green'>Retailer\'s Warranty: "+wireless.getWarranty()+"</h6><ul>");
					}
			if(wireless.getMrebate().equals("No")) {
				pw.print("<h6 style='color:red'>Manufacturer Rebate: "+wireless.getMrebate()+"</h6><ul>");}
				else {
					pw.print("<h6 style='color:green'>Manufacturer Rebate: "+wireless.getMrebate()+"</h6><ul>");
					}
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
