package com.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    //add timeouts value

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
