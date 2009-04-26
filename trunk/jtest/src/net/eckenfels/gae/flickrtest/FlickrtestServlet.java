/* Copyright 2009, Bernd Eckenfels, Germany
 * 
 * This prototype code is under the GPL
 * 
 */
package net.eckenfels.gae.flickrtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FlickrtestServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException 
	{
		String frob = req.getParameter("frob"); // sanitize!

		if (frob == null || frob.trim().length() < 5) {
			doWelcome(req, resp);
			return;
		}
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();

		String secret = "SECRET"; // TODO
		String apikey = "3d938a92a71b43d6f04841d48bedf698";
		String method = "flickr.auth.getToken";

		String call = "api_key" + apikey + "frob" + frob + "method" + method;
		String sig = asMD5(secret + call);

		writer.println("The callback Frob=" + frob);

		String REST = "http://api.flickr.com/services/rest/?";
		String urlStr = REST + "method=" + method + "&api_key=" + apikey + "&frob=" + frob + "&api_sig=" + sig;

		writer.println("The new url = " + urlStr);

		// call URL with GET and read response back to client
		try {
		URL url = new URL(urlStr);
		InputStream is = url.openStream();
		BufferedReader b = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while((line = b.readLine()) != null)
			writer.println("> " + line);
		b.close(); 
		} catch (Exception e) {
			writer.println("Error while connection to " + urlStr + ": " + e);
			e.printStackTrace(writer);
		}
		writer.close();
	}


	private void doWelcome(HttpServletRequest reqArg,
			HttpServletResponse respArg) throws IOException 
	{
		String auth = "http://flickr.com/services/auth/?api_key=3d938a92a71b43d6f04841d48bedf698&perms=read&api_sig=1f72bff54d65b59e302c7ed8fc2b409d";
		respArg.setContentType("text/html");
		PrintWriter writer = respArg.getWriter();
		writer.println("<html><head><title>Welcome</title></head><body>");
		writer.println("<h1>Welcome Stranger</h1><p>This application shows you how to read from Flickr without requiring any login or password from you.</p>");
		writer.println("<p>Click here to request authorisation: <a href=\"" + auth +"\">Flickr/authenticate</a></p><p><a href=\"mailto:bernd.eckenfels@gmail.com\">Bernd.Eckenfels</a></p></body></html>");
		writer.close();
	}


	private String asMD5(String in) 
	{
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("md5 failed", e);
		}
		md5.reset(); 
		md5.update(in.getBytes()); // ASCII only
		byte[] digest = md5.digest();
		StringBuffer result = new StringBuffer();
		String hx;
		for (int i=0;i<digest.length;i++){
			hx =  Integer.toHexString(0xFF & digest[i]);
			if(hx.length() == 1){hx = "0" + hx;} 
			result.append(hx);
		}
		return result.toString();
	}
}
