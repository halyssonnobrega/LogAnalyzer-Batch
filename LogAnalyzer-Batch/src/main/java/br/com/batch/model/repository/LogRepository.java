package br.com.batch.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.batch.model.entity.LogEntity;

@RepositoryRestResource(collectionResourceRel = "log", path = "log")
public interface LogRepository extends MongoRepository<LogEntity, Long> {

}
