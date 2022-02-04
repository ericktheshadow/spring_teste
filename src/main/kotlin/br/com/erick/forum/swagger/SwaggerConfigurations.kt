package br.com.erick.forum.swagger

import br.com.erick.forum.model.Usuario
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.DocumentationType.SWAGGER_2
import springfox.documentation.spring.web.plugins.Docket
import java.util.*

@Configuration
class SwaggerConfigurations {

    @Bean
    fun forumApi():Docket{
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.erick.forum"))
            .paths(PathSelectors.ant("/**"))
            .build()
            .ignoredParameterTypes()
            .globalOperationParameters(
                Arrays.asList(
                    ParameterBuilder()
                        .name("Authorization")
                        .description("Header para Token JWT")
                        .modelRef(ModelRef("string"))
                        .parameterType("header")
                        .required(false)
                        .build()))
    }
}