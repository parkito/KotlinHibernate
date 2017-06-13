package com.roommate.basecontrol.app.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Artem Karnov @date 13.06.2017.
 *         artem.karnov@t-systems.com
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.roommate.basecontrol")
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.roommate.basecontrol"))
//                .paths(PathSelectors.regex("/.*"))
//                .build()
//                .pathMapping("/")
//                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET,
//                        Lists.newArrayList(new ResponseMessageBuilder()
//                                .code(500)
//                                .message("500 message")
//                                .responseModel(new ModelRef("Error"))
//                                .build()))
//                .enableUrlTemplating(false);
    }

//    @Bean
//    public Docket restfulApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("restful-api")
//                .select()
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//
//        ApiInfo apiInfo = new ApiInfo(
//                "My Apps API Title",
//                "My Apps API Description",
//                "My Apps API Version",
//                "My Apps API terms of service",
//                "My Apps API Contact Email",
//                "My Apps API Licence Type",
//                "My Apps API License URL"
//        );
//        return apiInfo;
//    }
}
