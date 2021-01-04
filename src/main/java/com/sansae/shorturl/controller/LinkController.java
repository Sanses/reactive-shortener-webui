package com.sansae.shorturl.controller;

import com.sansae.shorturl.model.Link;
import com.sansae.shorturl.service.LinkService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/links")
    Mono<CreateLinkResponse> createLink(@RequestBody CreateLinkRequest request) {
        return linkService.shortenLink(request.getLink())
                .map(CreateLinkResponse::new);
    }

    @GetMapping("/links/{key}")
    Mono<ResponseEntity<Object>> getLink(@PathVariable String key) {
        return linkService.getOriginalLink(key)
                .map(link -> ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                        .header("Location", link.getOriginalLink())
                        .build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/links/{key}")
    Mono<Long> deleteLink(@PathVariable String key) {
        return linkService.deleteByKey(key);
    }

    @GetMapping("/links")
    Flux<Link> getAllLinks() {
        return linkService.findAll();
    }

    @Value
    public static class CreateLinkRequest {
        private String link;
    }

    @Value
    public static class CreateLinkResponse {
        private String shortenedLink;
    }
}
