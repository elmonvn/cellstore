package desafio.varejo.cellstoreapi.repository;

import desafio.varejo.cellstoreapi.model.Phone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "phone", path = "phones")
public interface PhoneRepository extends MongoRepository<Phone, String> {//, QuerydslPredicateExecutor<Phone>, QuerydslBinderCustomizer<QPhone> {

    Optional<Phone> findByCode(String code);
}
