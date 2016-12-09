package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mainAPI.Photo;
import mainAPI.Post;


public class TestGet {

	@Test
	public void testGetOne()   throws ClientProtocolException, IOException{
		HttpUriRequest request = new HttpGet( "http://jsonplaceholder.typicode.com/posts/1" );
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		if (httpResponse.getStatusLine().getStatusCode()==200) {

			Gson  gson=new Gson();
			String  json=EntityUtils.toString(httpResponse.getEntity());
			Post  post=gson.fromJson(json,  Post.class);
			Assert.assertNotNull(post.getuserId());
			Assert.assertNotNull(post.getid());
			Assert.assertNotNull(post.gettitle());
			Assert.assertNotNull(post.getbody());

		}
	}
	@Test
	public void testGetAll()   throws ClientProtocolException, IOException{
		HttpUriRequest request = new HttpGet( "http://jsonplaceholder.typicode.com/posts" );
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		AssertJUnit.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
		String  json=EntityUtils.toString(httpResponse.getEntity());
		Gson  gson=new Gson();
		List<Post> posts=gson.fromJson(json,  new TypeToken<Collection<Post>>() {}.getType());
		for (Post post : posts) {
			Assert.assertNotNull(post.getuserId());
			Assert.assertNotNull(post.getid());
			Assert.assertNotNull(post.gettitle());
			Assert.assertNotNull(post.getbody());
		}
	}
	@Test
	public void testGetFiltering()   throws ClientProtocolException, IOException{
		HttpUriRequest request = new HttpGet( "http://jsonplaceholder.typicode.com/posts?userId=1" );
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		AssertJUnit.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
		String  json=EntityUtils.toString(httpResponse.getEntity());
		Gson  gson=new Gson();
		List<Post> posts=gson.fromJson(json,  new TypeToken<Collection<Post>>() {}.getType());
		for (Post post : posts) {
			AssertJUnit.assertEquals(post.getuserId(), "1");
		}
	} 

	@Test
	public void testGetNested()   throws ClientProtocolException, IOException{
		HttpUriRequest request = new HttpGet( "http://jsonplaceholder.typicode.com/albums/1/photos" );
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		AssertJUnit.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
		String  json=EntityUtils.toString(httpResponse.getEntity());
		Gson  gson=new Gson();
		List<Photo> photos=gson.fromJson(json,  new TypeToken<Collection<Photo>>() {}.getType());
		for (Photo photo : photos) {
			AssertJUnit.assertEquals(photo.getualbumId(), "1");
		}
	}

	@Test
	public void testOutofRange()   throws ClientProtocolException, IOException{
		HttpUriRequest request = new HttpGet( "http://jsonplaceholder.typicode.com/posts/120" );
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		AssertJUnit.assertEquals(httpResponse.getStatusLine().getStatusCode(), 404);

	}
}