package com.gopivotal.bookshop.buslogic;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gopivotal.bookshop.domain.Customer;

// TODO-06: Execute this class to load Customer entries to the cache server.
public class CustomerLoader
{

	private ClientCache cache;
	private Region <Integer, Customer> customers;
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		CustomerLoader loader = new CustomerLoader();
		loader.getCache();
		loader.getCustomerRegion();
		loader.populateCustomers();
		loader.closeCache();
		

	}
	
	public void setCache (ClientCache cache)
	{
		this.cache = cache;
	}
	
	public void closeCache()
	{
		cache.close();
	}
	
	public void getCache()
	{
		this.cache = new ClientCacheFactory()
        .set("name", "ClientWorker")
        .set("cache-xml-file", "xml/clientCache.xml")
        .create();
	}
	
	public void getCustomerRegion()
	{
		customers = cache.getRegion("Customer");
		System.out.println("Got the Customer Region: " + customers);
	}

	
	public void populateCustomers()
	{
		
		Customer cust1 = new Customer(5598, "Kari", "Powell", ""+44444);
		cust1.addOrder(17699);
		cust1.addOrder (18009);
		cust1.addOrder (18049);
		customers.put(5598, cust1);
		System.out.println("Inserted a customer: " + cust1);
		
		
		
		Customer cust2 = new Customer (5543, "Lula", "Wax", ""+12345);
		cust2.addOrder(17699);
		customers.put(5543, cust2);
		System.out.println("Inserted a customer: " + cust2);
		
		
		Customer cust3 = new Customer (6024, "Trenton", "Garcia", ""+88888);
		customers.put(6024, cust3);
		System.out.println("Inserted a customer: " + cust3);	

	}
}
