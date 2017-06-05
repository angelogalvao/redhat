package org.remote.ejb.service.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.remote.ejb.service.SayHello;

@Stateless
@Remote(SayHello.class)
public class SayHelloBean implements SayHello {

	@Override
	public String sayHello(String message) {
		
		String result = "Hello " + message + "!";
		
		System.out.println(result);
		
		return result;
	}

}
