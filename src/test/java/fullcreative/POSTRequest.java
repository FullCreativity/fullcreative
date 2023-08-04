package fullcreative;


import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;



import util.GitUtil;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class POSTRequest {

	
	@Test(priority = 1)
	   
    public void CreateRepository_request()
    {
		try {	
		GitUtil auaUtil = new GitUtil();
		
		//Create  repository
		Response res=auaUtil.CreateRepository_request();
		if(res!=null)
		{
			System.out.println("Response"+res);
		}
		
		if(res.getStatusCode()==201)
		{
			System.out.print("Repository created successfully:"+res.getStatusCode());
		}
		else
		{
			System.out.print("Repository not created failed with status code :"+res.getStatusCode());
			Assert.assertTrue(false);
		}	
		
		JsonPath j = new JsonPath(res.asString());
		String Repo_Name=j.getString("name");
		
		
		String RepoPath=j.getString("full_name");
		if (Repo_Name !=null && RepoPath!=null)
		{
			System.out.println("Repository created with name: "+Repo_Name);
			System.out.println("full path of the repository:"+RepoPath);
		}
		
		String Orgnizaltion_name=j.getString("owner.login");
		if(Orgnizaltion_name !=null)
		{
			System.out.println("Repository created in orgnization: "+Orgnizaltion_name);
		}
		
		
//		create 2 issues
		for (int i=0;i<2;i++)
		{
			res=auaUtil.CreateIssue_request(RepoPath);
			if(res.getStatusCode()==201)
			{
				System.out.print("issue created successfully:"+res.getStatusCode());
			}
			else
			{
				System.out.print("Error while creating issue check manually :"+res.getStatusCode());
				Assert.assertTrue(false);
			}
		}
		
		
		
//		get issue details  
		
		res=auaUtil.getIssue_request(RepoPath);
		if(res!=null)
		{
			System.out.println("Response"+res);
		}
		
		if(res.getStatusCode()==200)
		{
			System.out.print("Issue details:"+res.getStatusCode());
		}
		else
		{
			System.out.print("Issue details not recieved  :"+res.getStatusCode());
			Assert.assertTrue(false);
		}		
		
		 j = new JsonPath(res.asString());
		String Issue_num=j.getString("number");
		if(Issue_num !=null)
		{
			System.out.println("Issue number:"+Issue_num);
		}
		String issue_title=j.getString("title");
		if(issue_title !=null)
		{
			System.out.println("Issue created with title: "+issue_title);
		}
		
		String UserId=j.getString("user.login");
		if(UserId !=null)
		{
			System.out.println("Login with user:"+UserId);
		}
		
		
//		closed issue  
		
		res=auaUtil.CloseIssue_request(RepoPath,Issue_num);
		if(res!=null)
		{
			System.out.println("Response"+res);
		}
		
		if(res.getStatusCode()==200)
		{
			System.out.print("Issue Close successfully:"+res.getStatusCode());
		}
		else
		{
			System.out.print("Error while closing issue check manually :"+res.getStatusCode());
			Assert.assertTrue(false);
		}		
		
		
	   
    
		
   
    }
		catch(Exception e){
			e.printStackTrace();
		}
    }
}