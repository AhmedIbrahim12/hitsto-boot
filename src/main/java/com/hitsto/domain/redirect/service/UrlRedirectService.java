package com.hitsto.domain.redirect.service;

import com.hitsto.domain.redirect.UrlRedirect;

public interface UrlRedirectService {

    UrlRedirect shortenUrl(final String currentPath, final String urlToShorten);

    UrlRedirect findByShortenedUrl(final String oldUrl);

    void delete(final Long id);

}
