package com.rshu.mppz;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface CustomerRepository extends Repository<Customer, Integer> {

	@Transactional(readOnly = true)
	Collection<Customer> findAll() throws DataAccessException;

	@Transactional(readOnly = true)
	Page<Customer> findAll(Pageable pageable) throws DataAccessException;

	@Query("SELECT customer FROM Customer customer WHERE customer.customerId =:id")
	@Transactional(readOnly = true)
	Customer findById(@Param("id") Integer id);

	void save(Customer customer);
}
