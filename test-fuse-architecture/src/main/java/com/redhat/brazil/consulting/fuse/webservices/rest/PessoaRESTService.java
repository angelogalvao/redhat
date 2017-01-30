package com.redhat.brazil.consulting.fuse.webservices.rest;

import java.util.Calendar;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.brazil.consulting.fuse.exception.InvalidPessoa;
import com.redhat.brazil.consulting.fuse.model.Pessoa;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/pessoa")
@Api(value = "/pessoa", description = "Operações da Entidade Pessoa.")
public class PessoaRESTService {

	@Path("/describe")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Descreve as propriedades da pessoa", notes = "Mais dadodos abaixo", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Pessoa inválida")            
    })	
	public String describePessoa(@Valid Pessoa pessoa, @HeaderParam("callerIp") String callerIp) throws InvalidPessoa {
		
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
		calendar.set(Calendar.YEAR, 2009);
		
		Pessoa pessoa = new Pessoa("Sergio", calendar.getTime());
		
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println(mapper.writeValueAsString(pessoa));
	}
}
