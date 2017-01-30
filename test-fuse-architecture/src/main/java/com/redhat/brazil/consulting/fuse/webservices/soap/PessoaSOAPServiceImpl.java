package com.redhat.brazil.consulting.fuse.webservices.soap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.jws.WebService;

import com.redhat.brazil.consulting.fuse.exception.InvalidPessoa;
import com.redhat.brazil.consulting.fuse.model.Pessoa;

@WebService(endpointInterface="com.redhat.brazil.consulting.fuse.webservices.soap.PessoaSOAPService")
public class PessoaSOAPServiceImpl implements PessoaSOAPService {

	@Override	
	public String describePessoa(Pessoa pessoa, String callerIp) throws InvalidPessoa {
		
		Calendar now		 = Calendar.getInstance();
		Calendar _nascimento = Calendar.getInstance();
		
		_nascimento.setTime(pessoa.getNascimento());
		
		if((now.get(Calendar.YEAR) - _nascimento.get(Calendar.YEAR)) < 18)
			throw new InvalidPessoa(100,"A pessoa deve ser maior de idade");
		
		String message = "A pessoa " + pessoa.getNome() + " possui " + _nascimento.get(Calendar.YEAR) + " ano(s) de ideade. Requisição do IP: " + callerIp;
		
		return message;
	}
	
	public static void main(String[] args) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		System.out.println(df.format(new Date()));
	}

}
