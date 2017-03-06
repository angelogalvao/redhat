package com.redhat.consulting;

import java.util.HashSet;
import java.util.Set;

/**
 * Bean que mantem as bridges configuradas no contexto na memória.
 * en: Manage the bridges in the context.
 * 
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
public class BridgesRepository {
	
	private Bridges bridges = new Bridges();

	public Bridges getBridges() {
		return bridges;
	}

	public void setBridges(Bridges bridges) {
		this.bridges = bridges;
	}
	
	public Set<Bridge> getBridgesList(){
		return bridges.getBridges()==null?new HashSet<>():bridges.getBridges();
	}
}
