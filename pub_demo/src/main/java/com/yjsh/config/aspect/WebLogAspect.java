package com.yjsh.config.aspect;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;

/**
 * 打印请求和响应信息
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {


    @Pointcut("execution(public * com.yjsh.controller.*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        //收到请求,记录请求内容
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();
        log.info("请求信息：" + request.getMethod() + " : " + request.getServletPath()
                + "   --> [" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() +"]"
                +"    --> [请求参数]：" + Arrays.toString(joinPoint.getArgs()) );
    }

    @AfterReturning(returning = "res", pointcut = "webLog()")
    public void doAfterReturning(Object res) throws JsonProcessingException {
        //处理完请求，返回内容
        log.info("返回内容: " + new ObjectMapper().writeValueAsString(res));
    }


    private static final String TRACE_ID = "traceId";

    @Before(value = "execution(* com.yjsh..*.*(..))")
    public void aroundHandle() {
        if (StringUtils.isBlank(MDC.get(TRACE_ID))) {
            String traceId = UUID.randomUUID().toString().replaceAll("-","");
            MDC.put(TRACE_ID, traceId);
        }
    }
}
