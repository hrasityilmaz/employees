package art.timestart.spring.employees.security;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title="MY API", version="v1"), security = @SecurityRequirement(name="basicAuth"))
@SecurityScheme(name="basicAuth", type = SecuritySchemeType.HTTP, scheme="basic")
public class SwaggerConfig {

}
