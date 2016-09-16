package org.jnosql.diana.dictionary.infrastructure.database.redis;

import org.jnosql.diana.api.key.BucketManager;
import org.jnosql.diana.api.key.BucketManagerFactory;
import org.jnosql.diana.api.key.KeyValueConfiguration;
import org.jnosql.diana.redis.key.RedisConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class BucketManagerConfiguration {

    private BucketManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        KeyValueConfiguration configuration = new RedisConfiguration();
        managerFactory = configuration.getManagerFactory();
    }

    @Bean
    public BucketManagerFactory getManagerFactory() {
        return managerFactory;
    }

    @Bean
    public BucketManager getBucketManager() {
        return managerFactory.getBucketManager("dictionary");
    }

    @PreDestroy
    public void destroy() {
        managerFactory.close();
    }
}
