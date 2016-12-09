package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import mainAPI.Post;

public class TestPost {
	@Test
	  public void testPost() throws ClientProtocolException, IOException{
		  
		  HttpPost httppost = new HttpPost("http://jsonplaceholder.typicode.com/posts");
		  List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		  params.add(new BasicNameValuePair("userId", "12345"));
		  params.add(new BasicNameValuePair("title", "Hello"));
		  params.add(new BasicNameValuePair("body", "Hello"));
		  httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		  HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httppost);
		  String json=EntityUtils.toString(httpResponse.getEntity());
		  Gson  gson=new Gson();		  
          Post  post=gson.fromJson(json,  Post.class);
          AssertJUnit.assertEquals(post.getid(), "101");
          AssertJUnit.assertEquals(post.getuserId(), "12345");
          AssertJUnit.assertEquals(post.gettitle(), "Hello");
          AssertJUnit.assertEquals(post.getbody(), "Hello");
          
	  }
	@Test
	  public void testPostData() throws ClientProtocolException, IOException{
		  
		  HttpPost httppost = new HttpPost("http://jsonplaceholder.typicode.com/posts");
		  List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		  params.add(new BasicNameValuePair("res", "12345"));
		  params.add(new BasicNameValuePair("userId", "12345"));
		  params.add(new BasicNameValuePair("title", "Hello"));
		  params.add(new BasicNameValuePair("body", "Hello"));
		  httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		  HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httppost);
		  String json=EntityUtils.toString(httpResponse.getEntity());
		  JsonParser parser = new JsonParser();
		  JsonObject jsonObj = parser.parse(json).getAsJsonObject();

          Assert.assertEquals(jsonObj.get("res").getAsString(), "12345");
          Assert.assertEquals(jsonObj.get("userId").getAsString(), "12345");
          Assert.assertEquals(jsonObj.get("title").getAsString(), "Hello");
          Assert.assertEquals(jsonObj.get("body").getAsString(), "Hello");
          Assert.assertEquals(jsonObj.get("id").getAsString(), "101");
          
	  }
}
