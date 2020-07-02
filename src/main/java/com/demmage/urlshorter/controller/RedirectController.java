package com.demmage.urlshorter.controller;

import com.demmage.urlshorter.dao.UrlRepo;
import com.demmage.urlshorter.domain.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {

    private final UrlRepo urlRepo;

    @Autowired
    public RedirectController(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    @GetMapping("/r/{internalUrl}")
    public RedirectView redirect(@PathVariable String internalUrl) {
        Url urlFromDb = urlRepo.getUrlByInternal(internalUrl);

        if (urlFromDb != null) {
            return new RedirectView(urlFromDb.getFullUrl());
        } else {
            return new RedirectView("/main");
        }
    }

}
