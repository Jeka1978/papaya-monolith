package com.papaya.infra.rrmistarter.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Evgeny Borisov
 */
@Data
@ConfigurationProperties(prefix = "rrmi")
public class PropertiesHolder {
    private String packagesToScan="com.papaya";
    private boolean enabled;
}
