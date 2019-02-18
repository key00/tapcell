package tapcell

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Import
import tapcell.config.RedisConfig

@SpringBootApplication
@EnableCaching
class AppContext
    fun main(args: Array<String>) {
        SpringApplication.run(AppContext::class.java, *args)
    }
