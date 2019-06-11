package com.mycompany.myapp.service;

public interface PhoneDataService 
{
	String[] splitFullPhoneNumber(String phone);
	String getCountryByCountryCode(String countryCode);
	String getState(String phone);
}
