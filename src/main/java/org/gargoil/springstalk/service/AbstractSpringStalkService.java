package org.gargoil.springstalk.service;

import org.gargoil.springstalk.service.interfaces.SpringStalkService;
import org.springframework.beans.factory.annotation.Value;

public class AbstractSpringStalkService implements SpringStalkService {
    @Value("${version:unknown}")
    private String appVersion;

    @Override
    public String getName() {
        return "SpringStalk";
    }
    @Override
    public String getVersion() {
        return appVersion;
    }
}