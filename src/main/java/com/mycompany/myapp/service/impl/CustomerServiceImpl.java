package com.mycompany.myapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	CustomerRepository customerRepository;
	
	@Autowired
	PhoneDataService phoneDataService;
	
	public Long count() {
		return customerRepository.count();
	}
	
	public CustomerDTO findOne(Integer id) {
		Customer customer = customerRepository.findOne(id);
		return fromCustomer(customer);
	}

	public List<CustomerDTO> getAll() {
		List<CustomerDTO> ret = new ArrayList<CustomerDTO>();
		for (Customer customer : customerRepository.findAll()) {
			CustomerDTO customerDTO = fromCustomer(customer);
			ret.add(customerDTO);
		}
		return ret;
	}
	
	public Page<CustomerDTO> findAllPaged(int page) 
    {
		return toPageObjectDto(customerRepository.findAll(new PageRequest(page, 10, Sort.Direction.DESC, "id")));
    }
		
	@Transactional
	public Customer save(CustomerDTO customerDTO)
	{
		return customerRepository.save(new Customer(Integer.valueOf(customerDTO.getId()), customerDTO.getName(), customerDTO.getPhone()));	
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
	
	private Page<CustomerDTO> toPageObjectDto(Page<Customer> objects) {
	    Page<CustomerDTO> dtos  = objects.map(this::convertToObjectDto);
	    return dtos;
	}

	private CustomerDTO convertToObjectDto(Customer customer) {
		CustomerDTO customerDTO = fromCustomer(customer);
	    return customerDTO;
	}
}
