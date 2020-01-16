package com.example.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sunshine big boy
 *
 * <pre>
 *     talk is cheap, show me the code
 * </pre>
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface AvoidFastClick {
    long fastClickInterval() default 1000L;

    String fastClickTip() default "";
}
