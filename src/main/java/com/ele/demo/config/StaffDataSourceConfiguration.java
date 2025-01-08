package com.ele.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
                "com.ele.demo.datasource.staff"
        },
        entityManagerFactoryRef = "staffEntityManagerFactory",
        transactionManagerRef = "staffTransactionManager"
)
@Slf4j
public class StaffDataSourceConfiguration {

    @Primary
    @Bean(name = "staffDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.staff")
    public DataSourceProperties staffDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "staffDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.staff.hikari")
    public HikariDataSource staffDataSource() {
        HikariDataSource hikariDataSource = staffDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
        log.info("jdbc url: {}", hikariDataSource.getJdbcUrl());
        log.info("username: {}", hikariDataSource.getUsername());

        return hikariDataSource;
    }

    @Primary
    @Bean(name = "staffEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder, @Qualifier("staffDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {

        return builder.dataSource(dataSource)
                .packages("com.ele.demo.datasource.staff.pojo")
                .persistenceUnit("staff")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Primary
    @Bean(name = "staffTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("staffEntityManagerFactory") EntityManagerFactory managerFactory) {
        return new JpaTransactionManager(managerFactory);
    }
}
