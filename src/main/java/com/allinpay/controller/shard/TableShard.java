package com.allinpay.controller.shard;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableShard {

    // 要替换的表名
    String tableName();

    // 对应的分表策略类
    Class<? extends ITableShardStrategy> shardStrategy();

}