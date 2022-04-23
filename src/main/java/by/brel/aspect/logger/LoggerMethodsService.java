package by.brel.aspect.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggerMethodsService {

    @Around("@annotation(by.brel.aspect.LogExecutionTime)")
    public Object startAndStopMethodsService(ProceedingJoinPoint joinPoint) {
        final StopWatch watch = new StopWatch();

        Object target = null;
        try {
            watch.start();
            target = joinPoint.proceed();
            watch.stop();

        } catch (Throwable e) {
            log.info(e.getMessage());
        }

        log.info(joinPoint.getSignature().getName() + " выполнен за " + watch.getTotalTimeMillis() + "мс");

        return target;
    }

    @Around("by.brel.aspect.PointCuts.pointCutLoggerSave()")
    public Object trySaveMethodsService(ProceedingJoinPoint joinPoint) {
        Object target = null;

        log.info("Попытка сохранения");

        try {
            target = joinPoint.proceed();

        } catch (Throwable e) {
            log.debug(e.getMessage());
        }

        log.info("Сохранено!");

        return target;
    }

    @Around("by.brel.aspect.PointCuts.pointCutLoggerDelete()")
    public Object tryDeleteMethodsService(ProceedingJoinPoint joinPoint) {
        Object target = null;

        log.info("Попытка удаления");

        try {
            target = joinPoint.proceed();

        } catch (Throwable e) {
            log.debug(e.getMessage());
        }

        log.info("Удалено!");

        return target;
    }

    @AfterReturning(value = "by.brel.aspect.PointCuts.pointCutLoggerFindById()", returning = "result")
    public void checkMethodFindById(Object result) {
        log.info(result.toString());
    }
}
