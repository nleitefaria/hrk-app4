package com.mycompany.myapp.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.mycompany.myapp.domain.CustomerDTO;
import com.mycompany.myapp.entity.Customer;
import com.mycompany.myapp.entity.Customer_;

public abstract class CustomerSpecs {
	
	public static Specifications<Customer> buildSpecification(CustomerDTO customerDTO) 
	{
		Specification<Customer> fetchSpec = fetch();
		Specifications<Customer> specs = Specifications.where(fetchSpec);
		
		if(customerDTO.getName() != null)
		{
			if(customerDTO.getName().equals("") == false)
			{
				Specification<Customer> customeNameIsLikeSpec = customeNameIsLike(customerDTO.getName());
				specs = specs.and(customeNameIsLikeSpec);
			}			
		}
		
		if(customerDTO.getPhone() != null)
		{
			if(customerDTO.getPhone().equals("") == false)
			{
				Specification<Customer> customePhoneIsLikeSpec = customePhoneIsLike(customerDTO.getPhone());
				specs = specs.and(customePhoneIsLikeSpec);
			}			
		}
		
		return specs;
	}
	
	private static Specification<Customer> fetch() {
		return new Specification<Customer>() {
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.isNotNull(root.get(Customer_.id));
			}
		};
	}
	
	private static Specification<Customer> customeNameIsLike(final String name) 
	{
        return new Specification<Customer>() 
        {
          public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) 
          {
             return builder.like(builder.lower(root.get(Customer_.name)), name.toLowerCase());
          }
        };
    }
	
	private static Specification<Customer> customePhoneIsLike(final String phone) 
	{
        return new Specification<Customer>() 
        {
          public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) 
          {
             return builder.like(builder.lower(root.get(Customer_.phone)), phone.toLowerCase());
          }
        };
    }
}
