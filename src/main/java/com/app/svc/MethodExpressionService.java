package com.app.svc;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.springframework.stereotype.Service;

@Service
public class MethodExpressionService {

	private Expression text1;
	private Expression text2;

	public String getGender(DelegateExecution execution) {
		System.out.println("getGender: "+(String) execution.getVariable("gender"));
		return (String) execution.getVariable("gender");
	}
	
	public void myService(DelegateExecution execution, String variableParam) {
		String var = (String) execution.getVariable("var1");
		execution.setVariable("svc1", "service 1 value");
		System.out.println("Method Expression Service 1 Task executed successfully. " + var + ":" + variableParam);
	}

	public void myService2(DelegateExecution execution, String gender) {
		System.out.println("getGender: "+(String) execution.getVariable("gender"));
		if(text1!=null) {
			System.out.println("text1:- "+((String)text1.getValue(execution)).toUpperCase());
		}
		if(text2!=null) {
			System.out.println("Text2:- "+((String)text2.getValue(execution)).toUpperCase());
		}
		System.out.println("Method Expression Service 2 Task executed successfully.");
	}

	public void myService3() {
		System.out.println("Method Expression Service 3 Task executed successfully.");
	}

	public void myService4() {
		System.out.println("Method Expression Service 4 Task executed successfully.");
	}
	
	public void myService5(DelegateExecution execution) {
		System.out.println("myService5 executed successfully: "+execution.getVariable("counterVariable"));
	}
	
}