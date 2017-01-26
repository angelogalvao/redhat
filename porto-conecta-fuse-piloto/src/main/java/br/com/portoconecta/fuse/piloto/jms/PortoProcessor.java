package br.com.portoconecta.fuse.piloto.jms;

import br.com.portoconecta.fuse.piloto.model.Pessoa;

public class PortoProcessor {
	
	public Pessoa processar(Pessoa pessoa){
		
		System.out.println("processor "+pessoa);
		
		return pessoa;
	}

}
