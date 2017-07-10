/*
 * Copyright (c) 2017 Otávio Santana and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Otavio Santana
 *
 */
package org.jnosql.diana.demo.redis;

import java.util.Optional;

import org.jnosql.diana.api.Value;
import org.jnosql.diana.api.key.BucketManager;
import org.jnosql.diana.api.key.BucketManagerFactory;
import org.jnosql.diana.api.key.KeyValueConfiguration;
import org.jnosql.diana.redis.key.RedisConfiguration;

public class App {
	
	public static final String KEY = "whats is the NOSQL standard for java?";
	public static final String VALUE = "JNOSQL";
	

	public static void main(String[] args) {
		
		 KeyValueConfiguration<?> configuration = new RedisConfiguration();
	        try (BucketManagerFactory<?> managerFactory = configuration.get()) {
	            BucketManager bucket = managerFactory.getBucketManager("bucket");
	           
	            bucket.put(KEY,VALUE);
	            Optional<Value> value = bucket.get(KEY);
	            value.ifPresent(v-> System.out.println(v.get()));
	        }

	}
	private App() { }
}
