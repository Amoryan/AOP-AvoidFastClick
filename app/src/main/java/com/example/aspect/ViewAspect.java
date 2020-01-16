package com.example.aspect;

import android.text.TextUtils;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author sunshine big boy
 *
 * <pre>
 *     talk is cheap, show me the code
 * </pre>
 */
@Aspect
public class ViewAspect {

    private static long lastClickMillis = 0L;

    @Pointcut("execution(@com.example.aspect.AvoidFastClick * *(..))")
    public void avoidFastClickPointcut() {

    }

    @Before(value = "avoidFastClickPointcut()")
    public void beforeAvoidFastClick(JoinPoint joinPoint) {
    }

    @Around("avoidFastClickPointcut()")
    public void avoidFastClickJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AvoidFastClick annotation = method.getAnnotation(AvoidFastClick.class);
        Class returnType = signature.getReturnType();
        Log.d("adai", "returnType = " + returnType.getSimpleName());
        Class declaringType = signature.getDeclaringType();
        Log.d("adai", "declaringType = " + declaringType.getSimpleName());
        String declaringTypeName = signature.getDeclaringTypeName();
        Log.d("adai", "declaringTypeName = " + declaringTypeName);
        Class[] exceptionTypes = signature.getExceptionTypes();
        Log.d("adai", "exception = ");
        for (Class exceptionType : exceptionTypes) {
            Log.d("adai", "\t" + exceptionType.getSimpleName());
        }
        int modifiers = signature.getModifiers();
        Log.d("adai", "modifiers = " + modifiers);
        String name = signature.getName();
        Log.d("adai", "name = " + name);
        String[] parameterNames = signature.getParameterNames();
        Log.d("adai", "parameterNames = ");
        for (String parameterName : parameterNames) {
            Log.d("adai", "\t" + parameterName);
        }
        Class[] parameterTypes = signature.getParameterTypes();
        Log.d("adai", "parameterTypes");
        for (Class parameterType : parameterTypes) {
            Log.d("adai", "\t" + parameterType.getSimpleName());
        }
        Log.d("adai", "" + annotation.fastClickInterval());
        if (System.currentTimeMillis() - lastClickMillis > annotation.fastClickInterval()) {
            lastClickMillis = System.currentTimeMillis();
            joinPoint.proceed();
        } else if (!TextUtils.isEmpty(annotation.fastClickTip())) {
            Log.d("adai", annotation.fastClickTip());
        }
    }

    @After(value = "avoidFastClickPointcut()")
    public void afterAvoidFastClick(JoinPoint joinPoint) {
    }

    @AfterReturning(value = "avoidFastClickPointcut()")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
    }

    @AfterThrowing(value = "avoidFastClickPointcut()")
    public void afterThrowing(JoinPoint point, Throwable ex) {
    }

}
