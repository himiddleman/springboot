package com.allinpay.controller.shard;

// 分表的策略类
public interface ITableShardStrategy {

    String tableShard(String tableName);

}