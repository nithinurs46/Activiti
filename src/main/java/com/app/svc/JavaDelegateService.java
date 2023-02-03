package com.app.svc;

import java.util.Map;
import java.util.stream.Stream;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.springframework.stereotype.Service;

@Service
public class JavaDelegateService implements JavaDelegate {
	
	private Expression text;
	private Expression text1;
	private Expression text2;

	@Override
	public void execute(DelegateExecution execution) {
		execution.setVariable("counterVariable", 0);
		execution.setVariable("closureBlock", false);
		Map<String, VariableInstance> variables = execution.getVariableInstances();
		Stream.of(variables.keySet().toString()).forEach(System.out::println);
		if(text!=null) {
			System.out.println("Text:- "+((String)text.getValue(execution)).toUpperCase());
		}
		if(text1!=null) {
			System.out.println("Text1:- "+((String)text1.getValue(execution)).toUpperCase());
		}
		if(text2!=null) {
			System.out.println("Text2:- "+((String)text2.getValue(execution)).toUpperCase());
		}
		System.out.println("Java Delegate Service Task executed successfully.");
	}

}
