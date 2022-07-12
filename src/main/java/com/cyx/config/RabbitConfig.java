package com.cyx.config;

import com.github.chengyuxing.sql.Baki;
import com.github.chengyuxing.sql.BakiDao;
import com.github.chengyuxing.sql.XQLFileManager;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitConfig {

    @Bean(name = "otherDatasource")
    @ConfigurationProperties(prefix = "other-datasource")
    public HikariDataSource otherDatasource() {
        return new HikariDataSource();
    }

    @Bean(name = "masterDatasource")
    @ConfigurationProperties(prefix = "master-datasource")
    public HikariDataSource masterDatasource() {
        return new HikariDataSource();
    }

    @Bean(initMethod = "init")
    @ConfigurationProperties(prefix = "xql")
    public XQLFileManager xqlFileManager() {
        return new XQLFileManager();
    }

    @Bean(name = "masterBaki")
    @Primary
    public Baki baki() {
        BakiDao bakiDao = new BakiDao(masterDatasource());
        bakiDao.setXqlFileManager(xqlFileManager());
        return bakiDao;
    }

    @Bean(name = "otherBaki")
    public Baki otherBaki() {
        BakiDao bakiDao = new BakiDao(otherDatasource());
        bakiDao.setXqlFileManager(xqlFileManager());
        return bakiDao;
    }
}
