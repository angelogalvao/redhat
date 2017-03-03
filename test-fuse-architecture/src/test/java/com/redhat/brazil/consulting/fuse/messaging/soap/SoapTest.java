package com.redhat.brazil.consulting.fuse.messaging.soap;

import org.junit.Before;
import org.junit.Test;

import br.com.portoconecta.services.gerenciarcontratos.GerenciarContratos;
import br.com.portoconecta.services.gerenciarcontratos.GerenciarContratosService;
import br.com.portoconecta.services.gerenciarcontratos.Identificacao;
import br.com.portoconecta.services.gerenciarcontratos.ObterContratoRequest;
import br.com.portoconecta.services.gerenciarcontratos.ObterContratoResponse;


public class SoapTest {
	
	private GerenciarContratos ss;
    private GerenciarContratosService port;
    
    @Before
    public void conectar(){
    	
    	ss = new GerenciarContratos();
        port = ss.getGerenciarContratosService();  
    	
    }
    
    @Test
    public void testarObterContrato(){
    	System.out.println("Invoking obterContrato...");
        ObterContratoRequest obterContratoRequest = new ObterContratoRequest();
        obterContratoRequest.setNumeroDoContrato(47242);
        Identificacao identificacao = new Identificacao();
        identificacao.setAtendente("Judite");
        identificacao.setNomeDoSistema("CRM");
        identificacao.setProtocolo("123");
        identificacao.setSolicitante("Sergio");
        identificacao.setUnidadeDeNegocio(4);
        obterContratoRequest.setIdentificacao(identificacao);
        
        ObterContratoResponse obterContratoResponse = port.obterContrato(obterContratoRequest);
        System.out.println("obterContrato.result=" + obterContratoResponse.getContrato().getCpfVendedor());
    }

}
