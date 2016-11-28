package com.redhat.asouza.datagrid.index.test;

import java.io.Serializable;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoField;

@ProtoDoc("@Indexed")
public class Foo implements Serializable {

	private static final long serialVersionUID = -3171723822426465748L;
	
	private Long id;	
	private String value;
	
	public Foo(){}
	
	public Foo(Long id, String value) {
		this.id = id;
		this.value = value;
	}
	
	@ProtoField(number=1)
	public Long getId() {
		return id;
	}

	@ProtoDoc("@IndexedField")
	@ProtoField(number=2)
	public String getValue() {
		return value;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foo other = (Foo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
