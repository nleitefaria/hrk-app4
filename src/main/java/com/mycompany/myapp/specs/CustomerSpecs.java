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
				Specification<Customer> actorFirstNameIsLikeSpec = actorFirstNameIsLike(customerDTO.getName());
				specs = specs.and(actorFirstNameIsLikeSpec);
			}			
		}
		
//		if(actorDTO.getLastName() != null)
//		{
//			if(actorDTO.getLastName().equals("") == false)
//			{
//				Specification<Actor> actorLastNameIsLikeSpec = actorLastNameIsLike(actorDTO.getLastName());
//				specs = specs.and(actorLastNameIsLikeSpec);
//			}			
//		}
		
		return specs;
	}
	
	
	
	
	private static Specification<Customer> fetch() {
		return new Specification<Customer>() {
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.isNotNull(root.get(Customer_.id));
			}
		};
	}
	
	private static Specification<Customer> actorFirstNameIsLike(final String name) 
	{
        return new Specification<Customer>() 
        {
          public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) 
          {
             return builder.like(builder.lower(root.get(Customer_.name)), name.toLowerCase());
          }
        };
    }
    
	
	

}
