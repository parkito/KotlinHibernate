package com.roommate.basecontrol.controllers;

import com.roommate.basecontrol.model.BaseControlModelConfig;
import com.roommate.basecontrol.service.BaseControlServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @author Artem Karnov @date 07.04.17.
 *         artem.karnov@t-systems.com
 */
@Configuration
@ComponentScan(basePackages = {"com.roommate.basecontrol.controllers.restControllers"})
@Import({BaseControlServiceConfig.class,
        BaseControlModelConfig.class})
public class BaseControlRestConfig {
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
