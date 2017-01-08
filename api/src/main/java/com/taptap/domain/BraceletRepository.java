package com.taptap.domain;

/**
 * Created by Nicolas on 05/01/2017.
 */
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bracelet", path = "bracelet")
public interface BraceletRepository extends PagingAndSortingRepository<Bracelet, Long> {

    List<Person> findById(@Param("id") long id);


}