package com.hitsto.infrastructure.mysql.repository.redirect;

import com.hitsto.domain.redirect.UrlRedirect;
import com.hitsto.domain.redirect.repository.UrlRedirectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class UrlRedirectDbRepository implements UrlRedirectRepository {

    public static final String REDIRECT_CACHE_VALUE = "redirects";

    private final UrlRedirectCrudRepository repository;

    @Autowired
    public UrlRedirectDbRepository(final UrlRedirectCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    @CachePut(cacheNames = REDIRECT_CACHE_VALUE, key = "#urlRedirect.shortenedUrl")
    public UrlRedirect save(final UrlRedirect urlRedirect) {

        final UrlRedirectEntity redirectEntity = UrlRedirectEntity.fromDomainModel(urlRedirect);

        return repository.save(redirectEntity).toDomainModel();
    }

    @Override
    public UrlRedirect findByOriginalUrl(final String originalUrl) {

        return repository.findByOriginalUrl(originalUrl)
                .map(UrlRedirectEntity::toDomainModel)
                .orElse(null);
    }

    @Override
    @Cacheable(cacheNames = REDIRECT_CACHE_VALUE, key = "#shortenedUrl")
    public UrlRedirect findByShortenedUrl(final String shortenedUrl) {

        return repository.findByShortenedUrl(shortenedUrl)
                .map(UrlRedirectEntity::toDomainModel)
                .orElse(null);
    }

    @Override
    @CacheEvict(cacheNames = REDIRECT_CACHE_VALUE, key = "#id")
    public void delete(final Long id) {

        repository.deleteById(id);
    }
}
