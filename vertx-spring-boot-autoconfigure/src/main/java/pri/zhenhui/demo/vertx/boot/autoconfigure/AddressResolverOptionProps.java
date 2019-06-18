package pri.zhenhui.demo.vertx.boot.autoconfigure;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.dns.AddressResolverOptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static io.vertx.core.dns.AddressResolverOptions.*;

@Getter
@Setter
@ConfigurationProperties("vertx.options.address-resolver-options")
public class AddressResolverOptionProps {

    private String hostsPath;
    private Buffer hostsValue;
    private List<String> servers;
    private boolean optResourceEnabled;
    private int cacheMinTimeToLive;
    private int cacheMaxTimeToLive;
    private int cacheNegativeTimeToLive;
    private long queryTimeout;
    private int maxQueries;
    private boolean rdFlag;
    private List<String> searchDomains;
    private int ndots;
    private boolean rotateServers;

    AddressResolverOptionProps() {
        servers = DEFAULT_SERVERS;
        optResourceEnabled = DEFAULT_OPT_RESOURCE_ENABLED;
        cacheMinTimeToLive = DEFAULT_CACHE_MIN_TIME_TO_LIVE;
        cacheMaxTimeToLive = DEFAULT_CACHE_MAX_TIME_TO_LIVE;
        cacheNegativeTimeToLive = DEFAULT_CACHE_NEGATIVE_TIME_TO_LIVE;
        queryTimeout = DEFAULT_QUERY_TIMEOUT;
        maxQueries = DEFAULT_MAX_QUERIES;
        rdFlag = DEFAULT_RD_FLAG;
        searchDomains = DEFAULT_SEACH_DOMAINS;
        ndots = DEFAULT_NDOTS;
        rotateServers = DEFAULT_ROTATE_SERVERS;
    }

    AddressResolverOptions toOptions() {
        AddressResolverOptions options = new AddressResolverOptions();
        BeanUtils.copyProperties(this, options);
        return options;
    }
}
