package pri.zhenhui.demo.vertx.boot.autoconfigure;

import io.vertx.core.VertxOptions;
import org.springframework.boot.util.LambdaSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class VertxCustomizers {

    private final List<VertxCustomizer<?>> customizers;

    public VertxCustomizers(
            List<? extends VertxCustomizer<?>> customizers) {
        this.customizers = (customizers != null) ? new ArrayList<>(customizers)
                : Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    public <T extends VertxOptions> T customize(T options) {
        LambdaSafe.callbacks(VertxCustomizer.class, this.customizers, options)
                .withLogger(VertxCustomizers.class)
                .invoke((customizer) -> customizer.customize(options));
        return options;
    }

}
