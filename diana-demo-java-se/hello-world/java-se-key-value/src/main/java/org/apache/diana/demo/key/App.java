package org.apache.diana.demo.key;


import org.apache.diana.api.Value;
import org.apache.diana.api.key.BucketManager;
import org.apache.diana.api.key.BucketManagerFactory;
import org.apache.diana.api.key.KeyValueConfiguration;
import org.apache.diana.hazelcast.key.HazelCastKeyValueConfiguration;

import java.util.*;

public class App {


    public static void main(String[] args) {

        KeyValueConfiguration configuration = new HazelCastKeyValueConfiguration();
        try (BucketManagerFactory managerFactory = configuration.getManagerFactory()) {
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

