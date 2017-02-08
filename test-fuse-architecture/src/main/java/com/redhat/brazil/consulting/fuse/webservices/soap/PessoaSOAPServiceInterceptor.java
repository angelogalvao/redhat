package com.redhat.brazil.consulting.fuse.webservices.soap;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
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
		super(Phase.UNMARSHAL);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		log.info("Exceutando a interceptação da mensagem: ");

		List<Header> headers = message.getHeaders();
		
		Header header = null;
		for (Header _header : headers) {
			QName name = _header.getName();
			
			if( name.getLocalPart().equals("callerIp") ){
				header = _header;
				break;
			}
				
		}
		
		if( header == null ) {
			log.info("A mensagem SOAP não possui o header callerIp");
			
			throw new Fault("A mensagem SOAP não possui o header callerIp",  java.util.logging.Logger.getLogger(this.getClass().getName()));
		} 
		
	}

}
