package com.redhat.brazil.consulting.fuse.webservices.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.validation.Valid;

import com.redhat.brazil.consulting.fuse.exception.InvalidPessoa;
import com.redhat.brazil.consulting.fuse.model.Pessoa;

/**
 * Interface do endpoint SOAP (usada para gerar o WSDL).
 * 
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
@WebService
public interface PessoaSOAPService {

	@WebMethod(operationName="describePessoa")
	@WebResult(name="result")
	public String describePessoa(@Valid @WebParam(name="pessoa") Pessoa pessoa, @WebParam(name="callerIp", header=true) String callerIp) throws InvalidPessoa;
}
