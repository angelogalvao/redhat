package com.redhat.brazil.consulting.fuse.messaging.jms;

import java.util.Calendar;

import com.redhat.brazil.consulting.fuse.exception.InvalidPessoa;
import com.redhat.brazil.consulting.fuse.model.Pessoa;

/**
 * 
 * @author asouza
 *
 */
public class PessoaService {

	public String describePessoa(Pessoa pessoa, String callerIp) throws InvalidPessoa {

		Calendar now = Calendar.getInstance();
		Calendar _nascimento = Calendar.getInstance();

		_nascimento.setTime(pessoa.getNascimento());

		if ((now.get(Calendar.YEAR) - _nascimento.get(Calendar.YEAR)) < 18)
			throw new InvalidPessoa(100, "A pessoa deve ser maior de idade");

		String message = "A pessoa " + pessoa.getNome() + " possui " + _nascimento.get(Calendar.YEAR)
				+ " ano(s) de ideade. Requisição do IP: " + callerIp;

		return message;
	}
}
