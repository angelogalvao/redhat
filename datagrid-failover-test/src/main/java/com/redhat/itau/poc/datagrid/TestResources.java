package com.redhat.itau.poc.datagrid;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.infinispan.client.hotrod.RemoteCache;
import org.slf4j.Logger;

@Path("/test")
public class TestResources {
	
	@Inject
	@ItauCacheDc1
	private RemoteCache<Object, Object> cacheDc1;
	
	@Inject
	@ItauCacheDc2
	private RemoteCache<Object, Object> cacheDc2;
	
	@Inject
	private Logger log;

	@Path("/dc1/put")
	@GET
	public Object put1(){
		log.info("REDHAT: Putting key = REDHAT with value = ITAU-DC1 at DC1");
		
		Object result = cacheDc1.put("REDHAT", "ITAU-DC1");
		
		return result == null ? "" : result;
		
	}
	
	@Path("/dc1/get")
	@GET
	public String get1(){
		
		log.info("REDHAT: Getting key = REDHAT at DC1");
		
		return "Result DC1= " + (String) cacheDc1.get("REDHAT");
	}
	
	@Path("/dc2/put")
	@GET
	public Object put2(){
		
		log.info("REDHAT: Putting key = REDHAT with value = ITAU-DC2 at DC2");
		
		Object result = cacheDc2.put("REDHAT", "ITAU-DC2");
		
		return result == null ? "" : result;
	}
	
	@Path("/dc2/get")
	@GET
	public String get2(){
		
		log.info("REDHAT: Getting key = REDHAT at DC2");
		
		return "Result DC2= " + (String) cacheDc2.get("REDHAT");
	}
}
