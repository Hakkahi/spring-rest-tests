package com.test.recruitment.tests;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

/**
 * Account test
 * 
 * @author A525125
 *
 */
public class TransactionTest extends AbstractTest {

	@Test
	public void getTransactions() throws Exception {
		mockMvc.perform(get("/accounts/1/transactions"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements", is(3)))
				.andExpect(jsonPath("$.content[0].number", is("12151885120")))
				.andExpect(jsonPath("$.content[0].balance", is(42.12)));
	}

	@Test
	public void getTransactionsNoContent() throws Exception {
		mockMvc.perform(get("/accounts/2/transactions")).andExpect(
				status().isNoContent());
	}

	@Test
	public void getTransactionsOnUnexistingAccount() throws Exception {
		mockMvc.perform(get("/accounts/3/transactions"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorCode", is("NOT_FOUND_ACCOUNT")));
	}


	@Test
	public void deleteTransactions() throws Exception {
		mockMvc.perform(delete("/accounts/1/transactions/1"))
				.andExpect(status().isOk());

		mockMvc.perform(get("/accounts/1/transactions"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements", is(2)))
				.andExpect(jsonPath("$.content[0].number", is("12151885121")))
				.andExpect(jsonPath("$.content[0].balance", is(456.0)));
	}

	@Test
	public void deleteTransactionsOnUnexistingAccount() throws Exception {
		mockMvc.perform(delete("/accounts/3/transactions/2"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorCode", is("NOT_FOUND_ACCOUNT")));
	}

	@Test
	public void deleteTransactionsOnUnexistingtransaction() throws Exception {
		mockMvc.perform(delete("/accounts/1/transactions/4"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorCode", is("NOT_FOUND_TRANSACTION")));
	}


}
