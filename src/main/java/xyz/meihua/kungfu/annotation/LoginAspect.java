package xyz.meihua.kungfu.annotation;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.meihua.kungfu.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author meihua
 * @date 2020/05/12
 */
@Aspect
@Component
@Log4j2
public class LoginAspect {

    @Pointcut("@annotation(Login)")
    public void annotationPointcut() {
    }


    @Before("annotationPointcut()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getParameter("token");

        if (StringUtils.isEmpty(token)) {
            throw new BusinessException("未登录");
        }

        log.info("{}", token);
    }

    @AfterThrowing(value = "annotationPointcut()", throwing = "throwable")
    public void doRecoveryActions(@NotNull JoinPoint jointPoint, Throwable throwable) throws Throwable {

        log.info(JSON.toJSONString(jointPoint.getArgs()));
        log.error("程序出错了被拦截到了。。。", throwable);

    }
}
