package com.sansae.shorturl.repository;

import com.sansae.shorturl.model.Link;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class RedisLinkRepository implements LinkRepository {

    private final static String LINKS = "links";
    private final ReactiveRedisOperations<String, Link> operations;

    public RedisLinkRepository(ReactiveRedisOperations<String, Link> operations) {
        this.operations = operations;
    }


    @Override
    public Mono<Link> save(Link link) {
        return operations.<String, Link>opsForHash()
                .put(LINKS, link.getKey(), link)
                .map(p -> link);
    }

    @Override
    public Mono<Long> deleteByKey(String key) {
        return operations.opsForHash().remove(LINKS, key);
    }

    @Override
    public Mono<Link> findByKey(String key) {
        return operations.<String, Link>opsForHash()
                .get(LINKS, key);
    }

    @Override
    public Flux<Link> findAll() {
        return operations.<String, Link>opsForHash().values(LINKS);
    }
}
