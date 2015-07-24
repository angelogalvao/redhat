package com.redhat.asouza.bpms.remoteejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;

import org.jbpm.services.ejb.TaskServiceEJBLocal;
import org.jbpm.services.ejb.api.ProcessServiceEJBLocal;

import com.redhat.asouza.bpms.remoteejb.util.StartupBean;

@LocalBean
public class TestBean {
	
	@EJB
	private ProcessServiceEJBLocal processService;
	
	@EJB
	private TaskServiceEJBLocal taskService;

	public Long startProcess(){
		
		Long processId = processService.startProcess(StartupBean.DEPLOYMENT_ID, "test-project-01.test-process-01");
		
		
			
		return processId;
		
	}
}
