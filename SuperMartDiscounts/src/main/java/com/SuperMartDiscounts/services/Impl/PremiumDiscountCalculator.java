package com.SuperMartDiscounts.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.SuperMartDiscounts.models.DiscountSlab;
import com.SuperMartDiscounts.services.DiscountCalculator;

import lombok.Getter;
import lombok.Setter;

@Service
@Setter
@Getter
public class PremiumDiscountCalculator implements DiscountCalculator {
	private List<DiscountSlab> slabs;

	public PremiumDiscountCalculator() {
		slabs = new ArrayList<>();
		slabs.add(new DiscountSlab(0, 5000, 0));
		slabs.add(new DiscountSlab(5000, 10000, 10));
		slabs.add(new DiscountSlab(10000, Double.MAX_VALUE, 20));
	}

	@Override
	public double calculateDiscount(double purchaseAmount) {
		for (DiscountSlab slab : slabs) {
			if (slab.isInRange(purchaseAmount)) {
				return purchaseAmount * (slab.getPercentage() / 100);
			}
		}
		return 0;
	}
}
