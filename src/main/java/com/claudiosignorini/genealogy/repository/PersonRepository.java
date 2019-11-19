package com.claudiosignorini.genealogy.repository;

import com.claudiosignorini.genealogy.entity.PersonEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<PersonEntity, Long> {

    Optional<PersonEntity> findByKey(String key);

}
