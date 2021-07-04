package au.gov.nsw.revenue.transferregistrationservice.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("qa")
@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI openAPIConfig(@Value("${api.version}") String apiVersion
            , @Value("${api.name}") String apiName
            , @Value("${api.description}") String apiDescription) {
        return new OpenAPI()
                .info(new Info()
                        .version(apiVersion)
                        .description(apiDescription)
                        .title(apiName));
    }

}