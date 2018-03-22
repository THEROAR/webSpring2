package com.example;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GetRecordInfo implements MethodInterceptor{

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object obj = null;
        if (invocation.getMethod().getReturnType() != null
                && !Void.TYPE.equals(invocation.getMethod().getReturnType())) {
            String serviceName = invocation.getMethod().getDeclaringClass().getName() + "."
                    + invocation.getMethod().getName();
            System.out.print("拦截成功" + serviceName);
            obj = invocation.proceed();
            return obj;
        }
        return obj;
    }
}
