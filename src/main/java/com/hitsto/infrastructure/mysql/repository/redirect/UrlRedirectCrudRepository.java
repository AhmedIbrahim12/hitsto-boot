package com.hitsto.infrastructure.mysql.repository.redirect;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UrlRedirectCrudRepository extends CrudRepository<UrlRedirectEntity, Long> {

    Optional<UrlRedirectEntity> findByOriginalUrl(final String originalUrl);

    Optional<UrlRedirectEntity> findByShortenedUrl(final String shortenedUrl);
}
