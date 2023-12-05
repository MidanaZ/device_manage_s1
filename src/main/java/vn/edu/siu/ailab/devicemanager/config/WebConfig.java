package vn.edu.siu.ailab.devicemanager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

    @Value("${imageDevice.location}")
    private String imageDevice;
    @Value("${imageBill.location}")
    private String imageBill;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        imageDevice = "E:\\Trung\\imageDevice\\";
        registry.addResourceHandler("/images/imageDevice/**").addResourceLocations("file:" + imageDevice);
        registry.addResourceHandler("/images/imageBill/**").addResourceLocations("file:" + imageBill);

        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }
    }
}