package com.redhat.consulting;

import java.util.HashSet;
import java.util.Set;

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
