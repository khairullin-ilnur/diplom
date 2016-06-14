package ru.darkvader.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"ru.darkvader.services", "ru.darkvader.model"})
@Import({ru.darkvader.config.DataSourceTestConfig.class, ru.darkvader.config.PersistenceConfig.class})
public class CoreConfig {
}
