package com.thinking.hello_elasticjob_nospring;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

public class MyJob implements SimpleJob {

    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                break;
            case 1:
                // do something by sharding item 1
                break;
            case 2:
                // do something by sharding item 2
                break;
            // case n: ...
        }
        pringJobDetails(context);
    }

    private void pringJobDetails(ShardingContext context) {
        System.out.println(String.format("Thread ID: %s, 作业分片总数: %s, 当前分片项: %s.当前参数: %s 作业名称: %s.作业自定义参数: %s",
                Thread.currentThread().getId(),
                context.getShardingTotalCount(),
                context.getShardingItem(),
                context.getShardingParameter(),
                context.getJobName(),
                context.getJobParameter()));
    }
}
