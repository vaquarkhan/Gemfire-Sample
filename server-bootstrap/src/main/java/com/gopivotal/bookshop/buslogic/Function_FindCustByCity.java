package com.gopivotal.bookshop.buslogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.execute.FunctionAdapter;
import com.gemstone.gemfire.cache.execute.FunctionContext;
import com.gemstone.gemfire.cache.execute.RegionFunctionContext;
import com.gemstone.gemfire.cache.partition.PartitionRegionHelper;
import com.gopivotal.bookshop.domain.Customer;

public class Function_FindCustByCity extends FunctionAdapter implements
		Declarable {

	public void execute(FunctionContext fc) {

		Customer someCustomer = null, lastCustomer = null;
		List<Customer> allCustomerList = new ArrayList<Customer>();
		int custNo = 0;

		if (fc instanceof RegionFunctionContext) {

			RegionFunctionContext context = (RegionFunctionContext) fc;

			String city = (String) context.getArguments();

			Region<Integer, Customer> customer = PartitionRegionHelper
					.getLocalDataForContext(context);

			Set keys = customer.keySet();
			int setSize = keys.size();
			Iterator keysIterator = keys.iterator();

			for (int i = 0; i < (setSize); i++) {
				someCustomer = customer.get(keysIterator.next());
				if (someCustomer.getPrimaryAddress().getCity().equals(city)) {
					allCustomerList.add(someCustomer);
					custNo++;
				}
			}

			System.out.println("Customer no :" + custNo);
			if (custNo > 0) {
				for (int j = 0; j < (custNo - 1); j++) {
					fc.getResultSender().sendResult(
							(Serializable) allCustomerList.get(j));
				}

				fc.getResultSender().lastResult(
						(Serializable) allCustomerList.get(custNo - 1));	
			} else {
				System.out.println("No matching customers found for " + city + "...");
				fc.getResultSender().lastResult(null);
				
			}
		}

		else {
			System.out.println("This is not a region function...");
			fc.getResultSender().lastResult(null);
		}
	}

	public String getId() {
		return getClass().getName();
	}

	@Override
	public void init(Properties arg0) {

	}
}
