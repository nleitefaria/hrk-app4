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
				Specification<Customer> customerNameIsLikeSpec = customerNameIsLike(customerDTO.getName());
				specs = specs.and(customerNameIsLikeSpec);
			}			
		}
		
		if(customerDTO.getPhone() != null)
		{
			if(customerDTO.getPhone().equals("") == false)
			{
				Specification<Customer> customerPhoneIsLikeSpec = customerPhoneIsLike(customerDTO.getPhone());
				specs = specs.and(customerPhoneIsLikeSpec);
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
	
	private static Specification<Customer> customerNameIsLike(final String name) 
	{
        return new Specification<Customer>() 
        {
          public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) 
          {
             return builder.like(builder.lower(root.get(Customer_.name)), name.toLowerCase());
          }
        };
    }
	
	private static Specification<Customer> customerPhoneIsLike(final String phone) 
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
