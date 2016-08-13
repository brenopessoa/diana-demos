package org.jnosql.diana.jsr363;


import org.apache.diana.api.key.BucketManagerFactory;
import org.apache.diana.api.key.KeyValueConfiguration;
import org.apache.diana.hazelcast.key.HazelCastKeyValueConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.measure.Quantity;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;

@ApplicationScoped
public class BucketManagerProducer {

    private BucketManagerFactory managerFactory;

    private static final Map<String, String> CONFIGURATION = singletonMap("hazelcast-instanceName", "hazelcast");

    @PostConstruct
    public void init() {
        KeyValueConfiguration configuration = new HazelCastKeyValueConfiguration();
        managerFactory = configuration.getManagerFactory(CONFIGURATION);
    }

    @PreDestroy
    public void destroy() {
        managerFactory.close();
    }

    @Produces
    public List<Quantity> getList() {
        return managerFactory.getList("temperature", Quantity.class);
    }

}
