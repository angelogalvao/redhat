package com.redhat.brazil.consulting.fuse.webservices.rest;

import com.redhat.brazil.consulting.fuse.model.Pessoa;

public class PessoaRestTransform {
	
	public Pessoa transform(Pessoa pessoa){
		pessoa.setNome(pessoa.getNome() + " recebido");
		return pessoa;
	}
	

}
