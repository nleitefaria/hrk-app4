package com.mycompany.myapp.service;

import java.util.List;
import com.mycompany.myapp.domain.CustomerDTO;
import com.mycompany.myapp.entity.Customer;

public interface CustomerService
{
	CustomerDTO findOne(Integer id);
	List<CustomerDTO> getAll();
	Customer save(CustomerDTO customerDTO);

}
