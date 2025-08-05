package com.app.shorturl.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.shorturl.dto.UrlRequest;
import com.app.shorturl.dto.UrlResponse;
import com.app.shorturl.exception.InvalidUrlException;
import com.app.shorturl.exception.UrlNotFoundException;
import com.app.shorturl.model.UrlData;
import com.app.shorturl.repository.UrlRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    public UrlResponse shortenUrl(UrlRequest request) {
        if (!isValidUrl(request.getOriginalUrl())) {
            throw new InvalidUrlException("Invalid URL format");
        }

        UrlData urlData = new UrlData();
        urlData.setOriginalUrl(request.getOriginalUrl());
        urlData.setShortCode(generateShortCode());

        urlRepository.save(urlData);

        return new UrlResponse("http://localhost:8080/api/url/" + urlData.getShortCode());
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        Optional<UrlData> urlData = urlRepository.findByShortCode(shortCode);
        return urlData.map(UrlData::getOriginalUrl)
                      .orElseThrow(() -> new UrlNotFoundException("Short URL not found"));
    }

    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private boolean isValidUrl(String url) {
        return url != null && url.startsWith("http");
    }
}
