package com.gopivotal.bookshop.buslogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gopivotal.bookshop.domain.BookMaster;

// TODO-18: Run this program. Note that it will pause for input so you can read instructions before proceeding with
//          the creation of the entry with key 999 and then deleting it. You should see the CacheListener reporting
//          these events on the ClientConsumer console output.
public class ClientWorker {

	public static final String REGION_NAME = "BookMaster";

	public static void main(String[] args) throws Exception {

		System.out.println("Connecting to the distributed system and creating the cache.");
		// Create the cache which causes the cache-xml-file to be parsed
		ClientCache cache = new ClientCacheFactory()
                  .set("name", "ClientWorker")
                  .set("cache-xml-file", "xml/clientWorkerCache.xml")
                  .create();

		// Get the exampleRegion
		Region<Integer,BookMaster> region = cache.getRegion(REGION_NAME);
				
		System.out.println("Note the other client's region listener in response to these gets.");
		System.out.println("Press Enter to continue.");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		bufferedReader.readLine();

		System.out.println("Changing the data in my cache - all destroys and updates are forwarded");
		System.out.println("through the server to other clients. Invalidations are not forwarded.");

		// Update one value in the cache
		System.out.println("Putting new value for 999");
		region.put(999, new BookMaster(999,"A spy fiction thriller about a retrograde amnesiac who must discover who he is ",(float) 34.99,2011,"Robert Ludlum","Bourne Identity"));

			// Destroy one entry in the cache
		System.out.println("Destroying 999");
		region.destroy(999);

		// Close the cache and disconnect from GemFire distributed system
		System.out.println("Closing the cache and disconnecting.");
		cache.close();

		System.out.println("In the other session, please hit Enter in the Consumer client");
		System.out.println("and then stop the cacheserver with 'cacheserver stop'.");
	}
}