package com.redhat.asouza.datagrid.index.test;

import java.io.IOException;

import org.infinispan.protostream.MessageMarshaller;

public class FooMarshaller implements MessageMarshaller<Foo> {

	@Override
	public Class<? extends Foo> getJavaClass() {
		return Foo.class;
	}

	@Override
	public String getTypeName() {
		return "com.redhat.asouza.datagrid.index.test.Foo";
	}

	@Override
	public Foo readFrom(ProtoStreamReader protoStreamReader) throws IOException {
		Long   id    = protoStreamReader.readLong("id");
		String value = protoStreamReader.readString("value");
		return new Foo(id, value);
	}

	@Override
	public void writeTo(ProtoStreamWriter protoStreamWriter, Foo foo) throws IOException {
		protoStreamWriter.writeLong("id", foo.getId());
		protoStreamWriter.writeString("value", foo.getValue());
		
	}

}
