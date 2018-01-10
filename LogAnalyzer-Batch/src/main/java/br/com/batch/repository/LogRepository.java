package br.com.batch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.batch.entity.LogEntity;

@RepositoryRestResource(collectionResourceRel = "log", path = "log")
public interface LogRepository extends MongoRepository<LogEntity, Long> {

}