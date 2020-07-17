
package xyz.meihua.kungfu.configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;
import java.util.TimeZone;

/**
 * @author meihua
 */
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
        return new HttpMessageConverters(jacksonConverter);
        //使用fastJSON序列化
        //FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DtErrorAttributes();
    }

    public DtMvcConfiguration(ServerProperties serverProperties, ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        this.serverProperties = serverProperties;
        this.errorViewResolvers = errorViewResolversProvider.getIfAvailable();
    }

    @Bean
    public BasicErrorController basicErrorController(ErrorAttributes errorAttributes) {
        return new DtErrorController(errorAttributes, this.serverProperties.getError(), this.errorViewResolvers);
    }
}
