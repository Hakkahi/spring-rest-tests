package com.test.recruitment.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.recruitment.entity.Transaction;

/**
 * Transaction repository
 * 
 * @author A525125
 *
 */
public interface TransactionRepository {


	/**
	 * Get transaction by Id
	 *
	 * @param id
	 *            id of the transaction to get
	 * @return the transaction corresponding to the given id or null
	 */
	Transaction findById(String id);

	/**
	 * Get transactions by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return
	 */
	Page<Transaction> getTransactionsByAccount(String accountId, Pageable p);

	/**
	 * Remove a specific transaction from the account
	 * 
	 * @param accountId
	 *            the account id
	 * @param transactionId
	 *             the translation id
	 */
	void removeTransactionForAccount(String accountId, String transactionId);

	
	/**
	 * Check if transaction exists
	 * 
	 * @param transactionId
	 *            the transaction id
	 * @return true if the transaction exists
	 */
	boolean exists(String transactionId);
}
