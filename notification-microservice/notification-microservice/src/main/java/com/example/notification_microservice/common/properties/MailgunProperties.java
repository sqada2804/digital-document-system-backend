package com.example.notification_microservice.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mailgun")
public class MailgunProperties {
    private String apiKey;
    private String baseUrl;
    private String domain;
    private String from;
}
