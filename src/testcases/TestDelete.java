package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;

public class TestDelete {
	  @Test
	  public void testDelete() throws ClientProtocolException, IOException{
		  
		  HttpDelete httpdelete = new HttpDelete("http://jsonplaceholder.typicode.com/posts/1");
		  HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpdelete);
		  AssertJUnit.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
	  }
	  @Test
	  public void testDeleteNeg() throws ClientProtocolException, IOException{
		  
		  HttpDelete httpdelete = new HttpDelete("http://jsonplaceholder.typicode.com/posts");
		  HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpdelete);
		  AssertJUnit.assertEquals(httpResponse.getStatusLine().getStatusCode(), 404);
	  }
}
