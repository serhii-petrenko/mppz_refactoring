package com.rshu.mppz;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends Repository<Product, Integer> {

	@Transactional(readOnly = true)
	List<Product> findAll() throws DataAccessException;

	@Transactional(readOnly = true)
	Page<Product> findAll(Pageable pageable) throws DataAccessException;

}
