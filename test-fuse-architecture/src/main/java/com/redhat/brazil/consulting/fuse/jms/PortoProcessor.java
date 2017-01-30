package com.redhat.brazil.consulting.fuse.jms;

import com.redhat.brazil.consulting.fuse.model.Pessoa;

public class PortoProcessor {
	
	public Pessoa processar(Pessoa pessoa){
		
		System.out.println("processor "+pessoa);
		
		return pessoa;
	}

}
