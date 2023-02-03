package com.app;

import javax.annotation.PostConstruct;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.config.SecurityUtil;
import com.app.svc.MyEventListener;

@SpringBootApplication
public class ActivitiApplication  {

	@Autowired
	SecurityUtil securityUtil;

	public static void main(String[] args) /* implements CommandLineRunner */{
		SpringApplication.run(ActivitiApplication.class, args);
	}

	
	@Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
            	securityUtil.logInAs("user");
                System.out.println("Number of process definitions : "
                	+ repositoryService.createProcessDefinitionQuery().count());
                //System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
                //runtimeService.startProcessInstanceByKey("voipTenant");
                //System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
            }
        };

    }

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@PostConstruct
	public void deployProcess() {
		/*Deployment deployment = repositoryService.createDeployment()
				//.addClasspathResource("processes/mainProcess.bpmn")
				//.addClasspathResource("processes/subProcess.bpmn")
				.addClasspathResource("processes/bpmn20.xml.bpmn")
				.deploy();

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("mainProcess");*/
		runtimeService.addEventListener(new MyEventListener());
	}
}
