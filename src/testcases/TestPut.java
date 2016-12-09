package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import mainAPI.Post;

public class TestPut {
	  @Test
	  public void testPut() throws ClientProtocolException, IOException{
		  
		  HttpPut httpput = new HttpPut("http://jsonplaceholder.typicode.com/posts/1");
		  List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		  params.add(new BasicNameValuePair("userId", "1"));
		  params.add(new BasicNameValuePair("title", "Hello"));
		  params.add(new BasicNameValuePair("body", "Hello"));
		  params.add(new BasicNameValuePair("id", "1"));
		  httpput.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		  HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpput);
		  String json=EntityUtils.toString(httpResponse.getEntity());
		  Gson  gson=new Gson();		  
          Post  post=gson.fromJson(json,  Post.class);
          AssertJUnit.assertEquals(post.getid(), "1");
          AssertJUnit.assertEquals(post.getuserId(), "1");
          AssertJUnit.assertEquals(post.gettitle(), "Hello");
          AssertJUnit.assertEquals(post.getbody(), "Hello");
	  }
	  @Test
	  public void testPutData() throws ClientProtocolException, IOException{
		  
		  HttpPut httpput = new HttpPut("http://jsonplaceholder.typicode.com/posts/1");
		  List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		  params.add(new BasicNameValuePair("photo", "101"));
		  params.add(new BasicNameValuePair("time", "10"));
		  params.add(new BasicNameValuePair("userId", "1"));
		  params.add(new BasicNameValuePair("id", "10"));
		  params.add(new BasicNameValuePair("res", "Hello"));
		  httpput.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		  HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpput);
		  String json=EntityUtils.toString(httpResponse.getEntity());
		  JsonParser parser = new JsonParser();
		  JsonObject jsonObj = parser.parse(json).getAsJsonObject();
          Assert.assertEquals(jsonObj.get("photo").getAsString(), "101");
          Assert.assertEquals(jsonObj.get("time").getAsString(), "10");
          Assert.assertEquals(jsonObj.get("userId").getAsString(), "1");
          Assert.assertEquals(jsonObj.get("id").getAsString(), "1");
          Assert.assertEquals(jsonObj.get("res").getAsString(), "Hello");
	  }
	  @Test
	  public void testPutNeg() throws ClientProtocolException, IOException{
		  
		  HttpPut httpput = new HttpPut("http://jsonplaceholder.typicode.com/posts");
		  List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		  params.add(new BasicNameValuePair("userId", "1"));
		  params.add(new BasicNameValuePair("title", "Hello"));
		  params.add(new BasicNameValuePair("body", "Hello"));
		  params.add(new BasicNameValuePair("id", "1"));
		  httpput.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		  HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpput);
		  Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),404);

	  }
}
