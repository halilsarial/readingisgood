package tr.com.readingisgood.bookservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("tr.com.readingisgood"))
                .paths(PathSelectors.regex("/readingisgood/books"))
                .build().apiInfo(apiEndPointsInfo());

    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("ReadingIsGood API Swagger Documentation")
                .description("ReadingIsGood Back-End Spring Boot Application Swagger Documentation")
                .contact(new Contact("Halil SARIAL", "https://www.getir.com", "halilsarial@gmail.com"))
                .license("Getir")
                .licenseUrl("https://www.getir.com")
                .version("1.0.0")
                .build();
    }

}