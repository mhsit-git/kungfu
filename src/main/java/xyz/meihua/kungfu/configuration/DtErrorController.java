//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package xyz.meihua.kungfu.configuration;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;

public class DtErrorController extends BasicErrorController {
    public DtErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    public DtErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        ResponseEntity<Map<String, Object>> entity = super.error(request);
        Map<String, Object> result = entity.getBody();
        if (result.get("path").toString().startsWith("/feign")) {
            return entity;
        } else {
            boolean http200 = result.get("http200") != null && result.get("http200").equals("true");
            result.remove("path");
            result.remove("error");
            result.remove("errors");
            result.remove("exception");
//            result.remove("message");
            result.remove("exception_detail");
            result.remove("http200");
            return http200 ? ResponseEntity.ok(result) : entity;
        }
    }
}
