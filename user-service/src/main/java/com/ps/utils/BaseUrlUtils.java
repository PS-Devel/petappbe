package com.ps.utils;

import org.apache.logging.log4j.util.Strings;

import javax.servlet.http.HttpServletRequest;

public class BaseUrlUtils {
    public static String getBasicUrlFromRequest(HttpServletRequest request) {
        return request.getRequestURL().toString()
                .replace(request.getServletPath(), Strings.EMPTY);
    }
}
