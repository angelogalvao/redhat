package com.redhat.brazil.consulting.fuse.webservices.rest;

import java.util.Calendar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.brazil.consulting.fuse.exception.InvalidPessoa;
import com.redhat.brazil.consulting.fuse.model.Pessoa;

/**
 * Endpoint REST.
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
public class PessoaRESTServiceImpl implements PessoaRESTService {

	@Override	
	public String describePessoa( Pessoa pessoa, String callerIp) throws InvalidPessoa {
		
		Calendar now		 = Calendar.getInstance();
		Calendar _nascimento = Calendar.getInstance();
		
		_nascimento.setTime(pessoa.getNascimento());
		
		if((now.get(Calendar.YEAR) - _nascimento.get(Calendar.YEAR)) < 18)
			throw new InvalidPessoa(100,"A pessoa deve ser maior de idade");
		
		String message = "A pessoa " + pessoa.getNome() + " possui " + _nascimento.get(Calendar.YEAR) + " ano(s) de ideade. Requisição do IP: " + callerIp;
		
		return message;
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1990);
		
		Pessoa pessoa = new Pessoa("Teste", calendar.getTime());
		
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println(mapper.writeValueAsString(pessoa));
	}
}
