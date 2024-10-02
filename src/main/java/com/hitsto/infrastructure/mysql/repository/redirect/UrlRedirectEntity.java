package com.hitsto.infrastructure.mysql.repository.redirect;

import com.hitsto.domain.redirect.UrlRedirect;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "url_redirect")
public class UrlRedirectEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String originalUrl;

    private String shortenedUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String oldUrl) {
        this.originalUrl = oldUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenedUrl(String newUrl) {
        this.shortenedUrl = newUrl;
    }

    public UrlRedirect toDomainModel() {

        final UrlRedirect model = new UrlRedirect();
        model.setId(id);
        model.setOriginalUrl(originalUrl);
        model.setShortenedUrl(shortenedUrl);

        return model;
    }

    public static UrlRedirectEntity fromDomainModel(final UrlRedirect model) {

        final UrlRedirectEntity entity = new UrlRedirectEntity();
        entity.setId(model.getId());
        entity.setOriginalUrl(model.getOriginalUrl());
        entity.setShortenedUrl(model.getShortenedUrl());

        return entity;
    }
}
