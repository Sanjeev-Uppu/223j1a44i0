package com.app.shorturl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shorturl.model.UrlData;

public interface UrlRepository extends JpaRepository<UrlData, Long> {
    Optional<UrlData> findByShortCode(String shortCode);
}
