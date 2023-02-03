package com.app.config;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

@Configuration
public class ActivitiConfig {
	/*@Autowired
	DataSource dataSource;
	
	@Autowired
	ProcessEngineFactoryBean engineBean;
	
	@Bean
	public RepositoryService getRepositoryService() throws Exception {
		RepositoryService svc = engineBean.getObject().getRepositoryService();
		svc.createDeployment()
				.addClasspathResource("bpmn20.xml")
				.deploy();
		return svc;
	}

	@Bean(name="transactionManager") 
	public DataSourceTransactionManager getTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public ProcessEngineConfigurationImpl getProcessEngineConfiguration() {
		SpringProcessEngineConfiguration res = new SpringProcessEngineConfiguration();
		res.setDataSource(dataSource);
		res.setTransactionManager(getTransactionManager());
		res.setDatabaseSchemaUpdate("create-drop");
		return res;
	}

	@Bean
	public ProcessEngineFactoryBean getProcessEngine() {
		ProcessEngineFactoryBean res = new ProcessEngineFactoryBean();
		res.setProcessEngineConfiguration(getProcessEngineConfiguration());
		return res;
	}

	@Bean
	public RepositoryService getRepositoryService() throws Exception {
		RepositoryService svc = engineBean.getObject().getRepositoryService();
		svc.createDeployment()
				.addClasspathResource("bpmn20.xml")
				.deploy();
		return svc;
	}

	@Bean
	public TaskService getTaskService() throws Exception {
		return getProcessEngine().getObject().getTaskService();
	}

	@Bean
	public RuntimeService getRuntimeService() throws Exception {
		return getProcessEngine().getObject().getRuntimeService();
	}

	@Bean
	public HistoryService getHistoryService() throws Exception {
		return getProcessEngine().getObject().getHistoryService();
	}*/

	
	
	@Bean
    public UserDetailsService myUserDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        String[][] usersGroupsAndRoles = {
        	      {"user", "password", "ROLE_ACTIVITI_USER"},
        	      {"admin", "password", "ROLE_ACTIVITI_ADMIN"},
        	   };
        for (String[] user : usersGroupsAndRoles) {
            List<String> authoritiesStrings = Arrays.asList(Arrays.copyOfRange(user, 2, user.length));
            inMemoryUserDetailsManager.createUser(new User(user[0], passwordEncoder().encode(user[1]),
                    authoritiesStrings.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList())));
        }
        return inMemoryUserDetailsManager;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
