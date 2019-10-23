package com.mycompany.myapp.rws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.myapp.domain.CustomerDTO;
import com.mycompany.myapp.service.CustomerService;

import io.swagger.annotations.ApiOperation;

@RestController
public class CustomerRWS {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerRWS.class);
	
	@Autowired
	CustomerService custormerService;
	
	@ApiOperation(value = "Find One customer")
	@GetMapping(value = "/customers/count")
    public ResponseEntity<Long> count() 
	{
		logger.info("Listing one customer");
		return new ResponseEntity<Long>(custormerService.count(), HttpStatus.OK);
    }
	
	@ApiOperation(value = "Find One customer")
	@GetMapping(value = "/customers/{id}")
    public ResponseEntity<CustomerDTO> findOne(@PathVariable(value = "id") String id) 
	{
		logger.info("Listing one customer");
		return new ResponseEntity<CustomerDTO>(custormerService.findOne(Integer.parseInt(id)), HttpStatus.OK);
    }
	
	@ApiOperation(value = "Find All customers")
	@GetMapping(value = "/customers")
	public ResponseEntity<List<CustomerDTO>> findAll()
	{
		logger.info("Listing all customers");
		return new ResponseEntity<List<CustomerDTO>>(custormerService.getAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find All customers with paging")
	@GetMapping(value = "/customers/page/{pageNum}")
	public ResponseEntity<Page<CustomerDTO>> findAllPaged(@PathVariable Integer pageNum)
	{
		logger.info("Listing a page of customer" + pageNum);
		return new ResponseEntity<Page<CustomerDTO>>(custormerService.findAllPaged(pageNum - 1), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Add a customer")
    @PostMapping(value = "/customers/add", produces = "application/json")
    public ResponseEntity save(@RequestBody CustomerDTO customerDTO)
    {
		custormerService.save(customerDTO);
        return new ResponseEntity("Customer saved successfully", HttpStatus.CREATED);
    }


}
