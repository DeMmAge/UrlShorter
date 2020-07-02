package com.demmage.urlshorter.dao;

import com.demmage.urlshorter.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepo extends JpaRepository<Url, Long> {

    Url getUrlByInternal(String internal);
    Url getFirstByFullUrl(String fullUrl);

}
