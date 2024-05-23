package com.SuperMartDiscounts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SuperMartDiscounts.models.Customer;
import com.SuperMartDiscounts.services.DiscountService;
import com.SuperMartDiscounts.validations.Validator;

@RestController
public class DiscountController {

	@Autowired
	private DiscountService discountService;

	@Autowired
	private Validator validator;

//	@Autowired
//	public DiscountController(DiscountService discountService, Validator validator) {
//		this.discountService = discountService;
//		this.validator = validator;
//	}

	@PostMapping("/calculateDiscount")
	public ResponseEntity<String> calculateDiscount(@RequestBody Customer customer,
			@RequestParam double purchaseAmount) {
		String variable ="abc";
		System.out.println(variable);
		 variable ="xyc";
		System.out.println(variable);
		this.validator.checkValidations(customer, purchaseAmount);
		double calculatedDiscount = discountService.calculateDiscount(customer, purchaseAmount);
		return ResponseEntity
				.ok("Discount available for " + customer.getType() + " type customer is " + calculatedDiscount);
	}

}
