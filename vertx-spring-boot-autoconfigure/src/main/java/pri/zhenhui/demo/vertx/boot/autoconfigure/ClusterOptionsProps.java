package pri.zhenhui.demo.vertx.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(
        prefix = "vertx.cluster"
)
public class ClusterOptionsProps {

    private ClusterType type = ClusterType.NONE;

}
