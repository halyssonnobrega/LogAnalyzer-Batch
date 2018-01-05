package br.com.batch.repository;

import java.io.Serializable;

import org.springframework.data.repository.Repository;

public interface GenericRepository<T, ID extends Serializable> extends Repository<T, ID> {

	<S extends T> S save(S entity);

}
