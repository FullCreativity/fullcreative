package util;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GitUtil {

	 String token="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";
	public Response CreateRepository_request()
	{
		  try {
			  Random random = new Random();

			  int rand = random.nextInt();
			  String jsonString = "{\"name\" : \"Pragati"+rand+"\"}";
			    
			  
				RestAssured.useRelaxedHTTPSValidation();
				Response res =  given()
						            .header("Content-Type", "application/json") //;charset=UTF-8
			    		   			.header("Accept", "application/vnd.github+json")
				                        .header("Authorization", "Bearer "+token)
				                        .body(jsonString)
			                        .post("https://api.github.com/orgs/FullCreativity/repos");
			                        
				   System.out.println(res.asString());
				  
				
    		   
    		   return res;
    	   }
    	   catch(Exception e)
    	   {
    		   e.printStackTrace();
    		   System.out.println("error");
    		   return null;
    	   }
	}
	
	public Response CreateIssue_request(String Path)
	{
		  try {
			  Random random = new Random();

			  int rand = random.nextInt();
			  String jsonString = "{\r\n"
			  		+ "  \"title\": \"Found a defect\",\r\n"
			  		+ "  \"body\": \"I'm having a problem db.\"\r\n"
			  		+ "}";
			    
			  
				RestAssured.useRelaxedHTTPSValidation();
				Response res =  given()
						            .header("Content-Type", "application/json") //;charset=UTF-8
			    		   			.header("Accept", "application/vnd.github+json")
				                        .header("Authorization", "Bearer "+token)
				                        .body(jsonString)
			                        .post("https://api.github.com/repos/"+Path+"/issues");
			                        
				   System.out.println(res.asString());
			
    		   return res;
    	   }
    	   catch(Exception e)
    	   {
    		   e.printStackTrace();
    		   System.out.println("error");
    		   return null;
    	   }
	}

	public Response getIssue_request(String Path)
	{
		  try {
			
				RestAssured.useRelaxedHTTPSValidation();
				Response res =  given()
						            .header("Content-Type", "application/json") //;charset=UTF-8
			    		   			.header("Accept", "application/vnd.github+json")
				                        .header("Authorization", "Bearer "+token)   
			                        .get("https://api.github.com/repos/"+Path+"/issues/1");
			                        
				   System.out.println(res.asString());
			
    		   return res;
    	   }
    	   catch(Exception e)
    	   {
    		   e.printStackTrace();
    		   System.out.println("error");
    		   return null;
    	   }
	}
	
	
	public Response CloseIssue_request(String Path,String IssueNu)
	{
		  try {
			
			  String jsonString = "{\r\n"
			  		+ "\"state\": \"closed\"\r\n"
			  		+ "}";
				    
				RestAssured.useRelaxedHTTPSValidation();
				Response res =  given()
						            .header("Content-Type", "application/json") //;charset=UTF-8
			    		   			.header("Accept", "application/vnd.github+json")
				                        .header("Authorization", "Bearer "+token)  
				                        .body(jsonString)
			                        .post("https://api.github.com/repos/"+Path+"/issues/"+IssueNu);
			                        
				   System.out.println(res.asString());
			
    		   return res;
    	   }
    	   catch(Exception e)
    	   {
    		   e.printStackTrace();
    		   System.out.println("error");
    		   return null;
    	   }
	}


}