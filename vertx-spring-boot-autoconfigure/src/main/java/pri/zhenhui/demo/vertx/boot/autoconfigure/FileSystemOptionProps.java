package pri.zhenhui.demo.vertx.boot.autoconfigure;

import io.vertx.core.file.FileSystemOptions;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static io.vertx.core.file.FileSystemOptions.*;

@Data
@ConfigurationProperties("vertx.options.file-system-options")
public class FileSystemOptionProps {

    private boolean classPathResolvingEnabled = DEFAULT_CLASS_PATH_RESOLVING_ENABLED;

    private boolean fileCachingEnabled = DEFAULT_FILE_CACHING_ENABLED;

    private String fileCacheDir = DEFAULT_FILE_CACHING_DIR;

    FileSystemOptions toOptions() {
        FileSystemOptions options = new FileSystemOptions();
        BeanUtils.copyProperties(this, options);
        return options;
    }
}

