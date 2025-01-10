package com.concert.infrastructrue.lock;

import com.concert.domain.core.lock.annotation.DistributedLockOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class LockKeyGenerator {

    private final ExpressionParser parser = new SpelExpressionParser();

    public String generate(ProceedingJoinPoint joinPoint, DistributedLockOperation lockOperation) {
        String key = lockOperation.key();
        String prefix = lockOperation.prefix();

        if (prefix.isEmpty()) {
            return key;
        }

        StandardEvaluationContext context = createEvaluationContext(joinPoint);
        String prefixValue = parser.parseExpression(prefix).getValue(context, String.class);
        return "%s:%s".formatted(key, prefixValue);
    }

    private StandardEvaluationContext createEvaluationContext(ProceedingJoinPoint joinPoint) {
        StandardEvaluationContext context = new StandardEvaluationContext();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        return context;
    }
}
