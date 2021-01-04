package com.sansae.shorturl.service;

import com.sansae.shorturl.model.Link;
import com.sansae.shorturl.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LinkService {


    private final LinkRepository linkRepository;

    public Mono<String> shortenLink(String link) {
        String randomKey = RandomStringUtils.randomAlphabetic(6);
        return linkRepository.save(new Link(link, randomKey))
                .map(result -> result.getKey());
    }

    public Mono<Link> getOriginalLink(String key) {
        return linkRepository.findByKey(key);
    }

    public Mono<Long> deleteByKey(String key) {
        return linkRepository.deleteByKey(key);
    }

    public Flux<Link> findAll() {
        return linkRepository.findAll();
    }
}
