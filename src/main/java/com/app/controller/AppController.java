package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.jms.Sender;
import com.app.model.Email;

@RestController
public class AppController {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	ProcessEngine processEngine;
	
	@Autowired
	Sender sender;

	@GetMapping("test")
	public String testApi() {
		return "Hello!!!"
	}	

	@GetMapping("service/task")
	public String startTheProcessService() {
		/*
		 * try { deploy(); } catch (Exception e) { e.printStackTrace(); }
		 */
		Map<String, Object> variables = new HashMap<>();
		variables.put("var1", "Test1");
		variables.put("var2", "Test2");
		variables.put("name", "ABD");
		variables.put("gender", "Female");
		final ProcessInstance process = runtimeService.startProcessInstanceByKey("serviceTaskProcess", variables);
		// System.out.println("createExecutionQuery:
		// "+runtimeService.createExecutionQuery().processInstanceId(process.getId()).list());
		// System.out.println("processId:
		// "+runtimeService.getActiveActivityIds(process.getId()));
		return "Process completed with process Id:- " + process.getId();
	}

	@GetMapping("service/conditional")
	public String startConditionalProcess() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("var1", "Test1");
		variables.put("var2", "Test2");
		variables.put("name", "ABD");
		variables.put("gender", "Female");
		final ProcessInstance process = runtimeService.startProcessInstanceByKey("decisionProcess", variables);
		return "Process completed with process Id:- " + process.getId();
	}

	@GetMapping("service/doWhile")
	public String startWhileProcess() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("var1", "Test1");
		variables.put("var2", "Test2");
		variables.put("name", "ABD");
		variables.put("gender", "Female");
		final ProcessInstance process = runtimeService.startProcessInstanceByKey("doWhileProcess", variables);
		return "Process completed with process Id:- " + process.getId();
	}

	@GetMapping("service/complex")
	public String startVoipProcess() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("var1", "Test1");
		variables.put("var2", "Test2");
		variables.put("name", "ABD");
		variables.put("gender", "Female");
		final ProcessInstance process = runtimeService.startProcessInstanceByKey("voipTenant", variables);
		sender.sendEmailMessage("queue-mailbox", new Email("abd@gmail.com", process.getProcessInstanceId()));
		sender.sendTextMessage("queue-1", process.getId());
		return "Voip Process completed with process Id:- " + process.getId();
	}

	@GetMapping("history/{procId}")
	public void processHistory(@PathVariable String procId) {
		HistoryService historyService = processEngine.getHistoryService();
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(procId).singleResult();
		System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
	}

}
