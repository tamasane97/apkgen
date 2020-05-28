package web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.http.impl.client.*;
import org.json.simple.JSONObject;


@WebServlet
public class rundeckTrigger extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		String urlLink = req.getParameter("urlLink");
		String appName = req.getParameter("appName");
		String email = req.getParameter("emailid");
		ConnectionFactory factory=new ConnectionFactory();

		try
		{
			factory.setHost("159.65.158.218");
			factory.setPort(5672);
			factory.setUsername("admin");
			factory.setPassword("admin");
			Connection connection=factory.newConnection();
			Channel channel=connection.createChannel();
			channel.queueDeclare("apkGenQueue",false,false,false,null);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("urlLink",urlLink);
			jsonObject.put("appName",appName);
			jsonObject.put("emailid",email);
			System.out.println(urlLink);
			System.out.println(appName);
			System.out.println(email);
			channel.basicPublish("","apkGenQueue",false,null,jsonObject.toString().getBytes());
			System.out.println("msg sent");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		PrintWriter out = response.getWriter();
		out.println(request_recvd_msg);
    }


    final String request_recvd_msg = "<!doctype html>\n" +
			"<html lang=\"en\">\n" +
			"<head>\n" +
			"    <meta charset=\"utf-8\">\n" +
			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
			"    <meta name=\"description\" content=\"\">\n" +
			"    <meta name=\"author\" content=\"\">\n" +
			"    <link rel=\"icon\" href=\"icon/favicon.ico\">\n" +
			"\n" +
			"    <title>APKGen :: Android App Generator</title>\n" +
			"\n" +
			"\n" +
			"    <link href=\"style.css\" rel=\"stylesheet\">\n" +
			"</head>\n" +
			"\n" +
			"<body class=\"text-center\">\n" +
			"\n" +
			"\n" +
			"<header>\n" +
			"    <nav class=\"navbar navbar-expand-md navbar-dark fixed-top bg-dark\">\n" +
			"        <img src=\"icon/favicon.ico\" width=\"60\" height=\"60\"/>\n" +
			"        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarCollapse\" aria-controls=\"navbarCollapse\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
			"            <span class=\"navbar-toggler-icon\"></span>\n" +
			"        </button>\n" +
			"        <div class=\"collapse navbar-collapse\" id=\"navbarCollapse\">\n" +
			"            <ul class=\"navbar-nav mr-auto\">\n" +
			"                <li class=\"nav-item\">\n" +
			"                    <a class=\"nav-link\" href=\"index.html\">Home <span class=\"sr-only\">(current)</span></a>\n" +
			"                </li>\n" +
			"                <li class=\"nav-item\">\n" +
			"                    <a class=\"nav-link\" onclick=\"window.open('https://github.com/tamasane/apkgen');\">Github</a>\n" +
			"                </li>\n" +
			"                <li class=\"nav-item\">\n" +
			"                    <a class=\"nav-link\" href=\"aboutus.html\">About Us</a>\n" +
			"                </li>\n" +
			"            </ul>\n" +
			"        </div>\n" +
			"    </nav>\n" +
			"</header>\n" +
			"\n" +
			"<div class=\"jumbotron\">\n" +
			"    <div class=\"container\">\n" +
			"        <h1 class=\"display-3\">Request Recieved!</h1>\n" +
			"        <p><br/><br/>We have recieved your request. Sit back, have a Coffee and <br/>check your Email, while our magic elves stitch your application!<br/><br/> Have a nice day &#128522; ! <br/><br/></p>\n" +
			"        <p><a class=\"btn btn-primary btn-lg\" onclick=\"window.open('http://apkgen.digital','_self','');\" role=\"button\">Make another App? Click here &raquo;</a></p>\n" +
			"    </div>\n" +
			"    <p class=\"mt-5 mb-3 text-muted\">APK Gen &copy; 2020</p>\n" +
			"</div>\n" +
			"\n" +
			"<script src=\"index.jsp\" type=\"text/javascript\"> </script>\n" +
			"<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n" +
			"<script src=\"popper.min.js\"></script>\n" +
			"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n" +
			"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\n" +
			"\n" +
			"</body>\n" +
			"</html>\n";

}
