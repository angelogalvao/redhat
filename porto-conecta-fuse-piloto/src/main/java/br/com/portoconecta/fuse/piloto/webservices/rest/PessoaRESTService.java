package br.com.portoconecta.fuse.piloto.webservices.rest;

import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import br.com.portoconecta.fuse.piloto.exception.InvalidPessoa;
import br.com.portoconecta.fuse.piloto.model.Pessoa;

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
		Pessoa pessoa = new Pessoa("Angelo", new Date(110, 1, 1));
		
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println(mapper.writeValueAsString(pessoa));
	}
}
