package pri.zhenhui.demo.vertx.boot.autoconfigure;

import io.vertx.core.VertxOptions;

@FunctionalInterface
public interface VertxCustomizer<T extends VertxOptions> {

    void customize(T options);

}


