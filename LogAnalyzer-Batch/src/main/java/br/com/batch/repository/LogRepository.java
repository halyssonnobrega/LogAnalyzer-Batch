package br.com.batch.repository;

import org.springframework.stereotype.Repository;

import br.com.batch.entity.Log;

@Repository
public interface LogRepository extends GenericRepository<Log, Long> {

}
