package com.redhat.asouza;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.redhat.asouza.jaxws.service.TestService;

@WebService(name="TestService", serviceName="Service")
@Stateless
public class TestServiceImpl implements TestService {

	@Override
	public void sayHello() {
		
		System.out.println("TESTE");
		
	}

}
