package com.taptap.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bracelet", path = "bracelet")
public interface BraceletRepository extends PagingAndSortingRepository<Bracelet, Long> {

    Bracelet findById(@Param("id") long id);


}