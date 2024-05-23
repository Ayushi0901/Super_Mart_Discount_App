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
public class RegularDiscountCalculator implements DiscountCalculator {
	private List<DiscountSlab> slabs;

	public RegularDiscountCalculator() {
		slabs = new ArrayList<>();
		slabs.add(new DiscountSlab(0, 4000, 10));
		slabs.add(new DiscountSlab(4000, 8000, 15));
		slabs.add(new DiscountSlab(8000, 12000, 20));
		slabs.add(new DiscountSlab(12000, Double.MAX_VALUE, 25));
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
