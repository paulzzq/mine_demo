package com.zzq.paul_tools.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2020/6/30 11:14
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {
    String name() default "my name is paul";
}
