package com.example.bookpub.repository;

import com.example.bookpub.entity.Reviewer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReviewerRepository extends PagingAndSortingRepository<Reviewer, Long> {

}
