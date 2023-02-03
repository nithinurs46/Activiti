package com.app.svc;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ComplexService {

	private static final String BLOCK1_COMPLETE = "block1Complete";
	private static final String BLOCK2_COMPLETE = "block2Complete";
	private static final String BLOCK3_COMPLETE = "block3Complete";
	private static final String BLOCK4_COMPLETE = "block4Complete";
	
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private RepositoryService repositoryService;
	
	private static final String COUNTER_VARIABLE = "counterVariable";

	public void startProcess(DelegateExecution execution) {
		log.info("Executed startProcess()");
	}

	public void processStart(DelegateExecution execution) throws Exception {
		log.info("Executed processStart()");
	}

	public void taskInitialize(DelegateExecution execution) {
		execution.setVariable(COUNTER_VARIABLE, 0);
		execution.setVariable(BLOCK1_COMPLETE, false);
		log.info("Executed createTASK_Initialize()");
	}

	public void taskNotCompleted(DelegateExecution execution) {
		int counter = (int) execution.getVariable(COUNTER_VARIABLE);
		counter++;
		execution.setVariable(COUNTER_VARIABLE, counter);
		if(counter == 11) {
			log.info("block1Complete: "+(boolean) execution.getVariable(BLOCK1_COMPLETE));
			execution.setVariable(BLOCK1_COMPLETE, true);
			execution.setVariable(BLOCK2_COMPLETE, true);
			execution.setVariable(BLOCK3_COMPLETE, true);
			execution.setVariable(BLOCK4_COMPLETE, true);
		}
		log.info("Executed taskNotCompleted(): "+ counter);
	}

	public void notifyProcessOnTaskCompletion(DelegateExecution execution) {
		log.info("Executed notifyProcessOnTaskCompletion(): "+execution.getVariable(COUNTER_VARIABLE));
	}
	
	public void taskWait1(DelegateExecution execution) {
		log.info("Executed taskWait1():- "
				+ execution.getVariable(BLOCK1_COMPLETE) + " : " + execution.getVariable(COUNTER_VARIABLE));
		execution.setVariable(COUNTER_VARIABLE, 0);
		execution.setVariable(BLOCK2_COMPLETE, false);
	}

	public void taskWaitForRelatedClosure1(DelegateExecution execution) {
		log.info("Executed taskWaitForRelatedClosure1():- "
				+ execution.getVariable(BLOCK2_COMPLETE) + " : " + execution.getVariable(COUNTER_VARIABLE));
		execution.setVariable("relatedServiceOrdersClosed", true);
	}
	
	public void taskWaitForRelatedClosure2(DelegateExecution execution) {
		execution.setVariable(COUNTER_VARIABLE, 0);
		execution.setVariable(BLOCK3_COMPLETE, false);
		log.info("Executed taskWaitForRelatedClosure2 ");
	}
	
	public void taskSend(DelegateExecution execution) {
		execution.setVariable(COUNTER_VARIABLE, 0);
		execution.setVariable(BLOCK4_COMPLETE, false);
		log.info("Executed taskSend ");
	}
	
	public void processCtrlProcessComplete(DelegateExecution execution) {
		log.info("Executed processCtrlProcessComplete ");
	}
	
	public void errorHandler(DelegateExecution execution) {
		log.info("Handle Error.. ");
		String errorCode = (String) execution.getVariable("errorCode");
	    if (errorCode.equals("exampleErrorCode")) {
	    	log.info("Handle Error exampleErrorCode.. ");
	    } else if (errorCode.equals("anotherErrorCode")) {
	    } else {
	    }
	}
}
