package com.ssm.concurrent;

import com.ssm.entity.*;
import com.ssm.service.*;
import com.ssm.util.UUIDUtil;
import com.ssm.vo.YZJHViewPointVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * TaskExecutor
 */
@Component
public class TaskExecutorExample1 {
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private SchoolBusLinkService schoolBusLinkService;

    @Autowired
    private SchoolBusStationService schoolBusStationService;

    @Autowired
    private SchoolBusBusService schoolBusBusService;

    @Autowired
    private SchoolBusStudentService schoolBusStudentService;

    public void printMessages() {
        for (int i = 0; i < 25; i++) {
//            taskExecutor.execute(new MessagePrinterTask("Message"+i));
            System.out.println("获取正在执行的线程数量:" + taskExecutor.getActiveCount());
            System.out.println("线程池的线程数量" + taskExecutor.getPoolSize());
        }
    }

    public void insert() {
        YZJHViewPointVo yzjhViewPoint = new YZJHViewPointVo();
        yzjhViewPoint.setPid(UUIDUtil.getId());
        yzjhViewPoint.setContent("测试线程");
        yzjhViewPoint.setStates('1');
        yzjhViewPoint.setGxrId("1");
        yzjhViewPoint.setZxDate(new Date());
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            //       taskExecutor.execute(new MessagePrinterTask(yzjhViewPoint));
        }
        System.out.println(taskExecutor.getPoolSize());
        Long endTime = System.currentTimeMillis();
        System.out.println("多线程花费时间：" + (endTime - startTime) + "ms");

    }

    public void insertBusLink() {
        SchoolBusLink schoolBusLink = new SchoolBusLink();
        for (int i = 0; i < 100; i++) {
            schoolBusLink.setLinkName("线路" + i);
            taskExecutor.execute(new MessagePrinterTask<>(schoolBusLink, schoolBusLinkService));
        }

    }

    public void insertBusStation() {
        SchoolBusStation schoolBusStation = new SchoolBusStation();
        for (int i = 0; i < 100000; i++) {
            schoolBusStation.setStationName("站点" + i);
            schoolBusStation.setLinkId(new Random().nextInt(100) + 1);
            taskExecutor.execute(new MessagePrinterTask<>(schoolBusStation, schoolBusStationService));
        }
        System.out.println("线程池大小："+taskExecutor.getPoolSize());
        System.out.println("线程池："+taskExecutor.getCorePoolSize());

    }

    public void insertBusBus() {
        SchoolBusBus schoolBusBus = new SchoolBusBus();
        for (int i = 0; i < 100000; i++) {
            schoolBusBus.setBusName("车辆" + i);
            schoolBusBus.setStationId(new Random().nextInt(100000) + 1);
            schoolBusBus.setLinkId(new Random().nextInt(100) + 1);
            taskExecutor.execute(new MessagePrinterTask<>(schoolBusBus, schoolBusBusService));
        }

    }

    public void insertBusStudent() {
        SchoolBusStudent schoolBusStudent = new SchoolBusStudent();
        for (int i = 0; i < 100000; i++) {
            schoolBusStudent.setBusId(new Random().nextInt(100000) + 1);
            schoolBusStudent.setYearId(new Random().nextInt(5) + 1);
            schoolBusStudent.setStationId(new Random().nextInt(100000) + 1);
            schoolBusStudent.setLinkId(new Random().nextInt(100) + 1);
            //schoolBusStudent.setStuId();
            taskExecutor.execute(new MessagePrinterTask<>(schoolBusStudent, schoolBusStudentService));
        }

    }

    public void find(CountDownLatch countDownLatch,final int size) {
        for (int i = 0; i < size; i++) {
            taskExecutor.execute(new MessagePrinterTask2(schoolBusStudentService,countDownLatch));
        }
    }

    private class MessagePrinterTask<T> implements Runnable {
        private String message;
        //   private YZJHViewPointVo viewPoint;
        private CommomService<T> commomService;
        private T t;

        public MessagePrinterTask(T t, CommomService commomService) {
            this.t = t;
            this.commomService = commomService;
        }

        @Override
        public void run() {
            //    yzjhViewPointService.addViewPoint(viewPoint,"1");
            commomService.add(t);
        }
    }

    private class MessagePrinterTask2 implements Runnable {
        private String message;
        private SchoolBusStudentService schoolBusStudentService;
        private CountDownLatch countDownLatch;

        public MessagePrinterTask2(SchoolBusStudentService schoolBusStudentService) {
            this.schoolBusStudentService = schoolBusStudentService;
        }

        public MessagePrinterTask2(SchoolBusStudentService schoolBusStudentService, CountDownLatch countDownLatch) {
            this.schoolBusStudentService = schoolBusStudentService;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            List<SchoolBusStudent> result = schoolBusStudentService.find();
            System.out.println(result.size());
            //代表线程执行完毕
            countDownLatch.countDown();
        }
    }
}
