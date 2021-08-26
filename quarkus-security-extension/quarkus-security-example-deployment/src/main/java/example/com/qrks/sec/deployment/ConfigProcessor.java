package example.com.qrks.sec.deployment;

import example.com.qrks.sec.runtime.auth.CustomAuthMechanism;
import example.com.qrks.sec.runtime.cache.WeatherForecastService;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

class ConfigProcessor {

    private static final String FEATURE = "security-extension-example";

    Config config;

    @BuildStep
    FeatureBuildItem feature () {
        return new FeatureBuildItem(FEATURE);
    }

    /**
     * Register the CDI beans that are needed by the security extension
     *
     * @param additionalBeans - producer for additional bean items
     */
    @BuildStep
    void registerAdditionalBeans(BuildProducer<AdditionalBeanBuildItem> additionalBeans,
                                 BuildProducer<ReflectiveClassBuildItem> reflectiveClasses) {


        if (config.enabled) {
            AdditionalBeanBuildItem.Builder unremovable = AdditionalBeanBuildItem.builder().setUnremovable();
            unremovable.addBeanClass(CustomAuthMechanism.class);
            unremovable.addBeanClass(WeatherForecastService.class);
            additionalBeans.produce(unremovable.build());
        }
        AdditionalBeanBuildItem.Builder removable = AdditionalBeanBuildItem.builder();
        additionalBeans.produce(removable.build());

    }


}
