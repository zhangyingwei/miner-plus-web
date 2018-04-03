package com.zhangyingwei.miner.common;

import java.lang.annotation.*;

/**
 * Created by zhangyw on 2018/4/3.
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Auth {
}
