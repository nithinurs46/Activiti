package com.app.svc;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class NotificationTask implements JavaDelegate {

	private static final String COUNTER_VARIABLE = "counterVariable";

	@Override
	public void execute(DelegateExecution execution) {
		int counter = (int) execution.getVariable(COUNTER_VARIABLE);
		System.out.println("Executing notification task.. " + counter);
		counter++;
		if(counter == 11) {
			System.out.println("closureBlock: "+(boolean) execution.getVariable("closureBlock"));
			execution.setVariable("closureBlock", true);
		}
		execution.setVariable(COUNTER_VARIABLE, counter);

	}

}
