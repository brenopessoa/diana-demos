package org.jnosql.diana.jsr363;


import org.jnosql.diana.api.key.BucketManagerFactory;
import org.jnosql.diana.hazelcast.key.HazelCastKeyValueConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.measure.Quantity;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;

@ApplicationScoped
public class BucketManagerProducer {

    private BucketManagerFactory managerFactory;

    private static final Map<String, String> CONFIGURATION = singletonMap("hazelcast-instanceName", "hazelcast");

    @PostConstruct
    public void init() {
        HazelCastKeyValueConfiguration configuration = new HazelCastKeyValueConfiguration();
        managerFactory = configuration.get(CONFIGURATION);
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
