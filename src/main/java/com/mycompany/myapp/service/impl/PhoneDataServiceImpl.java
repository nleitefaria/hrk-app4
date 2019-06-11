package com.mycompany.myapp.service.impl;

import org.springframework.stereotype.Service;

import com.mycompany.myapp.service.PhoneDataService;

@Service
public class PhoneDataServiceImpl implements PhoneDataService 
{	
	public String[] splitFullPhoneNumber(String phone) {
		String[] ret = new String[2];
		String[] temp = phone.split("\\) ");
		ret[0] = temp[0].substring(1);
		ret[1] = temp[1];
		return ret;
	}

	public String getCountryByCountryCode(String countryCode) {
		String result;
		switch (countryCode) {
		case "237":
			result = "Cameroon";
			break;
		case "251":
			result = "Ethiopia";
			break;
		case "212":
			result = "Morocco";
			break;
		case "258":
			result = "Mozambique";
			break;
		case "256":
			result = "Uganda";
			break;
		default:
			result = "n.a.";
			break;
		}
		return result;
	}

	public String getState(String phone) 
	{		
		if(phone.matches("\\(237\\)\\ ?[2368]\\d{7,8}$") || 
				phone.matches("\\(251\\)\\ ?[1-59]\\d{8}$") || 
				phone.matches("\\(212\\)\\ ?[5-9]\\d{8}$") || 
				phone.matches("\\(258\\)\\ ?[28]\\d{7,8}$") || 
				phone.matches("\\(256\\)\\ ?\\d{9}$"))
		{
			return "Yes";
		}
		return "No";
	}
}
