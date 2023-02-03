package com.app.svc;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Service
public class ExclusiveGatewaySvc {

	private static final int ADMIN_ROLE = 1;
	private static final int USER_ROLE = 2;
	
	public void setRole(DelegateExecution execution) {
		execution.setVariable("role", ADMIN_ROLE);
	}

	public void adminRole(DelegateExecution execution) {
		//execution.setVariable("counterVariable", 0);
		System.out.println("Perform Admin Activities");
	}

	public void userRole(DelegateExecution execution) {
		System.out.println("Perform User Activities");
	}
	
	public void cleanUp(DelegateExecution execution) {
		System.out.println("Perform clean up: ");
	}
	
	public void doWhileTask(DelegateExecution execution) {
		System.out.println("Repeat task.....");
	}

}
