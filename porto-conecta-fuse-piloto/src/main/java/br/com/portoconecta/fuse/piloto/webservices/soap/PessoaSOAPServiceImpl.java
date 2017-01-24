package br.com.portoconecta.fuse.piloto.webservices.soap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.portoconecta.fuse.piloto.exception.InvalidPessoa;
import br.com.portoconecta.fuse.piloto.model.Pessoa;

@WebService(endpointInterface="br.com.portoconecta.fuse.piloto.webservices.soap.PessoaSOAPService")
public class PessoaSOAPServiceImpl implements PessoaSOAPService {

	@Override
	@WebMethod(operationName="describePessoa")
	@WebResult(name="result")
	public String describePessoa(@WebParam(name="pessoa") Pessoa pessoa, @WebParam(name="callerIp", header=true, mode=Mode.IN) String callerIp) throws InvalidPessoa {
		
		Calendar now		 = Calendar.getInstance();
		Calendar _nascimento = Calendar.getInstance();
		
		_nascimento.setTime(pessoa.getNascimento());
		
		if((now.get(Calendar.YEAR) - _nascimento.get(Calendar.YEAR)) < 18)
			throw new InvalidPessoa("A pessoa deve ser maior de idade");
		
		String message = "A pessoa " + pessoa.getNome() + " possui " + _nascimento.get(Calendar.YEAR) + " ano(s) de ideade. Requisição do IP: " + callerIp;
		
		return message;
	}
	
	public static void main(String[] args) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		System.out.println(df.format(new Date()));
	}

}