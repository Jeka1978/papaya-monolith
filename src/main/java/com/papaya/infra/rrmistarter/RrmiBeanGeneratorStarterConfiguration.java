package com.papaya.infra.rrmistarter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.papaya.infra.rrmistarter.common.Person;
import com.papaya.infra.rrmistarter.props.PropertiesHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@Configuration
@EnableConfigurationProperties(PropertiesHolder.class)
public class RrmiBeanGeneratorStarterConfiguration {

    public static void main(String[] args) throws JsonProcessingException {
        ConfigurableApplicationContext context = SpringApplication.run(RrmiBeanGeneratorStarterConfiguration.class);
        Person person = new Person("jeka", 33);
        ObjectMapper mapper = context.getBean(ObjectMapper.class);
        String json = mapper.writeValueAsString(person);
        System.out.println(json);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

//    @Bean
//    @ConditionalOnProperty(name = "rrmi.enabled", havingValue = "true",matchIfMissing = true)
//    public AdapterImplGenerator adapterImplGenerator() {
//        return new AdapterImplGenerator();
//    }

}
