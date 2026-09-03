package com.example.notification_microservice.services.Implementation;

import com.example.notification_microservice.common.properties.MailgunProperties;
import com.example.notification_microservice.services.Interface.IMailgunService;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MailgunService implements IMailgunService {

    private final WebClient webClient;
    private final MailgunProperties properties;

    public MailgunService(WebClient.Builder builder, MailgunProperties properties) {
        this.webClient = builder.baseUrl(properties.getBaseUrl()).build();
        this.properties = properties;
    }

    @Override
    public Mono<Void> sendEmail(String receiver, String subject, String html) {
        MultipartBodyBuilder body = new MultipartBodyBuilder();

        body.part("from", properties.getFrom());
        body.part("to", receiver);
        body.part("subject", subject);
        body.part("html", html);

        return webClient.post()
                .uri("/v3/{domain}/messages",
                        properties.getDomain())
                .headers(headers ->
                        headers.setBasicAuth("api", properties.getApiKey()))
                .body(BodyInserters.fromMultipartData(body.build()))
                .retrieve()
                .bodyToMono(Void.class);
    }
}
