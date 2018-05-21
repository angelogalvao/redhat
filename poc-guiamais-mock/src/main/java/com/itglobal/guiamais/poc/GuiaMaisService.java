package com.itglobal.guiamais.poc;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService()
public interface GuiaMaisService {

	@WebMethod
	@WebResult(name="result")
	public String saySomething(@WebParam(name="name") String name, @WebParam(name="message") String message);
}
