package com.hitsto.domain.redirect.service;

import com.hitsto.domain.redirect.UrlRedirect;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hitsto.domain.redirect.repository.UrlRedirectRepository;

import java.util.Objects;

@Service
public class UrlRedirectServiceImpl implements UrlRedirectService {

    private final UrlRedirectRepository urlRedirectRepository;

    @Autowired
    public UrlRedirectServiceImpl(final UrlRedirectRepository urlRedirectRepository) {
        this.urlRedirectRepository = urlRedirectRepository;
    }

    @Override
    public UrlRedirect shortenUrl(final String currentPath, final String urlToShorten) {

        final UrlRedirect existingRedirect = urlRedirectRepository.findByOriginalUrl(urlToShorten);

        if (Objects.nonNull(existingRedirect)) {
            return existingRedirect;
        }

        final UrlRedirect urlRedirect = new UrlRedirect();
        urlRedirect.setOriginalUrl(urlToShorten);

        final String shortKey = RandomStringUtils.randomAlphanumeric(8).toLowerCase();
        urlRedirect.setShortenedUrl(currentPath + "/" + shortKey);

        return urlRedirectRepository.save(urlRedirect);
    }

    @Override
    public UrlRedirect findByShortenedUrl(final String oldUrl) {
        return urlRedirectRepository.findByShortenedUrl(oldUrl);
    }

    @Override
    public void delete(final Long id) {
        urlRedirectRepository.delete(id);
    }
}
