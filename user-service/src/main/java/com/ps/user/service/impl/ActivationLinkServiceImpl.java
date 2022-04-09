package com.ps.user.service.impl;

import com.ps.user.service.ActivationLinkService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;

@Service
public class ActivationLinkServiceImpl implements ActivationLinkService {
    @Override
    public String getActivationLink(String baseUrl, String code) {
        Assert.hasText(baseUrl);
        Assert.hasText(code);

        return baseUrl + "/api/users/activate/token/" + code;
    }
}

