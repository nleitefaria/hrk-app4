package com.mycompany.myapp.rws;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRWS 
{	
	@RequestMapping("/hello")
    public String greeting() {
		
		System.out.println("************************");
		System.out.println("************************");		
		System.out.println("************************");
		System.out.println("************************");
		
		
        return "Hi";
    }

}
