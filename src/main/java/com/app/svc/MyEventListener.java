package com.app.svc;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.jms.Sender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyEventListener implements ActivitiEventListener {
	
	@Override
	public void onEvent(ActivitiEvent event) {
		switch (event.getType()) {

		case JOB_EXECUTION_SUCCESS:
			log.info("Job executed successfully");
			break;

		case JOB_EXECUTION_FAILURE:
			log.info("Job failed");
			break;

		default:
			log.info("Event received: " + event.getType());
		}
	}

	@Override
	public boolean isFailOnException() {
		// The logic in the onEvent method of this listener is not critical, exceptions
		// can be ignored if logging fails...
		return false;
	}
}
