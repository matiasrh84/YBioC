package com.ybc.ybioq.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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
        basePackages = "com.ybc.ybioq.repository.remote",
        entityManagerFactoryRef = "remoteEntityManagerFactory",
        transactionManagerRef = "remoteTransactionManager"
)
public class RemoteDataSourceConfig {

    @Bean(name = "remoteDataSource")
    @ConfigurationProperties(prefix = "spring.remote.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean remoteEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("remoteDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.ybc.ybioq.entity.remote") // Paquete donde están las entidades de la base remota
                .persistenceUnit("remote")
                .build();
    }

    @Bean
    public PlatformTransactionManager remoteTransactionManager(
            @Qualifier("remoteEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
