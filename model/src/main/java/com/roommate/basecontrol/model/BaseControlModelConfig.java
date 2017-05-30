package com.roommate.basecontrol.model;

import com.roommate.basecontrol.service.BaseControlServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Artem Karnov @date 11.04.2017.
 *         artem.karnov@t-systems.com
 */
@Configuration
@Import({BaseControlServiceConfig.class})
@ComponentScan("com.roommate.basecontrol.model")
public class BaseControlModelConfig {
}
