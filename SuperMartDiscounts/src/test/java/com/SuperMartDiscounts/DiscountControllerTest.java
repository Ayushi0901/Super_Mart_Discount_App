package com.SuperMartDiscounts;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.SuperMartDiscounts.controllers.DiscountController;
import com.SuperMartDiscounts.models.Customer;
import com.SuperMartDiscounts.services.DiscountService;
import com.SuperMartDiscounts.validations.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DiscountController.class)
@AutoConfigureMockMvc
public class DiscountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DiscountService discountService;

	@MockBean
	private Validator validator;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testCalculateDiscount() throws Exception {
		Customer customer = new Customer("PREMIUM");
		double purchaseAmount = 6000.0;
		double calculatedDiscount = 600.0; // 6000 * 10%

		when(discountService.calculateDiscount(any(Customer.class), any(Double.class))).thenReturn(calculatedDiscount);

		mockMvc.perform(post("/calculateDiscount").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer))
				.param("purchaseAmount", String.valueOf(purchaseAmount))).andExpect(status().isOk());
	}

	@Test
	public void testCalculateDiscountForRegular() throws Exception {
		Customer customer = new Customer("Regular");
		double purchaseAmount = 6000.0;
		double calculatedDiscount = 900.0; // 6000 * 15%

		when(discountService.calculateDiscount(any(Customer.class), any(Double.class))).thenReturn(calculatedDiscount);

		mockMvc.perform(post("/calculateDiscount").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer))
				.param("purchaseAmount", String.valueOf(purchaseAmount))).andExpect(status().isOk());
	}

	@Test
	public void testCalculateDiscount_InvalidCustomerType() throws Exception {
		Customer customer = new Customer("INVALID_TYPE");
		double purchaseAmount = 6000.0;
		mockMvc.perform(post("/calculateDiscount").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer))
				.param("purchaseAmount", String.valueOf(purchaseAmount))).andExpect(status().isBadRequest());
	}

	@Test
	public void testCalculateDiscount_NegativePurchaseAmount() throws Exception {
		Customer customer = new Customer("REGULAR");
		double purchaseAmount = -100.0;

		mockMvc.perform(post("/calculateDiscount").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer))
				.param("purchaseAmount", String.valueOf(purchaseAmount))).andExpect(status().isBadRequest());
	}
}
