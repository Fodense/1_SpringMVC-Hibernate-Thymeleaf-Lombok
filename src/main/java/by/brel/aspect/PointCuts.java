package by.brel.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointCuts {

    @Pointcut("execution(* by.brel.service.impl.*.save*(..))")
    public void pointCutLoggerSave() {}

    @Pointcut("execution(* by.brel.service.impl.*.delete*(..))")
    public void pointCutLoggerDelete() {}

    @Pointcut("execution(* by.brel.rest.api.v1.controller.*.getAll*(..))")
    public void pointCutLoggerRESTGetAllBalances() {}
}