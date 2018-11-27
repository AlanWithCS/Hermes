package onlineShop.dao;

import onlineShop.model.Customer;

public interface CustomerDao {
	// data access object
	public void addCustomer(Customer customer);
	public Customer getCustomerByUserName(String userName);
	
}
