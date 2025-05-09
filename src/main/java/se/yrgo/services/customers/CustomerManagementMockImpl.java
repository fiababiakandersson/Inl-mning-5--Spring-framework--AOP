package se.yrgo.services.customers;

import java.util.*;

import se.yrgo.domain.*;

public class CustomerManagementMockImpl implements CustomerManagementService {
	private HashMap<String, Customer> customerMap;

	public CustomerManagementMockImpl() {
		customerMap = new HashMap<String, Customer>();
		customerMap.put("OB74", new Customer("OB74", "Fargo Ltd", "some notes"));
		customerMap.put("NV10", new Customer("NV10", "North Ltd", "some other notes"));
		customerMap.put("RM210", new Customer("RM210", "River Ltd", "some more notes"));
	}

	@Override
	public void newCustomer(Customer newCustomer) {
		customerMap.put(newCustomer.getCustomerId(), newCustomer);
	}

	@Override
	public void updateCustomer(Customer changedCustomer) {
		Customer customer = customerMap.get(changedCustomer.getCustomerId());
		if (customer != null) {
			customer.setEmail(changedCustomer.getEmail());
			customer.setCompanyName(changedCustomer.getEmail());
			customer.setCustomerId(changedCustomer.getCustomerId());
			customer.setTelephone(changedCustomer.getTelephone());
			customer.setNotes(changedCustomer.getNotes());
			customer.setCalls(changedCustomer.getCalls());
		} else {
			System.out.println("Could not find customer!");
		}
	}

	@Override
	public void deleteCustomer(Customer oldCustomer) {
		customerMap.remove(oldCustomer.getCustomerId());

	}

	@Override
	public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
		return customerMap.get(customerId);
	}

	@Override
	public List<Customer> findCustomersByName(String name) {
		List<Customer> customers = new ArrayList<>();

		for (Map.Entry<String, Customer> entry : customerMap.entrySet()) {
			if (entry.getValue().getCompanyName() == name)
				customers.add(entry.getValue());
		}
		return customers;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();

		for (Map.Entry<String, Customer> entry : customerMap.entrySet()) {
			customers.add(entry.getValue());
		}
		return customers;
	}

	@Override
	public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
		return customerMap.get(customerId);
	}

	@Override
	public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
		Customer c = customerMap.get(customerId);

		c.addCall(callDetails);
	}

}
