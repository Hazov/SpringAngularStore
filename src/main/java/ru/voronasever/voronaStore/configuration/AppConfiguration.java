package ru.voronasever.voronaStore.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.voronasever.voronaStore.repositories.jpa")
public class AppConfiguration {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
