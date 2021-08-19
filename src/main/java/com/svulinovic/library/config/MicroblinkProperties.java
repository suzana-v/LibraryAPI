package com.svulinovic.library.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("microblink")
@Data
public class MicroblinkProperties {

    private String endpoint;
    private String token;

}
