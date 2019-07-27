package com.stackroute.MuzixApplication.config;


import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//annotation for configuration class
@Configuration
//annotation to enable Swagger2
@EnableSwagger2
public class WebConfiguration {
    //Bean to enable H2 console
    @Bean
    ServletRegistrationBean h2ServletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    //Bean for docket api, used for swagger2 documnetation
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.stackroute.MuzixApplication.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo()).tags(new Tag("Track Controller", "Operations of Track Management"));
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("Track Management REST API")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
