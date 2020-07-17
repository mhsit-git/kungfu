
package xyz.meihua.kungfu.configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import java.util.TimeZone;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@ConditionalOnProperty(
    name = {"dt.extension.mvc"},
    matchIfMissing = true
)
public class DtMvcConfiguration {
    private final ServerProperties serverProperties;
    private final List<ErrorViewResolver> errorViewResolvers;

    @Bean
    public HttpMessageConverters customConverters() {
        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.registerModule(new JavaTimeModule());
        return new HttpMessageConverters(new HttpMessageConverter[]{jacksonConverter});
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DtErrorAttributes();
    }

    public DtMvcConfiguration(ServerProperties serverProperties, ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        this.serverProperties = serverProperties;
        this.errorViewResolvers = (List)errorViewResolversProvider.getIfAvailable();
    }

    @Bean
    public BasicErrorController basicErrorController(ErrorAttributes errorAttributes) {
        return new DtErrorController(errorAttributes, this.serverProperties.getError(), this.errorViewResolvers);
    }
}
