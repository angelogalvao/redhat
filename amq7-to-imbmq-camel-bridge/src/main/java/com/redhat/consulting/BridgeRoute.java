package com.redhat.consulting;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Rota que cria a ponte entre o AMQ-7 e o IBM MQSeries
 * en: Route that create the bridge between AMQ-7 and IBM MQSeries
 * 
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
public class BridgeRoute extends RouteBuilder {
		
	private Logger log = LoggerFactory.getLogger(BridgeRoute.class);

	private Bridges bridges;
	
	private Long redeliveryDelay;
	
	public BridgeRoute(Bridges bridges, Long redeliveryDelay) {
		this.bridges = bridges;
		this.redeliveryDelay = redeliveryDelay;
	}

	@Override
	public void configure() throws Exception {
		
		List<String> routesAdded = new ArrayList<>();
				
		for (Bridge bridge : bridges.getBridges()) {
		
			String routeId = bridge.getId();
			
			Route routeAlreadyExist = getContext().getRoute(routeId);			
			
			if( routeAlreadyExist != null || routesAdded.contains(routeId)) {
				
				// en: The route already exists and will be ignored
				log.warn("A rota " + bridge.getId() + " já existe no contexto e será ignorada.");
				
				continue;
			}
			
			from(bridge.getFrom())
				.errorHandler(defaultErrorHandler().redeliveryDelay(redeliveryDelay).maximumRedeliveries(-1))
				.routeId(routeId)
				//.transacted()
				.log("Message: ${body}")
				.setHeader("JMS_IBM_MsgType")
					.constant("8")
				.setHeader("JMS_IBM_MsgType")
					.simple("${headerAs(\"JMS_IBM_MsgType\", java.lang.Integer)}")				
				.throttle(bridge.getMessagesPerSecond())
					.timePeriodMillis(1000)
				.to(bridge.getTo())
				;
			
			// controle para rota duplicadas
			// en: Avoid duplicate routes
			routesAdded.add(routeId);
		}
		
		
	}
}
