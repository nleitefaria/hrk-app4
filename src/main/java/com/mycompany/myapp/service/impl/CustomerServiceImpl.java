package com.mycompany.myapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public CustomerDTO findOne(Integer id) {
		Customer customer = customerReppository.findOne(id);
		return fromCustomer(customer);
	}

	public List<CustomerDTO> getAll() {
		List<CustomerDTO> ret = new ArrayList<CustomerDTO>();
		for (Customer customer : customerReppository.findAll()) {
			CustomerDTO customerDTO = fromCustomer(customer);
			ret.add(customerDTO);
		}
		return ret;
	}
	
	@Transactional
	public Customer save(CustomerDTO customerDTO)
	{
		return customerReppository.save(new Customer(Integer.valueOf(customerDTO.getId()), customerDTO.getName(), customerDTO.getPhone()));	
	}

	private CustomerDTO fromCustomer(Customer customer) 
	{
		CustomerDTO ret = null;
		String[] phoneData = null;
		String state = null;
		String countryCode = null;
		String number = null;
		String country = null;
		
		if(customer.getPhone() != null)
		{
			state = phoneDataService.getState(customer.getPhone());
			phoneData = phoneDataService.splitFullPhoneNumber(customer.getPhone());			
		}	
		
		if(phoneData != null)
		{
			countryCode = phoneData[0];
			number = phoneData[1];	
		}
		
		if(countryCode != null)
		{
			country = phoneDataService.getCountryByCountryCode(countryCode);			
		}
					
		ret = new CustomerDTO(customer.getId().toString(), customer.getName(), customer.getPhone(), state, country, countryCode, number);
		return ret;
	}
}
