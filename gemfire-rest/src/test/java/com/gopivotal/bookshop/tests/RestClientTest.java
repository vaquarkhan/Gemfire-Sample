package com.gopivotal.bookshop.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import antlr.collections.List;

import com.gopivotal.bookshop.domain.BookOrder;


public class RestClientTest {
	// TODO-07: Change the port to 7072. Next, show the view: TCP/IP Monitor and configure using the 
	//          detailed lab instructions. Re-run the test and observe the request & response.
	private static final String BASE_URL = "http://localhost:7071/gemfire-api/v1";
	private RestTemplate restTemplate = new RestTemplate();

	
	@Test
	// TODO-06: Run this test. Did it run properly? If not, why not?
	// TODO-09: Re-run this test after properly specifying formatting on the BookOrder class
	public void testGetBookOrder() {
		// TODO-04: Define the URL that will be used to fetch the entry from the BookOrder region. 
		//          Use URI template syntax to allow passing the entry key as a parameter.
		String url = "";
		
		// TODO-05: Issue a call to the RestTemplate to fetch an entry having the key 17699 and assigning to the 'order' variable.
		BookOrder order = null;
		assertNotNull(order);
		assertEquals(new Integer(17699), order.getOrderNumber());
	}
	
}
