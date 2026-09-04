package com.example.notification_microservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.example.notification_microservice.common.properties.S3Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AmazonS3Config {
    private final S3Properties s3Properties;

    public AmazonS3Config(S3Properties s3Properties) {
        this.s3Properties = s3Properties;
    }

    @Bean
    public AmazonS3 s3Client(){
        log.info("S3 Client initialized with bucket: {} in {}", s3Properties.getBucket(), s3Properties.getRegion());
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                s3Properties.getEndpoint(),
                                s3Properties.getRegion()
                        )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        s3Properties.getAccessKey(),
                                        s3Properties.getSecretKey()
                                )
                        )
                )
                .withPathStyleAccessEnabled(true)
                .build();
    }
}
