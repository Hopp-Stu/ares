package com.ares.system.common.log;

import com.alibaba.fastjson.JSON;
import com.ares.core.model.SysLog;
import com.ares.core.model.SysUser;
import com.ares.core.service.SysLogService;
import com.ares.core.utils.*;
import com.ares.system.common.security.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @description: 用户日志记录 当前仅记录出错日志
 * @author: yy 2020/01/27
 **/
@Aspect
@Component
@EnableAsync
public class LogAspect {
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private static final ThreadLocal<Date> dateThreadLocal = new ThreadLocal<>();

    @Resource
    SysLogService sysLogService;

    @Pointcut("@annotation(com.ares.system.common.log.Log)")
    public void logPointCut() {
    }

    @Pointcut("bean(*Controller)")
    public void logPointCutController() {
    }

    @Pointcut("execution(* com.ares.*.service.*.*(..))")
    public void logPointCutService() {
    }

    @AfterReturning("logPointCutController()")
    public void doBefore(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    //出错时记录日志
    @AfterThrowing(value = "logPointCutController()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    @Async
    public void handleLog(JoinPoint joinPoint, Exception e) {
        logger.info("用户行为日志记录开始！");
        dateThreadLocal.remove();
        dateThreadLocal.set(new Date());
        logger.info("开始计时:{}", DateUtils.getTime());
        SysLog sysLog = new SysLog();
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Log annotation = method.getAnnotation(Log.class);
            if (null != annotation) {
                String value = annotation.value();
            }
            String url = ServletUtils.getRequest().getRequestURI();
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());

            SysUser currentUser = SecurityUtils.getUser();
            if (null != currentUser) {
                sysLog.setHostIp(ip);
                sysLog.setUrl(url);
                sysLog.setUserName(currentUser.getUserName());
                sysLog.setRequestMethod(ServletUtils.getRequest().getMethod());
                if (null != e) {
                    sysLog.setNotes(StringUtils.substring(e.getMessage(), 0, 2000));
                } else {
                    setRequestValue(joinPoint, sysLog);
                }
            }
            long beginTime = dateThreadLocal.get().getTime();
            long endTime = System.currentTimeMillis();
            logger.info("计时结束:{} 耗时:{}", DateUtils.format(endTime), (endTime - beginTime) / 1000 + "s");
        } catch (Exception ex) {
            sysLog.setNotes(StringUtils.substring(ex.getMessage(), 0, 2000));
        } finally {
            if (null != sysLog.getUrl()) {
                ThreadPoolUtils.executorService.execute(new SaveLogThread(sysLog, sysLogService));
            }
            logger.info("用户行为日志记录结束！");
        }
    }

    private void setRequestValue(JoinPoint joinPoint, SysLog sysLog) {
        String requestMethod = sysLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            sysLog.setOperParams(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            sysLog.setOperParams(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                if (!isFilterObject(paramsArray[i])) {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params += jsonObj.toString() + " ";
                }
            }
        }
        return params.trim();
    }

    public boolean isFilterObject(final Object o) {
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }
}
