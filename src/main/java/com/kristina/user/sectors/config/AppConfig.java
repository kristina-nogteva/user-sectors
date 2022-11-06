package com.kristina.user.sectors.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.kristina.user.sectors")
@ComponentScan(basePackages = "com.kristina.user.sectors")
public class AppConfig {

}
