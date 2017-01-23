package com.taptap.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "vibration", path = "vibration")
public interface VibrationRepository extends PagingAndSortingRepository<Vibration, Long> {

    List<Vibration> findByBracelet(@Param("bracelet") Bracelet id);
    Vibration findById(@Param("id") long id);


}
