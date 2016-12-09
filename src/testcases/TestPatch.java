package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import mainAPI.Post;

public class TestPatch {
	  @Test
	  public void testPatch() throws ClientProtocolException, IOException{
		  
		  HttpPatch httppatch = new HttpPatch("http://jsonplaceholder.typicode.com/posts/1");
		  List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		  params.add(new BasicNameValuePair("title", "Hello"));
		  httppatch.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		  HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httppatch);
		  String json=EntityUtils.toString(httpResponse.getEntity());
		  Gson  gson=new Gson();		  
          Post  post=gson.fromJson(json,  Post.class);
          Assert.assertEquals(post.getid(), "1");
          Assert.assertEquals(post.getuserId(), "1");
          Assert.assertEquals(post.gettitle(), "Hello");
          Assert.assertEquals(post.getbody(), "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
	  }
	  
	  @Test
	  public void testPatchData() throws ClientProtocolException, IOException{
		  
		  HttpPatch httppatch = new HttpPatch("http://jsonplaceholder.typicode.com/posts/1");
		  List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		  params.add(new BasicNameValuePair("userId", "12345"));
		  params.add(new BasicNameValuePair("title", "Hello"));
		  params.add(new BasicNameValuePair("res", "Hello"));
		  params.add(new BasicNameValuePair("id", "101"));
		  params.add(new BasicNameValuePair("time", "Hello"));
		  httppatch.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		  HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httppatch);
		  String json=EntityUtils.toString(httpResponse.getEntity());
		  JsonParser parser = new JsonParser();
		  JsonObject jsonObj = parser.parse(json).getAsJsonObject();

          Assert.assertEquals(jsonObj.get("userId").getAsString(), "12345");
          Assert.assertEquals(jsonObj.get("title").getAsString(), "Hello");
          Assert.assertEquals(jsonObj.get("res").getAsString(), "Hello");
          Assert.assertEquals(jsonObj.get("id").getAsString(), "1");
          Assert.assertEquals(jsonObj.get("time").getAsString(), "Hello");
          Assert.assertEquals(jsonObj.get("body").getAsString(), "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
	  }
	  @Test
	  public void testPatchNeg() throws ClientProtocolException, IOException{
		  
		  HttpPatch httppatch = new HttpPatch("http://jsonplaceholder.typicode.com/posts");
		  List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		  params.add(new BasicNameValuePair("title", "Hello"));
		  httppatch.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		  HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httppatch);
		  Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),404);
	  }
}
