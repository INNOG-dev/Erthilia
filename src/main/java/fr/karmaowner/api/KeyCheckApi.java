package fr.karmaowner.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import fr.karmaowner.erthilia.Main;

public class KeyCheckApi {
	
	private String keycode;
	
	private boolean result = false;
	
	private KeyCheckApi() { }
	
	public static KeyCheckApi startVerification(String keycode)
	{
		KeyCheckApi api = new KeyCheckApi();
		api.keycode = keycode;
		api.result = api.checkKey();
		return api;
	}
	
	public boolean checkKey() {
	    PrintWriter out = null;
	    BufferedReader in = null;
	    String result = "";
	    String postData = "";
	    String ip = "";
		try {
			ip = getServerIP();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			postData = URLEncoder.encode("licencekey", "UTF-8") + "=" + URLEncoder.encode(keycode, "UTF-8");
			postData += "&" + URLEncoder.encode("mod_name", "UTF-8") + "=" + URLEncoder.encode(Main.MODID, "UTF-8");
			postData += "&" + URLEncoder.encode("ip", "UTF-8") + "=" + URLEncoder.encode(ip, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	    try {
	        URL realUrl = new URL("https://www.atonha.fr/api/check.php");
	        URLConnection conn = realUrl.openConnection();
	        conn.setRequestProperty("accept", "*/*");
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

	        
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        out = new PrintWriter(conn.getOutputStream());
	        out.print(postData);
	        out.flush();
	        
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        
	        while ((line = in.readLine()) != null)
	        {
	            result += line;
	        }
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    finally 
	    {
	        try 
	        {
	            if (out != null) out.close();
	            if (in != null) in.close(); 
	        } 
	        catch (IOException ex) 
	        {
	            ex.printStackTrace();
	        }
	    }

	    if(result.contains("false")) return false;
	    
	    return true;
	}
	
	public String getServerIP() throws IOException {
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));

		String ip = in.readLine();

		return ip;
	}
	
	public boolean getResult()
	{
		return this.result;
	}
	
	

}
