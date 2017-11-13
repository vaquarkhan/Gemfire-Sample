package com.gopivotal.bookshop.buslogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.gemstone.gemfire.GemFireCheckedException;
import com.gemstone.gemfire.GemFireException;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.Pool;
import com.gemstone.gemfire.cache.client.PoolManager;
import com.gemstone.gemfire.cache.query.CqAttributes;
import com.gemstone.gemfire.cache.query.CqAttributesFactory;
import com.gemstone.gemfire.cache.query.CqQuery;
import com.gemstone.gemfire.cache.query.QueryService;
import com.gemstone.gemfire.cache.query.SelectResults;
import com.gemstone.gemfire.cache.query.Struct;
import com.gopivotal.bookshop.domain.BookOrder;

public class CQClient {
	private ClientCache cache;

	public static void main(String[] args) throws Exception {
		CQClient consumer = new CQClient();
		consumer.getCache();

		try {
			consumer.registerCq();

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(System.in));

			System.out.println("Press enter to end");
			bufferedReader.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		consumer.closeCache();

	}

	public void registerCq() throws GemFireException, GemFireCheckedException {
		// Get a reference to the pool
		Pool myPool = PoolManager.find("client");

		// TODO-07: Get the query service for the Pool
		QueryService queryService = myPool.getQueryService();

		// TODO-08: Create CQ Attributes, registering the SimpleCQListener
		// implementation class
		CqAttributesFactory cqAf = new CqAttributesFactory();
		cqAf.addCqListener(new SimpleCQListener());
		CqAttributes cqa = cqAf.create();

		// TODO-09: Construct a query that will trigger a CQEvent when a
		// BookOrder has a totalPrice value > $100
		String query = "SELECT * FROM /BookOrder b WHERE b.totalPrice > 100.00";

		// TODO-10: Create the continuous query and execute it. If executing
		// with initial results, capture
		// the results and iterate over them, printing the orders
		CqQuery myCq = queryService.newCq("myCQ", query, cqa);
		System.out.println("Made new CQ Service");
		SelectResults sResults = myCq.executeWithInitialResults();
		for (Object o : sResults) {
			Struct s = (Struct) o;
			BookOrder bo = (BookOrder) s.get("value");
			System.out.println("Intial result includes: " + bo);
		}

	}

	public void closeCache() {
		cache.close();
	}

	public void getCache() {

		cache = new ClientCacheFactory().set("name", "CQClient")
				.set("cache-xml-file", "xml/clientConsumerCache.xml").create();
	}

}
