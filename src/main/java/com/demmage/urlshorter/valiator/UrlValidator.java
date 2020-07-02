package com.demmage.urlshorter.valiator;

public class UrlValidator {

    public boolean IsUrlValid(String url) {
        boolean containsProtocol = url.contains("://");
        boolean containsDomain = url.contains(".");
        return containsProtocol && containsDomain;
    }
}
