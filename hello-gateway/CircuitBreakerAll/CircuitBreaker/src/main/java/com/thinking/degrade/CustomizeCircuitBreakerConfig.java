package com.thinking.degrade;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CustomizeCircuitBreakerConfig {

    @Bean
    public ReactiveResilience4JCircuitBreakerFactory defaultCustomizer() {

        ReactiveResilience4JCircuitBreakerFactory factory = new ReactiveResilience4JCircuitBreakerFactory(CircuitBreakerRegistry.ofDefaults(), TimeLimiterRegistry.ofDefaults());

        factory.configureDefault(id -> {

            CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom() //
                    .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED) // 滑动窗口的类型为时间窗口
                    .slidingWindowSize(5) // 时间窗口的大小为 秒
                    .minimumNumberOfCalls(5) // 在单位时间窗口内最少需要 次调用才能开始进行统计计算
                    .failureRateThreshold(50) // 在单位时间窗口内调用失败率达到 %后会启动断路器
                    .enableAutomaticTransitionFromOpenToHalfOpen() // 允许断路器自动由打开状态转换为半开状态
                    .permittedNumberOfCallsInHalfOpenState(5) // 进入halfOpen状态时，可以被调用次数，就算这些请求的失败率，低于设置的失败率变为close状态，否则变为open。
                    .waitDurationInOpenState(Duration.ofSeconds(30)) // 断路器打开状态转换为半开状态需要等待 秒
                    .recordExceptions(Throwable.class) // 所有异常都当作失败来处理
                    .build();

            return new Resilience4JConfigBuilder(id)
                    .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(200)).build())
                    .circuitBreakerConfig(circuitBreakerConfig).build();
        });

        return factory;
    }
}
