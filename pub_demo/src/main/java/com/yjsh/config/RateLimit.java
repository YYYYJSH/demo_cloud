package com.yjsh.config;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {
    /** 周期,单位是秒 */
    int cycle() default 5;

    /** 请求次数 */
    int number() default 1;
}
