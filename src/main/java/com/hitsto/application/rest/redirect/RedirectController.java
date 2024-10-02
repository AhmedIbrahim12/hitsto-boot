package com.hitsto.application.rest.redirect;

import com.hitsto.domain.redirect.UrlRedirect;
import com.hitsto.domain.redirect.service.UrlRedirectService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/redirect/v1")
public class RedirectController {

    private final Logger LOGGER = LogManager.getLogger(RedirectController.class);

    private UrlRedirectService urlRedirectService;

    @PostMapping
    public String shortenUrl(@RequestParam(value = "url") final String urlToShorten, final HttpServletRequest request) {

        final String currentPath = getRequestUrl(request);

        return urlRedirectService.shortenUrl(currentPath, urlToShorten).getShortenedUrl();
    }

    @GetMapping("/{url}")
    public ResponseEntity<?> getNewUrl(@PathVariable String url, final HttpServletRequest request) throws URISyntaxException {

        final String requestUrl = getRequestUrl(request);

        final String originalUrl = Optional.ofNullable(urlRedirectService.findByShortenedUrl(requestUrl))
                .map(UrlRedirect::getOriginalUrl)
                .orElse(null);

        if (Objects.isNull(originalUrl)) {

            LOGGER.info("Shortened url not found : " + requestUrl);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Use MOVED_PERMANENTLY(301) to automatically redirect from the browser without requesting here
        return new ResponseEntity<>(buildRedirectUrl(originalUrl), HttpStatus.FOUND);
    }

    private HttpHeaders buildRedirectUrl(final String originalUrl) throws URISyntaxException {

        final URI oldUrI = new URI("https://" + originalUrl);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(oldUrI);

        return httpHeaders;
    }

    private String getRequestUrl(final HttpServletRequest request){
        return request.getRequestURL().toString();
    }

    @Autowired
    public void setUrlRedirectService(final UrlRedirectService urlRedirectService) {
        this.urlRedirectService = urlRedirectService;
    }
}
