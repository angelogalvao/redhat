package br.com.portoconecta.fuse.piloto.webservices.soap;

import javax.jws.WebService;
import javax.validation.Valid;

import br.com.portoconecta.fuse.piloto.exception.InvalidPessoa;
import br.com.portoconecta.fuse.piloto.model.Pessoa;

@WebService
public interface PessoaSOAPService {

	public String describePessoa(@Valid Pessoa pessoa, String callerIp) throws InvalidPessoa;
}
