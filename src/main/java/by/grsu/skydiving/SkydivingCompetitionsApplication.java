package by.grsu.skydiving;

import by.grsu.skydiving.common.config.security.JwtSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {JwtSettings.class})
public class SkydivingCompetitionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkydivingCompetitionsApplication.class, args);
    }

}
