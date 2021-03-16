package com.thinking.helloscheduled.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MyServicie {

    int Scheduled1_num = 0;

    @Scheduled(cron = "0/3 * * * * ?")
    public void doScheduled1() {
        System.out.println(Thread.currentThread().getId() + "--scd1-->" + (Scheduled1_num++));
    }

    int Scheduled2_num = 10000;

    @Scheduled(cron = "${myjob.do_scheduled2}")
    public void doScheduled2() {
        System.out.println(Thread.currentThread().getId() + "--scd2-->" + (Scheduled2_num++));
    }
}