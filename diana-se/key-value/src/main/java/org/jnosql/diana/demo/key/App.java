package org.jnosql.diana.demo.key;


import org.jnosql.diana.api.Value;
import org.jnosql.diana.api.key.BucketManager;
import org.jnosql.diana.api.key.BucketManagerFactory;
import org.jnosql.diana.api.key.KeyValueConfiguration;
import org.jnosql.diana.hazelcast.key.HazelCastKeyValueConfiguration;

import java.util.*;

public class App {


    public static void main(String[] args) {

        KeyValueConfiguration configuration = new HazelCastKeyValueConfiguration();
        try (BucketManagerFactory managerFactory = configuration.get()) {
            BucketManager bucket = managerFactory.getBucketManager("bucket");
            List<String> list = managerFactory.getList("bucketList", String.class);
            Set<String> set = managerFactory.getSet("bucketSet", String.class);
            Map<String, Integer> map = managerFactory.getMap("bucketList", String.class, Integer.class);
            Queue<String> queue = managerFactory.getQueue("queueList", String.class);
            bucket.put("key", "value");
            Optional<Value> value = bucket.get("key");
        }


    }
}

