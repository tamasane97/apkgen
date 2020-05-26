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
			factory.setPort(15672);
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
		out.println("Your request is being processed. Your app will be generated within an hour and sent on your email. ");
    }

}
