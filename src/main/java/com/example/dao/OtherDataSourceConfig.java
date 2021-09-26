package com.example.dao;

import java.util.HashMap;
import java.util.Map;
 
import javax.persistence.EntityManager;
import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactoryOther",
		transactionManagerRef="transactionManagerOther",
	    basePackages={"com.example.dao.repository.other"})
public class OtherDataSourceConfig {
 
	@Autowired
	@Qualifier("otherDataSource")
	private DataSource otherDataSource;
	
	@Autowired
	private JpaProperties jpaProperties;
	
	@Bean(name="entityManagerOther")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder){
		return entityManagerFactoryBean(builder).getObject().createEntityManager();
	}
	
	@Bean(name="entityManagerFactoryOther")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder){
		return builder
				.dataSource(otherDataSource)
				.properties(getProperties())
				.packages("com.example.dao.domain.other")
				.persistenceUnit("otherPersistentUnit")
				.build();
	}
	
	public Map<String, String> getProperties(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("format_sql", "true");
		map.put("max_fetch_depth", "1");
		return map;
	}
	
	@Bean(name="transactionManagerOther")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
		return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
	}
}