package com.papaya.infra.rrmistarter;

import com.papaya.infra.rrmistarter.props.PropertiesHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@Configuration
//@EnableConfigurationProperties(PropertiesHolder.class)
public class RrmiBeanGeneratorStarterConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(RrmiBeanGeneratorStarterConfiguration.class);
    }

//    @Bean
//    @ConditionalOnProperty(name = "rrmi.enabled", havingValue = "true",matchIfMissing = true)
//    public AdapterImplGenerator adapterImplGenerator() {
//        return new AdapterImplGenerator();
//    }

}
