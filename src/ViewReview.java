
import java.io.IOException;
import java.io.PrintWriter;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewReview")

public class ViewReview extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		review(request, response);
	}

	protected void review(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			if (!utility.isLoggedin()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("login_msg", "Please Login to view Review");
				response.sendRedirect("Login");
				return;
			}
			String productName = request.getParameter("name");
			HashMap<String, ArrayList<Review>> hm = MongoDBDataStoreUtilities.selectReview();
			String userName = "";
			String reviewRating = "";
			String reviewDate;
			String reviewText = "";
			String price = "";
			String city = "";
			
			String producttype="";
			String retailername = "";
			String retailerstate = "";
			String productonsale = "";
			String manufacturerrebate = "";
			String userage = "";
			String usergender = "";
			String useroccupation = "";

			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");

			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Review</a>");
			pw.print("</h2><div class='entry'>");

			// if there are no reviews for product print no review else iterate over all the
			// reviews using cursor and print the reviews in a table
			if (hm == null) {
				pw.println("<h2>Mongo Db server is not up and running</h2>");
			} else {
				if (!hm.containsKey(productName)) {
					pw.println("<h2>There are no reviews for this product.</h2>");
				} else {
					for (Review r : hm.get(productName)) {
						pw.print("<table class='gridtable'>");
						pw.print("<tr>");
						pw.print("<td> Product Name: </td>");
						productName = r.getProductName();
						pw.print("<td>" + productName + "</td>");
						pw.print("</tr>");
						
						pw.print("<tr>");
						pw.print("<td> Product Category: </td>");
						producttype = r.getProductType();
						pw.print("<td>" + producttype + "</td>");
						pw.print("</tr>");
						
						pw.print("<tr>");
						pw.print("<td> Product Price: </td>");
						price = r.getPrice();
						pw.print("<td>" + price + "</td>");
						pw.print("</tr>");
						pw.print("<tr>");
						pw.print("<tr>");
						pw.print("<td> Retailer Name: </td>");
						retailername = r.getRetailername();
						pw.print("<td>" + retailername+ "</td>");
						pw.print("</tr>");
						
						pw.print("<tr>");
						pw.print("<td> Retailer Zip: </td>");
						retailername = r.getRetailerpin();
						pw.print("<td>" + retailername+ "</td>");
						pw.print("</tr>");
						pw.print("<td> Retailer City: </td>");
						city = r.getRetailerCity();
						pw.print("<td>" + city + "</td>");
						pw.print("</tr>");
						
						
						
						
						
						pw.print("<tr>");
						pw.print("<td> Retailer State: </td>");
						retailerstate = r.getRetailerstate();
						pw.print("<td>" + retailerstate + "</td>");
						pw.print("</tr>");
						
						pw.print("<tr>");
						pw.print("<td> Product on Sale: </td>");
						productonsale = r.getProductonsale();
						pw.print("<td>" + productonsale + "</td>");
						pw.print("</tr>");
						
						pw.print("<tr>");
						pw.print("<td> Manufacturer Name: </td>");
						String manufacturername = r.getProductMaker();
						pw.print("<td>" + manufacturername + "</td>");
						pw.print("</tr>");
						
						pw.print("<tr>");
						pw.print("<td> Manufacturer Rebate: </td>");
						manufacturerrebate = r.getManufacturerrebate();
						pw.print("<td>" + manufacturerrebate + "</td>");
						pw.print("</tr>");
						pw.print("<tr>");
						pw.print("<td> User Name: </td>");
						userName = r.getUserName();
						pw.print("<td>" + userName + "</td>");
						pw.print("</tr>");
						pw.print("<tr>");
						pw.print("<td> User Age: </td>");
						userage = r.getUserage();
						pw.print("<td>" + userage + "</td>");
						pw.print("</tr>");
						
						pw.print("<tr>");
						pw.print("<td> User Gender: </td>");
						usergender = r.getUsergender();
						pw.print("<td>" + usergender + "</td>");
						pw.print("</tr>");
						
						pw.print("<tr>");
						pw.print("<td> User Occupation: </td>");
						useroccupation = r.getUseroccupation();
						pw.print("<td>" + useroccupation + "</td>");
						pw.print("</tr>");
						
						
						
						pw.println("<tr>");
						pw.println("<td> Review Rating: </td>");
						reviewRating = r.getReviewRating().toString();
						pw.print("<td>" + reviewRating + "</td>");
						pw.print("</tr>");
						pw.print("<tr>");
						pw.print("<td> Review Date: </td>");
						reviewDate = r.getReviewDate().toString();
						pw.print("<td>" + reviewDate + "</td>");
						pw.print("</tr>");
						pw.print("<tr>");
						pw.print("<td> Review Text: </td>");
						reviewText = r.getReviewText();
						pw.print("<td>" + reviewText + "</td>");
						pw.print("</tr>");
						pw.println("</table>");
					}

				}
			}
			pw.print("</div></div></div>");
			utility.printHtml("Footer.html");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	}
}
