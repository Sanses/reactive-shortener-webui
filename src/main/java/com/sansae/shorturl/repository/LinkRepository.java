package com.sansae.shorturl.repository;

import com.sansae.shorturl.model.Link;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LinkRepository {
    Mono<Link> save(Link link);

    Mono<Long> deleteByKey(String key);

    Mono<Link> findByKey(String key);

    Flux<Link> findAll();
}