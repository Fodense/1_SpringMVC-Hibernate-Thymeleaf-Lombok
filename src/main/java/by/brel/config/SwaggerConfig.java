package by.brel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@PropertySource("classpath:/swagger/swagger.properties")
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .apiInfo(getApiInfo());
    }

    @Bean
    public UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder
                .builder()
                .operationsSorter(OperationsSorter.METHOD)
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Subscription API",
                "Api Documentation",
                "1.0",
                "urn:tos",
                new Contact("Uladzislau Brel", "", "vlad.brel.97@mail.ru"), "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList()
        );
    }
}
