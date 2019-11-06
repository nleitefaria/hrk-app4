package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mycompany.myapp.domain.CustomerDTO;
import com.mycompany.myapp.entity.Customer;

public interface CustomerService
{
	Long count();
	CustomerDTO findOne(Integer id);
	List<CustomerDTO> getAll();
	Page<CustomerDTO> findAllPaged(int page);
	Page<CustomerDTO> findAllPagedWithFilters(CustomerDTO customerDTO, int page);
	Customer save(CustomerDTO customerDTO);
}
