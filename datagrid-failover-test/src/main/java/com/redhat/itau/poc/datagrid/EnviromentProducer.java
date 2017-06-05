package com.redhat.itau.poc.datagrid;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class EnviromentProducer {

	private RemoteCacheManager cacheManagerDc1;
	private RemoteCacheManager cacheManagerDc2;

	@Produces
	public Logger createLog(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

	@PostConstruct
	public void configureCache() {
		
		if( cacheManagerDc1 == null ){ 
			Configuration conf = new ConfigurationBuilder()
										.addCluster("master")
											.addClusterNode("localhost", 11223)
											.addClusterNode("localhost", 11224)
										.addCluster("backup")
											.addClusterNode("localhost", 11225)
											.addClusterNode("localhost", 11226)
										.build();
		
			cacheManagerDc1 = new RemoteCacheManager(conf);
		}
		
		if( cacheManagerDc2 == null ){ 
			Configuration conf = new ConfigurationBuilder()
										.addCluster("master")
											.addClusterNode("localhost", 11225)
											.addClusterNode("localhost", 11226)
										.addCluster("backup")
											.addClusterNode("localhost", 11223)
											.addClusterNode("localhost", 11224)
										.build();
		
			cacheManagerDc2 = new RemoteCacheManager(conf);
		}
		
	}

	@Produces
	@ItauCacheDc1
	public RemoteCache<Object, Object> getDefaultCacheDc1() {
		return cacheManagerDc1.getCache();
	}
	
	@Produces
	@ItauCacheDc2
	public RemoteCache<Object, Object> getDefaultCacheDc2() {
		return cacheManagerDc2.getCache();
	}
}
