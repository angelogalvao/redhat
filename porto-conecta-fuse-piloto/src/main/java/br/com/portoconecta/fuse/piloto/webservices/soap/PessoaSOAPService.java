package br.com.portoconecta.fuse.piloto.webservices.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.validation.Valid;

import br.com.portoconecta.fuse.piloto.exception.InvalidPessoa;
import br.com.portoconecta.fuse.piloto.model.Pessoa;

@WebService
public interface PessoaSOAPService {

	@WebMethod(operationName="describePessoa")
	@WebResult(name="result")
	public String describePessoa(@Valid @WebParam(name="pessoa") Pessoa pessoa, @WebParam(name="callerIp", header=true) String callerIp) throws InvalidPessoa;
}
