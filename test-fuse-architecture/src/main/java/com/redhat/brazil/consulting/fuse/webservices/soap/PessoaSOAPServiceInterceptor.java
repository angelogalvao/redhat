package com.redhat.brazil.consulting.fuse.webservices.soap;

import java.util.List;
import java.util.Map;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interceptor de requsições SOAP.
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
public class PessoaSOAPServiceInterceptor extends AbstractSoapInterceptor {
	
	private Logger log = LoggerFactory.getLogger(PessoaSOAPServiceInterceptor.class);
	
	public PessoaSOAPServiceInterceptor() {
		super(Phase.READ);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		log.info("Exceutando a interceptação da mensagem");

//		Map<String, List<String>> headers = CastUtils.cast((Map)message.get(Message.PROTOCOL_HEADERS));
		
		List<Header> headers = message.getHeaders();

//		 List<String> callerIp = headers.get("callerIp");
//		 
//		 if(callerIp == null || callerIp.isEmpty()) {
//			 log.info("Header vazio");
//		 } else {
//			 log.info("Header preenchido");
//		 }
		
	}



}
