package com.redhat.brazil.consulting.fuse.webservices.rest;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.redhat.brazil.consulting.fuse.exception.InvalidPessoa;
import com.redhat.brazil.consulting.fuse.model.Pessoa;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * Endpoint REST.
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
@Path("/pessoa")
@Api(value = "/pessoa", description = "Operações da Entidade Pessoa.")
public interface PessoaRESTService {

	@Path("/describe")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Descreve as propriedades da pessoa", notes = "Mais dadodos abaixo", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Pessoa Inválida."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
	public String describePessoa(@Valid Pessoa pessoa, @HeaderParam("callerIp") String callerIp) throws InvalidPessoa;

}