package com.SuperMartDiscounts.validations;

import org.springframework.stereotype.Service;

import com.SuperMartDiscounts.enums.CustomerType;
import com.SuperMartDiscounts.models.Customer;

@Service
public class Validator {
	public void checkValidations(Customer customer, double purchaseAmount) {
		try {
			CustomerType.valueOf(customer.getType().toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid Customer type: " + customer.getType());
		}
		if (purchaseAmount < 0.0) {
			throw new IllegalArgumentException("Purchase amount must be greater than or equal to zero");
		}
	}
}
