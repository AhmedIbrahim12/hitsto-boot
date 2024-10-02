package com.hitsto.domain.redirect.repository;

import com.hitsto.domain.redirect.UrlRedirect;

public interface UrlRedirectRepository {

    UrlRedirect save(final UrlRedirect urlRedirect);

    UrlRedirect findByOriginalUrl(final String originalUrl);

    UrlRedirect findByShortenedUrl(final String shortenedUrl);

    void delete(final Long id);
}
