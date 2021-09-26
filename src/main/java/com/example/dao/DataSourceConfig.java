package com.example.dao;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
@Configuration
public class DataSourceConfig {
	@Bean(name="dataSource")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource(){
		//return DataSourceBuilder.create().build();
		return dataSourceProperties().initializeDataSourceBuilder().build();
	}
	
	@Primary
	@Bean(name="dataSourceProperties")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSourceProperties dataSourceProperties(){
		return new DataSourceProperties();
	}
	
	
	@Bean(name="otherDataSource")
	@ConfigurationProperties(prefix="spring.datasource.other")
	public DataSource otherDataSource(){
		//return DataSourceBuilder.create().build();
		return otherDataSourceProperties().initializeDataSourceBuilder().build();
	}
	
	
	@Bean(name="otherDataSourceProperties")
	@ConfigurationProperties(prefix="spring.datasource.other")
	public DataSourceProperties otherDataSourceProperties(){
		return new DataSourceProperties();
	}
	
}
