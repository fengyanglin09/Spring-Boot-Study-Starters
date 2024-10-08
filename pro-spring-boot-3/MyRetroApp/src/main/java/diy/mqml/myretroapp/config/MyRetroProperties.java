package diy.mqml.myretroapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="service")
@Data
public class MyRetroProperties {
    UserServiceConfig users;
}
