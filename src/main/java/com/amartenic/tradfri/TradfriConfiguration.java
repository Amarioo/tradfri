package com.amartenic.tradfri;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TradfriConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(DeviceService deviceService) {
        return args -> {
            deviceService.config();
        };
    }
}
