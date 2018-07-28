package in.habel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //    .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("in.habel.resources"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API Documentation Test",
                "Swagger UI Implementation. Authentication/Authorization based docs/properties not added",
                "0.0.1",
                "Terms of service",
                new Contact(null, "https://github.com/habelMarottipuzha/Spring-Boot-test---E-commerce", null),
                null,
                null,
                Collections.emptyList());
    }
}