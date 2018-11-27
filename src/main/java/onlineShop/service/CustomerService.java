package onlineShop.service;

import onlineShop.model.Customer;

public interface CustomerService {
	public void addCustomer(Customer customer);

    public Customer getCustomerByUserName(String userName);
}
