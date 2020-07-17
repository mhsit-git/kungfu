//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package xyz.meihua.kungfu.configuration;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import xyz.meihua.kungfu.exception.BusinessException;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author meihua
 */
public class DtErrorAttributes extends DefaultErrorAttributes {
    private static final Logger log = LoggerFactory.getLogger(DtErrorAttributes.class);
    private final int MAX_STACK_TRACE_ELEMENT = 10;

    public DtErrorAttributes() {
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> result = super.getErrorAttributes(webRequest, options);
        Throwable throwable = this.getError(webRequest);
        if (throwable == null) {
//            result.put("status", result.get("status"));
            log.error(result.toString(), throwable);
            return result;
        } else {
            if (throwable instanceof BusinessException) {
                BusinessException be = (BusinessException) throwable;
                result.put("status", be.getCode());
                result.put("message", be.getMessage());
                result.put("http200", "true");
                log.warn(result.toString(), throwable);
            } else if (throwable instanceof MethodArgumentNotValidException) {
                FieldError error = (FieldError) ((List) result.get("errors")).get(0);
                result.put("status", 400);
                result.put("message", error.getDefaultMessage());

                result.put("http200", "true");
                log.warn(result.toString(), throwable);
            } else {
                result.put("message", "服务端内部异常");
                log.error(result.toString(), throwable);
            }

            result.put("exception", throwable.getClass());

            try {
                List<StackTraceElement> steList = new LinkedList();
                StackTraceElement[] var6 = throwable.getStackTrace();
                int var7 = var6.length;

                for (int var8 = 0; var8 < var7; ++var8) {
                    StackTraceElement stackTraceElement = var6[var8];
                    steList.add(stackTraceElement);
                    if (steList.size() >= 10) {
                        break;
                    }
                }

                throwable.setStackTrace((StackTraceElement[]) steList.toArray(new StackTraceElement[steList.size()]));
                result.put("exception_detail", JSONObject.toJSONString(throwable));
            } catch (Exception var10) {
                var10.setStackTrace(new StackTraceElement[0]);
                result.put("exception_detail", var10);
            }

            return result;
        }
    }
}
