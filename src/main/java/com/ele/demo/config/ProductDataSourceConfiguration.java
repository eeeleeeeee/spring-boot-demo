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
                "com.ele.demo.datasource.product"
        },
        entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager"
)
@Slf4j
public class ProductDataSourceConfiguration {

    @Bean(name = "productDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.product")
    public DataSourceProperties productDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "productDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.product.hikari")
    public HikariDataSource productDataSource() {
        HikariDataSource hikariDataSource = productDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
        log.info("jdbc url: {}", hikariDataSource.getJdbcUrl());
        log.info("username: {}", hikariDataSource.getUsername());

        return hikariDataSource;
    }


    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder, @Qualifier("productDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {

        return builder.dataSource(dataSource)
                .packages("com.ele.demo.datasource.product.pojo")
                .persistenceUnit("product")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("productEntityManagerFactory") EntityManagerFactory managerFactory) {
        return new JpaTransactionManager(managerFactory);
    }
}
