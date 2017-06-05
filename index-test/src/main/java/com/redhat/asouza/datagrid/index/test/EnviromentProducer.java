package com.redhat.asouza.datagrid.index.test;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.configuration.NearCacheMode;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.protostream.annotations.ProtoSchemaBuilder;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class EnviromentProducer {

	private RemoteCacheManager cacheManager;

	@Produces
	public Logger createLog(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

	@PostConstruct
	public void configureCache() throws Exception {

		Configuration conf = new ConfigurationBuilder().tcpNoDelay(true)
				.nearCache().mode(NearCacheMode.LAZY).maxEntries(100)
				.addServers("localhost")
				.marshaller(new ProtoStreamMarshaller())				
				.build();

		cacheManager = new RemoteCacheManager(conf);

		// registar os marsshallers
		SerializationContext ctx = ProtoStreamMarshaller.getSerializationContext(cacheManager);

		//ctx.registerMarshaller(new FooMarshaller());
		
		ProtoSchemaBuilder protoSchemaBuilder = new ProtoSchemaBuilder();
		String fooSchemaFile = protoSchemaBuilder.fileName("foo.proto")
												 .packageName("test")
												 .addClass(Foo.class)												 
												 .build(ctx);			
		
		// register the schemas with the server too
		RemoteCache<String, String> metadataCache = cacheManager.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
		metadataCache.put("foo.proto", fooSchemaFile);
		String errors = metadataCache.get(ProtobufMetadataManagerConstants.ERRORS_KEY_SUFFIX);
		if (errors != null) {
			throw new IllegalStateException("Some Protobuf schema files contain errors:\n" + errors);
		}

	}

	@Produces
	public RemoteCache<Long, Foo> getDefaultCache() {
		return cacheManager.getCache("index_cache_test");
	}
}
