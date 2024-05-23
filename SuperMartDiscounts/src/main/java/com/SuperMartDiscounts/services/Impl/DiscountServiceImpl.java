package com.SuperMartDiscounts.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuperMartDiscounts.enums.CustomerType;
import com.SuperMartDiscounts.models.Customer;
import com.SuperMartDiscounts.services.DiscountCalculator;
import com.SuperMartDiscounts.services.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

	// constructor-injected dependencies
	private final DiscountCalculator premiumCalculator;
	private final DiscountCalculator regularCalculator;

	@Autowired
	public DiscountServiceImpl(PremiumDiscountCalculator premiumCalculator,
			RegularDiscountCalculator regularCalculator) {
		this.premiumCalculator = premiumCalculator;
		this.regularCalculator = regularCalculator;
	}

	// field-injected dependencies
//	@Autowired
//    private PremiumDiscountCalculator premiumCalculator;
//    @Autowired
//    private RegularDiscountCalculator regularCalculator;

	public double calculateDiscount(Customer customer, double purchaseAmount) {
		DiscountCalculator calculator;
		CustomerType customerType = CustomerType.valueOf(customer.getType().toUpperCase());
		if (customerType == CustomerType.PREMIUM) {
			calculator = premiumCalculator;
		} else {
			calculator = regularCalculator;
		}
		return calculator.calculateDiscount(purchaseAmount);
	}
}
