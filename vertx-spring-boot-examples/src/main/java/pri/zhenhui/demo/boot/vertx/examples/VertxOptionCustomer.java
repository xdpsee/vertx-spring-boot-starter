package pri.zhenhui.demo.boot.vertx.examples;

import io.vertx.core.VertxOptions;
import org.springframework.stereotype.Component;
import pri.zhenhui.demo.vertx.boot.autoconfigure.VertxCustomizer;

@Component
public class VertxOptionCustomer implements VertxCustomizer<VertxOptions> {

    @Override
    public void customize(VertxOptions options) {

    }
}

