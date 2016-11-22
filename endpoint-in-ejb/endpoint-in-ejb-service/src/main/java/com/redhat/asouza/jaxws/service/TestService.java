package com.redhat.asouza.jaxws.service;

import javax.ejb.Remote;

@Remote
public interface TestService {

	public void sayHello();
}
