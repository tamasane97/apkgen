package web;

import java.io.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.rundeck.api.*;
import org.rundeck.api.domain.*;
import org.rundeck.api.domain.RundeckExecution.ExecutionStatus;

@WebServlet
public class rundeckTrigger extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	
	private void triggerMyJob(String appUrl, String appName) {
		RundeckClient rundeck = RundeckClient.builder().url("http://206.189.134.237:4440/").login("admin", "admin1234").build();
		RundeckJob job = rundeck.getJob("debf006b-4bc5-4934-bdde-9b26eec54145");
		RundeckExecution exec = rundeck.runJob(RunJobBuilder.builder()
				.setOptions(new OptionsBuilder().addOption("APPURL", appUrl).addOption("APPNAME", appName).toProperties())
				.setJobId(job.getId())
				.build(), 10L, TimeUnit.SECONDS);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		String urlLink = req.getParameter("urlLink");
		String appName = req.getParameter("appName");
		String[] appUrl = urlLink.replaceAll("/", "").trim().split("\\.");
		try {
			String FILE_URL = "http://142.93.210.19:8000/"+appName+appUrl[1]+".apk";
	        InputStream in = new URL( FILE_URL ).openStream();
	        response.setContentType("application/vnd.android.package-archive");
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", appName+".apk");
	        response.setHeader(headerKey, headerValue);        
	        IOUtils.copy(in, response.getOutputStream());   
		}
		catch(Exception e) {
			this.triggerMyJob(urlLink, appName);
			String FILE_URL = "http://142.93.210.19:8000/"+appName+appUrl[1]+".apk";
	        InputStream in = new URL( FILE_URL ).openStream();
	        response.setContentType("application/vnd.android.package-archive");
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", appName+".apk");
	        response.setHeader(headerKey, headerValue);        
	        IOUtils.copy(in, response.getOutputStream());
		}        
    }

}
