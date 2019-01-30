package com.example.bookpub.repository;

import com.example.bookpub.entity.Publisher;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PublisherRepository extends PagingAndSortingRepository<Publisher, Long> {

}
