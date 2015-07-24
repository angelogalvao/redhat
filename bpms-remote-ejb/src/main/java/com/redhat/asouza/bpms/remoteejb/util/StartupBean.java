package com.redhat.asouza.bpms.remoteejb.util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.model.DeploymentUnit;
import org.jbpm.services.ejb.api.DeploymentServiceEJBLocal;
import org.kie.api.runtime.manager.RuntimeManager;

@Singleton
@Startup
public class StartupBean {

	public static final String DEPLOYMENT_ID = "com.redhat:test-project-01:1.0";

    @EJB
    private DeploymentServiceEJBLocal deploymentService;

    @PostConstruct
    public void init() {
        System.setProperty("org.jbpm.ht.callback", "custom");
        System.setProperty("org.jbpm.ht.custom.callback", "com.redhat.asouza.bpms.remoteejb.util.SimpleUserGroupCallback");
        String[] gav = DEPLOYMENT_ID.split(":");
        DeploymentUnit deploymentUnit = new KModuleDeploymentUnit(gav[0], gav[1], gav[2]);
        
        deploymentService.deploy(deploymentUnit);
        
        
        RuntimeManager runtimeManager = deploymentService.getDeployedUnit(DEPLOYMENT_ID).getRuntimeManager();
                
    }
	
}
