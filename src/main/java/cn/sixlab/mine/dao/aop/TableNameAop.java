package cn.sixlab.mine.dao.aop;

import cn.sixlab.mine.dao.Params;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TableNameAop {

    @Autowired
    private Params params;

    @Pointcut("@annotation(TableName)")
    public void tableName() {
    }

    @Around("tableName()")
    public Object demoAop(ProceedingJoinPoint point) throws Throwable {
        log.info("aop start");

        Object[] args = point.getArgs();

        Object arg0 = args[0];

        if (arg0 != null) {
            if (params.getAlias().containsKey(arg0)) {
                args[0] = params.getAlias().get(arg0);
            }
        }

        Object value = point.proceed(args);

        log.info("aop finish");

        return value;
    }

}
