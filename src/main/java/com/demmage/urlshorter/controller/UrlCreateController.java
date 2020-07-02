package com.demmage.urlshorter.controller;

import com.demmage.urlshorter.dao.UrlRepo;
import com.demmage.urlshorter.domain.Url;
import com.demmage.urlshorter.valiator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.UUID;

@Controller
public class UrlCreateController {

    private final UrlRepo urlRepo;
    private UrlValidator urlValidator = new UrlValidator();

    @Autowired
    public UrlCreateController(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    private String generateInternalString() {
        return UUID.randomUUID().toString().substring(0, 5);
        //return RandomStringUtils.random(5, true, true); https://www.baeldung.com/spring-redirect-and-forward
        // https://enterprise.arcgis.com/ru/server/10.3/cloud/amazon/change-default-database-passwords-on-linux.htm
    }

    @GetMapping("/createUrl")
    public String createUrl(Map<Object, String> model) {
        model.put("shortUrl", "Your url will be here!");
        return "create_url";
    }

    @PostMapping("/createUrl")
    public String createShortUrl(@RequestParam String url, Map<Object, String> model) {
        if (!urlValidator.IsUrlValid(url)) {
            model.put("shortUrl", "Url is not valid!");
            return "create_url";
        }
        String internal = generateInternalString();
        Url urlFromDb = urlRepo.getFirstByFullUrl(url);
        if (urlFromDb != null) {
            model.put("shortUrl", urlFromDb.getInternal());
        } else {
            urlRepo.save(new Url(internal, url));
            model.put("shortUrl", internal);
        }
        return "create_url";
    }
}
