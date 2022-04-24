package by.brel.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(
                "customers",
                "customer",
                "tariffs",
                "tariff",
                "balances",
                "balance"
        );
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(500)
                .weakKeys()
                .recordStats()
        );

        return cacheManager;
    }
}
