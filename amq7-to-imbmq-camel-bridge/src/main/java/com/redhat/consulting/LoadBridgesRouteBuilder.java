package com.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

/**
 * Carrega as configurações das bridges.
 * en: load the bridges configuration files.
 *  
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
public class LoadBridgesRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
			
		String from = System.getProperty("artemis.instance");
		
		if(from == null) 
			throw new IllegalStateException("Necessário que a propriedade de sistema 'artemis.instance' esteja presente.");
		
		DataFormat jaxb = new JaxbDataFormat("com.redhat.consulting");

		
		from("file:"+from+"/etc/bridges?noop=true")
			.routeId("load-routes")
			.unmarshal(jaxb)
			.log("Criado a bridge baseado no arquivo: ${file:name}.")
			.to("bean:bridgesRepository?method=setBridges")
			.to("bean:dynamicRoutesProcessor")
			;
	}

}
