package com.app.shorturl.service;

import com.app.shorturl.dto.UrlRequest;
import com.app.shorturl.dto.UrlResponse;

public interface UrlService {
    UrlResponse shortenUrl(UrlRequest request);
    String getOriginalUrl(String shortCode);
}
