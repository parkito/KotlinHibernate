package com.roommate.basecontrol.service;

import com.roommate.basecontrol.repository.BaseControlRepositoryConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Artem Karnov @date 07.04.17.
 *         artem.karnov@t-systems.com
 */
@Configuration
@EnableTransactionManagement
@Import({BaseControlRepositoryConfig.class})
@ComponentScan("com.roommate.basecontrol.service")
public class BaseControlServiceConfig {
}
