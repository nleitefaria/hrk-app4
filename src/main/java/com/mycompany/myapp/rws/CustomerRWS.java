package com.mycompany.myapp.rws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.myapp.domain.CustomerDTO;
import com.mycompany.myapp.service.CustomerService;

@RestController
public class CustomerRWS {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerRWS.class);
	
	@Autowired
	CustomerService custormerService;
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public ResponseEntity<List<CustomerDTO>> findAll()
	{
		logger.info("Listing all addresses");
		return new ResponseEntity<List<CustomerDTO>>(custormerService.getAll(), HttpStatus.OK);
	}


}
