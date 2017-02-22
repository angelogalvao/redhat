package com.redhat.consulting;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class BridgeRoute extends RouteBuilder {
	
	private static final String PROPERTIES_BEAN = "properties";
	
	private Logger log = LoggerFactory.getLogger(BridgeRoute.class);

	private Bridges bridges;
	
	//@Value("bridge.redeliveryDelay")
	private String redeliveryDelay;
	
	public BridgeRoute(Bridges bridges) {
		this.bridges = bridges;
		
//		prop = (PropertiesComponent) getContext().getRegistry().lookupByName(PROPERTIES_BEAN);
	}

	@Override
	public void configure() throws Exception {
		
		List<String> routesAdded = new ArrayList<>();
				
		for (Bridge bridge : bridges.getBridges()) {
		
			String routeId = bridge.getId();
			
			Route routeAlreadyExist = getContext().getRoute(routeId);			
			
			if( routeAlreadyExist != null || routesAdded.contains(routeId)) {
				log.warn("CAMEL_ROUTE - Rota com a origem e destino já existem no contexto. IGNORANDO.");
				
				continue;
			}
			
			from(bridge.getFrom())
				.errorHandler(defaultErrorHandler().redeliveryDelay(new Long(redeliveryDelay)).maximumRedeliveries(-1))
				.routeId(routeId)
				.transacted()
				.setHeader("JMS_IBM_MsgType")
					.constant("8")
				.setHeader("JMS_IBM_MsgType")
					.simple("${headerAs(\"JMS_IBM_MsgType\", java.lang.Integer)}")				
				.throttle(bridge.getMessagesPerSecond())
					.timePeriodMillis(1000)
				.to(bridge.getTo())
				;
			
			// controle para rota duplicadas
			routesAdded.add(routeId);
		}
		
		
	}
}
