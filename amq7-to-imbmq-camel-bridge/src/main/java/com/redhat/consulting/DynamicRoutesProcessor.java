package com.redhat.consulting;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Bean que cria as todas dinamicamente.
 * en: create dinamic routes
 * 
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
public class DynamicRoutesProcessor implements Processor {	
	
	private String redeliveryDelay;
	
	public DynamicRoutesProcessor(String redeliveryDelay) {
		this.redeliveryDelay = redeliveryDelay;
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {		
		Bridges bridges = (Bridges) exchange.getIn().getBody();
		
		
		exchange.getContext().addRoutes(new BridgeRoute(bridges, new Long(redeliveryDelay)));
		
	}

}
