package com.redhat.consulting;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bridges implements Serializable {

	private static final long serialVersionUID = -8551339536574823123L;
	
	
	private Set<Bridge> bridges;

	@XmlElement(name="bridge")
	public Set<Bridge> getBridges() {
		return bridges;
	}

	public void setBridges(Set<Bridge> bridges) {
		this.bridges = bridges;
	}

}
