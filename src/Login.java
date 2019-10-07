import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet("/Login")
public class Login extends HttpServlet {
	

	MySQLDataStoreUtilities dataStoreUtilities = new MySQLDataStoreUtilities();
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        HashMap<String, User> hm = new HashMap<String, User>();
        String TOMCAT_HOME = System.getProperty("catalina.home");
      
        try {
        	
        	hm=MySQLDataStoreUtilities.selectloginUser(username, "Registration",userType);
        	if(hm.containsKey(username))
        	{
        		System.out.println("User is a valid user");
        	}
        	//FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME + "/webapps/vinit/UserDetails.txt"));
            //ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            //System.out.println("User Info: " + objectInputStream.toString());
            //hm = (HashMap) objectInputStream.readObject(); 
           // hm = MySQLDataStoreUtilities.selectUser(username, "Registration");
        } catch (Exception e) {
        	e.printStackTrace();

        }
        User user = hm.get(username);
        if (user != null) { 
            String user_password = user.getPassword();
            if (password.equals(user_password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("userType", userType);
                
                if (userType.equalsIgnoreCase("Customer")) {
                    response.sendRedirect("Home");
                    return;
                } else if (userType.equalsIgnoreCase("StoreManager")) {
                    response.sendRedirect("StoreManagerHome");
                    return;

                } else if (userType.equalsIgnoreCase("Salesman")) {
                    response.sendRedirect("SalesmanHome");
                    return;
                }
            }
        }
        displayLogin(request, response, pw, true);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayLogin(request, response, pw, false);
    }


    /*  Login Screen is Displayed, Registered User specifies credentials and logins into the Game Speed Application. */
    protected void displayLogin(HttpServletRequest request,
                                HttpServletResponse response, PrintWriter pw, boolean error)
            throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        pw.print("<div class='post' style='float: none; width: 85%'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Login</a></h2>"
                + "<div class='entry'>"
                + "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
        if (error)
            pw.print("<h4 style='color:red'>Please check your username, password and user type!</h4>");
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login_msg") != null) {
            pw.print("<h4 style='color:red'>" + session.getAttribute("login_msg") + "</h4>");
            session.removeAttribute("login_msg");
        }
        pw.print("<form method='post' action='Login'>"
                + "<table class ='table' style='width:100%'><tr><td>"
                + "<h3>Username</h3></td><td><input type='text' name='username' value='' class='form-control' required></input>"
                + "</td></tr><tr><td>"
                + "<h3>Password</h3></td><td><input type='password' name='password' value='' class='form-control' required></input>"
                + "</td></tr><tr><td>"
                + "<h3>User Type</h3></td><td><select name='userType' class='form-control'><option value='Customer' selected>Customer</option><option value='StoreManager'>Store Manager</option><option value='Salesman'>Salesman</option></select>"
                + "</td></tr><tr><td></td><td>"
                + "<input type='submit' class='btn btn-primary' value='Login' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
                + "</td></tr><tr><td></td><td>"
                + "<strong><a class='' href='Registration' style='float: right;height: 20px margin: 20px;'>New User? Register here!</a></strong>"
                + "</td></tr></table>"
                + "</form>" + "</div></div></div>");
        utility.printHtml("Footer.html");
    }
}
