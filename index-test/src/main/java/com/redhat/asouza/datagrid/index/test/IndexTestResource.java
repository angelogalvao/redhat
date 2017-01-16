package com.redhat.asouza.datagrid.index.test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.RandomStringUtils;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.Search;
import org.infinispan.commons.util.concurrent.FutureListener;
import org.infinispan.commons.util.concurrent.NotifyingFuture;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.slf4j.Logger;

@Path("/test")
public class IndexTestResource {

	@Inject
	private RemoteCache<Long, Foo> cache;

	@Inject
	private Logger log;

	@Path("/load")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String loadRandomCacheData() {

		Map<Long, Foo> data = new LinkedHashMap<Long, Foo>();
		for (int i = 0; i < 100000; i++) {
			Foo foo = new Foo(new Long(i + 1), RandomStringUtils.random(20, true, true));

			data.put(new Long(i + 1), foo);
		}

		NotifyingFuture<Void>  a = cache.putAllAsync(data);
		
		a.attachListener(new FutureListener<Void>() {
			
			@Override
			public void futureDone(Future<Void> future) {
				//get
				
			}
		});

		return "Data loaded in JBoss Data Grid succefully";

	}

	@Path("/clear")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String clear() {
		cache.clear();

		return "Data cleaded in JBoss Data Grid succefully";
	}

	@Path("/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Foo get() {

		long start = System.currentTimeMillis();

		Foo result = cache.get(500L);

		long end = System.currentTimeMillis();

		log.info("Tempo de acesso ao cache: " + (end - start));

		return cache.get(500L);

	}

	@Path("/get/query")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Foo getUsingQuery() {
		
		Foo random = cache.get(340L);

		long start = System.currentTimeMillis();

		QueryFactory qf = Search.getQueryFactory(cache);
		Query query = qf.from(Foo.class).having("value").like(random.getValue()).toBuilder().build();
		List<Foo> resultList = query.list();
		
		Foo result = resultList.get(0);

		long end = System.currentTimeMillis();

		log.info("Tempo de acesso ao cache: " + (end - start));

		return result;

	}

}
