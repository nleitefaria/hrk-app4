package com.mycompany.myapp.domain;

public class CustomerDTO 
{	
	private String id;	
	private String name;	
	private String phone;
	private String state;
	private String country;
	private String countryCode;
	private String number;

	public CustomerDTO() 
	{		
	}
	
	public CustomerDTO(String name, String phone) 
	{
		this.name = name;
		this.phone = phone;		
	}

	
	public CustomerDTO(String id, String name, String phone) 
	{
		this.id = id;
		this.name = name;
		this.phone = phone;		
	}

	public CustomerDTO(String id, String name, String phone, String state, String country, String countryCode, String number) 
	{
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.state = state;
		this.country = country;
		this.countryCode = countryCode;
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}	
}
