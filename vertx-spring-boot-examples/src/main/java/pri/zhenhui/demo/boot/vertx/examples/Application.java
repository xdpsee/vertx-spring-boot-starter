package pri.zhenhui.demo.boot.vertx.examples;

import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    Vertx vertx;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("d(>_<)b");

    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}

