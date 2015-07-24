package com.redhat.asouza.bpms.remoteejb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test") @SessionScoped
public class TestResource  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private transient TestBean testBean;
	
	@Path("/start") @GET
	public Long startProcess(){
		return testBean.startProcess();
		
	}

}
