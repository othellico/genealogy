package com.claudiosignorini.genealogy.repository;

import com.claudiosignorini.genealogy.entity.EventEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends PagingAndSortingRepository<EventEntity, Long> {


}
