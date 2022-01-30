package com.patika.creditservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableRabbit
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class CreditServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplateBuild(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}


