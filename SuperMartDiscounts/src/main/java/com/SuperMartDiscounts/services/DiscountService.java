package com.SuperMartDiscounts.services;

import com.SuperMartDiscounts.models.Customer;

public interface DiscountService {

	double calculateDiscount(Customer customer, double purchaseAmount);
}
