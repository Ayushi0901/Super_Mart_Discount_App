package com.SuperMartDiscounts.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DiscountSlab {

	private double minAmount;
	private double maxAmount;
	private double percentage;

	public boolean isInRange(double purchaseAmount) {
		return purchaseAmount >= minAmount && purchaseAmount < maxAmount;
	}
}
