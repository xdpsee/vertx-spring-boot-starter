package pri.zhenhui.demo.vertx.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.concurrent.TimeUnit;


@Data
@ConfigurationProperties(
        prefix = "vertx.options"
)
public class VertxOptionProps {

    private boolean haEnabled = io.vertx.core.VertxOptions.DEFAULT_HA_ENABLED;
    private String haGroup = io.vertx.core.VertxOptions.DEFAULT_HA_GROUP;

    private int eventLoopPoolSize = io.vertx.core.VertxOptions.DEFAULT_EVENT_LOOP_POOL_SIZE;
    private int workerPoolSize = io.vertx.core.VertxOptions.DEFAULT_WORKER_POOL_SIZE;
    private int internalBlockingPoolSize = io.vertx.core.VertxOptions.DEFAULT_INTERNAL_BLOCKING_POOL_SIZE;
    private long blockedThreadCheckInterval = io.vertx.core.VertxOptions.DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL;
    private long maxEventLoopExecuteTime = io.vertx.core.VertxOptions.DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME;
    private long maxWorkerExecuteTime = io.vertx.core.VertxOptions.DEFAULT_MAX_WORKER_EXECUTE_TIME;
    private int quorumSize = io.vertx.core.VertxOptions.DEFAULT_QUORUM_SIZE;
    private long warningExceptionTime = TimeUnit.SECONDS.toNanos(5);
    private boolean preferNativeTransport = io.vertx.core.VertxOptions.DEFAULT_PREFER_NATIVE_TRANSPORT;
    private TimeUnit maxEventLoopExecuteTimeUnit = io.vertx.core.VertxOptions.DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME_UNIT;
    private TimeUnit maxWorkerExecuteTimeUnit = io.vertx.core.VertxOptions.DEFAULT_MAX_WORKER_EXECUTE_TIME_UNIT;
    private TimeUnit warningExceptionTimeUnit = io.vertx.core.VertxOptions.DEFAULT_WARNING_EXCEPTION_TIME_UNIT;
    private TimeUnit blockedThreadCheckIntervalUnit = io.vertx.core.VertxOptions.DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL_UNIT;

    private boolean metricsEnabled = false;

    @NestedConfigurationProperty
    private FileSystemOptionProps fileSystemOptions = new FileSystemOptionProps();
    @NestedConfigurationProperty
    private AddressResolverOptionProps addressResolverOptions = new AddressResolverOptionProps();

}


