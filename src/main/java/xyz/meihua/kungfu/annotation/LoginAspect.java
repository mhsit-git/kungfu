package xyz.meihua.kungfu.annotation;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getParameter("token");
        log.info("{}", token);
    }
}
