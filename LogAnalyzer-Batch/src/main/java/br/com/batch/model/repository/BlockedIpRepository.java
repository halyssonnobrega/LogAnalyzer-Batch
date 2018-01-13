package br.com.batch.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.batch.model.entity.BlockedIpEntity;

@RepositoryRestResource(collectionResourceRel = "blockedIP", path = "blockedIP")
public interface BlockedIpRepository extends MongoRepository<BlockedIpEntity, Long> {

}
