package org.remote.ejb.web;

import java.util.Hashtable;

import javax.enterprise.inject.Model;
import javax.naming.Context;
import javax.naming.NamingException;

import org.remote.ejb.service.SayHello;

@Model
public class HelloBean {

	private String message;
	
	
	public String getMessage() {
		return message;
	}
	
	public void sayHello(){
		
		message = getSayHelloBean().sayHello("Red Hat");
	}
	
	public SayHello getSayHelloBean() {
		
        final Hashtable props = new Hashtable();
        // setup the ejb: namespace URL factory
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        props.put("org.jboss.ejb.client.scoped.context", "true");
        
        props.put("jboss.naming.client.ejb.context", true);
        
        // create the InitialContext
        try {
			final Context context = new javax.naming.InitialContext(props);
			
			return (SayHello) context.lookup("ejb:/remote-ejb-service/SayHelloBean!org.remote.ejb.service.SayHello");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
