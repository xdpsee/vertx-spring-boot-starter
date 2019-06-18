package pri.zhenhui.demo.vertx.boot.autoconfigure;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Configuration
@ConditionalOnClass(Vertx.class)
@EnableConfigurationProperties({ClusterOptionsProps.class
        , VertxOptionProps.class
        , FileSystemOptionProps.class
        , AddressResolverOptionProps.class})
public class VertxAutoConfiguration {

    @Autowired
    private VertxCustomizers customizers;

    @Bean
    public VertxOptions options(@Autowired VertxOptionProps props, @Autowired ClusterOptionsProps clusterOptionsProps) {
        VertxOptions options = new VertxOptions();

        options.setEventLoopPoolSize(props.getEventLoopPoolSize());
        options.setWorkerPoolSize(props.getWorkerPoolSize());
        options.setBlockedThreadCheckInterval(props.getBlockedThreadCheckInterval());
        options.setBlockedThreadCheckIntervalUnit(props.getBlockedThreadCheckIntervalUnit());
        options.setMaxEventLoopExecuteTime(props.getMaxEventLoopExecuteTime());
        options.setMaxEventLoopExecuteTimeUnit(props.getMaxEventLoopExecuteTimeUnit());
        options.setMaxWorkerExecuteTime(props.getMaxWorkerExecuteTime());
        options.setMaxWorkerExecuteTimeUnit(props.getMaxWorkerExecuteTimeUnit());
        options.setInternalBlockingPoolSize(props.getInternalBlockingPoolSize());
        options.setQuorumSize(props.getQuorumSize());
        options.setWarningExceptionTime(props.getWarningExceptionTime());
        options.setWarningExceptionTimeUnit(props.getWarningExceptionTimeUnit());
        options.setHAEnabled(props.isHaEnabled());
        options.setHAGroup(props.getHaGroup());
        options.setClusterManager(clusterManager(clusterOptionsProps));

        options.setMetricsOptions(new MetricsOptions().setEnabled(props.isMetricsEnabled()));
        options.setFileSystemOptions(props.getFileSystemOptions().toOptions());
        options.setAddressResolverOptions(props.getAddressResolverOptions().toOptions());

        customizers.customize(options);

        return options;
    }

    @Bean
    @ConditionalOnMissingBean(Vertx.class)
    Vertx vertx(@Autowired VertxOptions options) throws InterruptedException {

        if (options.getClusterManager() == null) {
            return Vertx.vertx(options);
        }

        AtomicReference<Vertx> ref = new AtomicReference<>(null);
        CountDownLatch latch = new CountDownLatch(1);
        Vertx.clusteredVertx(options, result -> {
            ref.set(result.result());
            latch.countDown();
        });
        latch.await();

        return ref.get();
    }

    private ClusterManager clusterManager(ClusterOptionsProps clusterOptionsProps) {
        if (ClusterType.HAZELCAST.equals(clusterOptionsProps.getType())) {
            return new HazelcastClusterManager();
        }

        return null;
    }

    @Bean
    @ConditionalOnMissingBean
    public VertxCustomizers vertxCustomizers(
            ObjectProvider<VertxCustomizer<?>> customizers) {
        return new VertxCustomizers(
                customizers.orderedStream().collect(Collectors.toList()));
    }
}

