package example.com.qrks.sec.deployment;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "security-extension-example")
public class Config {

    /**
     * Enables security
     */
    @ConfigItem(defaultValue = "true")
    public boolean enabled = true;
}
