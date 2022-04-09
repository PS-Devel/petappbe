package com.ps.user.service;

public interface ActivationLinkService {
    String getActivationLink(String baseUrl, String code);
}
