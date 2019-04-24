package com.thinking.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MyAsyncService {
    @Scheduled(fixedRate = 1000 * 5) //间隔5秒执行一次
    @Async //此方法将在线程池中异步执行，线程池在MyAsyncConfig中定义
    public void myJob() {
        System.out.println("執行定时任务");
    }

    @Async
    public void myAsyncTask() {
        while (true) {
            System.out.println("執行异步任务");
            try {
                Thread.sleep(1000 * 10);
            } catch (Exception e) {
                System.out.println("myAsyncTask error-->" + e.getMessage());
            }
        }
    }
}
