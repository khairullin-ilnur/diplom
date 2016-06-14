package ru.darkvader.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Khairullin on 22.08.2014.
 * Configuration for tests.
 */


@Configuration
@Import({ru.darkvader.config.PersistenceConfig.class, ru.darkvader.config.DataSourceTestConfig.class})
@ComponentScan(basePackages = {"ru.darkvader.*"})
public class TestConfig {
}
