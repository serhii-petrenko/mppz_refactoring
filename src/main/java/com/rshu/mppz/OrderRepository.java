package com.rshu.mppz;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface OrderRepository extends Repository<Order, Integer> {

	@Transactional(readOnly = true)
	Collection<Order> findAll() throws DataAccessException;

	@Transactional(readOnly = true)
	Page<Customer> findAll(Pageable pageable) throws DataAccessException;

	@Query("SELECT order FROM Order order WHERE order.orderId =:id")
	@Transactional(readOnly = true)
	Order findById(@Param("id") Integer id);

    void save(Order order);
}
