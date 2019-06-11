package com.mycompany.myapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myapp.domain.CustomerDTO;
import com.mycompany.myapp.entity.Customer;
import com.mycompany.myapp.repository.CustomerRepository;
import com.mycompany.myapp.service.CustomerService;
import com.mycompany.myapp.service.PhoneDataService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerReppository;
	
	@Autowired
	PhoneDataService phoneDataService;

	@Override
	public List<CustomerDTO> getAll() {
		List<CustomerDTO> ret = new ArrayList<CustomerDTO>();
		for (Customer customer : customerReppository.findAll()) {
			CustomerDTO customerDTO = fromCustomer(customer);
			ret.add(customerDTO);
		}
		return ret;
	}

	private CustomerDTO fromCustomer(Customer customer) {
		CustomerDTO ret = null;
		String state = phoneDataService.getState(customer.getPhone());
		String[] phoneData = phoneDataService.splitFullPhoneNumber(customer.getPhone());
		String countryCode = phoneData[0];
		String number = phoneData[1];
		String country = phoneDataService.getCountryByCountryCode(countryCode);
		ret = new CustomerDTO(customer.getId().toString(), customer.getName(), customer.getPhone(), state, country, countryCode, number);
		return ret;
	}
}
