package com.itglobal.guiamais.poc;

import java.time.Instant;

import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(endpointInterface="com.itglobal.guiamais.poc.GuiaMaisService")
@Stateless
public class GuiaMaisServiceImpl implements GuiaMaisService {

	@Override
	public String saySomething(String name, String message) {
		
		System.out.println("Mensagem recebida - " + Instant.now());
		
		return "Hi " + name + "! " + message + ".";
	}

}
