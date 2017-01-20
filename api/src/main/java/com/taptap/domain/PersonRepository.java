package com.taptap.domain;

/**
 * Created by Nicolas on 05/01/2017.
 */
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    Person findByLastname(@Param("lastname") String lastname);
    Person findByFirstname(@Param("firstname") String firstname);
	Person findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	Person findById(@Param("id") long id);


}
