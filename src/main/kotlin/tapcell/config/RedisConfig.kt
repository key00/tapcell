package tapcell.config

/*
* the config class for redis cash 
* */


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheConfiguration
import java.time.Duration
import org.springframework.beans.factory.annotation.Value


@Configuration
@PropertySource(value = "classpath:application.yml" )
class RedisConfig {


    @Value(value = "\${spring.redis.port}")
    lateinit var port : String
    @Value(value = "\${spring.redis.catch.time}")
    lateinit var timeToCache : String
    @Value(value = "\${spring.redis.host}")
    lateinit var server: String

    @Bean
    fun redisConnectionFactory(): JedisConnectionFactory {
        val config = RedisStandaloneConfiguration(server, port.toInt())
        return JedisConnectionFactory(config)
    }

    @Bean
    fun redisCacheManager(jedisConnectionFactory: JedisConnectionFactory): RedisCacheManager {
        val redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(timeToCache.toLong()))

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(jedisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration).build()

    }
}
