import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/VoiceAssistantList")

public class VoiceAssistantList extends HttpServlet {

    /* Accessory Page Displays all the Accessories and their Information in Game Speed */

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        /* Checks the Tablets type whether it is microsft or apple or samsung */

        String name = null;
        String CategoryName = request.getParameter("maker");
        HashMap<String, VoiceAssistant> hm = new HashMap<String, VoiceAssistant>();

        if (CategoryName == null) {
            hm.putAll(SaxParserDataStore.voiceAssistantHashMap);
            name = "";
        } else {
            if (CategoryName.equals("Amazon")) {
                for (Map.Entry<String, VoiceAssistant> entry : SaxParserDataStore.voiceAssistantHashMap.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Amazon")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Amazon";
            } else if (CategoryName.equals("Google")) {
                for (Map.Entry<String, VoiceAssistant> entry : SaxParserDataStore.voiceAssistantHashMap.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Google")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Google";
            }
            else if (CategoryName.equals("Lenovo")) {
                for (Map.Entry<String, VoiceAssistant> entry : SaxParserDataStore.voiceAssistantHashMap.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Lenovo")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Lenovo";
            }
        }

		/* Header, Left Navigation Bar are Printed.

		All the tablets and tablet information are dispalyed in the Content Section

		and then Footer is Printed*/

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>" + name + " Voice Assistants</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");
        int i = 1;
        int size = hm.size();
        for (Map.Entry<String, VoiceAssistant> entry : hm.entrySet()) {
            VoiceAssistant voiceAssistant = entry.getValue();
            if (i % 3 == 1)
                pw.print("<tr>");
            pw.print("<td><div id='shop_item'>");
            pw.print("<h3>" + voiceAssistant.getName() + "</h3>");
            pw.print("<strong>" + voiceAssistant.getPrice() + "$</strong><ul>");
            pw.print("<li id='item'><img src='images/voiceAssistant/"
                    + voiceAssistant.getImage() + "' alt='' /></li>");
            pw.print("<li><form method='post' action='Cart'>" +
                    "<input type='hidden' name='name' value='" + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='voiceAssistant'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' class='btn btn-success' value='Buy Now'></form></li>");
            pw.print("<li><form method='post' action='WriteReview'>" + "<input type='hidden' name='name' value='" + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='voiceAssistant'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' value='WriteReview' class='btn btn-primary'></form></li>");
            pw.print("<li><form method='post' action='ViewReview'>" + "<input type='hidden' name='name' value='" + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='voiceAssistant'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' value='ViewReview' class='btn btn-primary'></form></li>");
           
            if(voiceAssistant.getWarranty().equals("No")) {
				pw.print("<h6 style='color:red'>Retailer\'s Warranty: "+voiceAssistant.getWarranty()+"</h6><ul>");
			}
				else {
					pw.print("<h6 style='color:green'>Retailer\'s Warranty: "+voiceAssistant.getWarranty()+"</h6><ul>");
					}
			if(voiceAssistant.getMrebate().equals("No")) {
				pw.print("<h6 style='color:red'>Manufacturer Rebate: "+voiceAssistant.getMrebate()+"</h6><ul>");}
				else {
					pw.print("<h6 style='color:green'>Manufacturer Rebate: "+voiceAssistant.getMrebate()+"</h6><ul>");
					}
            pw.print("</ul></div></td>");
            if (i % 3 == 0 || i == size)
                pw.print("</tr>");
            i++;
        }
        pw.print("</table></div></div></div>");
        utility.printHtml("Footer.html");
    }
}
