package by.brel.aspect.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerMethodRESTController {

    @Around("by.brel.aspect.PointCuts.pointCutLoggerRESTGetAllBalances()")
    public Object tryGetAllBalances(ProceedingJoinPoint joinPoint) {
        Object target = null;

        log.info("Попытка достать всё - " + joinPoint.getSignature().getName());

        try {
            target = joinPoint.proceed();

        } catch (Throwable e) {
            log.debug(e.getMessage());
        }

        log.info("Успешно!");

        return target;
    }
}