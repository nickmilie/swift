package com.nick.spring.service;

import java.util.List;

import com.nick.spring.model.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);
	
}
