package com.thinking.helloelasticjob;


import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("'${fuck_zk_reg_cent.fuck_zk_serverList}'.length() > 0") //zk机器列表不为空才启用配置
public class ZKRegCentConfig {

    @Bean(initMethod = "init")

    public ZookeeperRegistryCenter fuckGetZKRefCent(@Value("${fuck_zk_reg_cent.fuck_zk_serverList}") final String serverList,
                                                    @Value("${fuck_zk_reg_cent.fuck_zk_namespace}") final String namespace) {
        System.out.println("fuck get ZookeeperRegistryCenter on --> " + serverList + " , namespace --> " + namespace);
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }
}
