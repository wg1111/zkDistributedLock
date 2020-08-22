package com.insane.zkdistributedlock.distributedlock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * 分布式锁模板类
 * author:insane
 * Date:2020/8/2215:32
 **/
public abstract class ZkAbstractTemplateLock implements ZkLock {

    public static final String ZKSERVER = "120.77.156.200:2181";

    public static final int TIME_OUT = 60 * 1000;

    public static final String path = "/zkLock";

    public CountDownLatch countDownLatch = null;

    ZkClient zkClient = new ZkClient(ZKSERVER,TIME_OUT);

    @Override
    public void zkLock() {
        //tyyZkLock()判断当前是否没别占用，没有的话抢到就创建锁（即zookeeper上的临时节点）
        if (tryZkLock()) {
            System.out.println(Thread.currentThread().getName() + "\t 占用锁成功！");
        } else {
            // 若没有成功上锁即等待并监听zookeeper临时节点的状态，若临时节点被删除就再次执行zkLock()方法
            waitZkLock();
            zkLock();
        }
    }

    public abstract void waitZkLock();

    public abstract boolean tryZkLock() ;

    @Override
    public void zkUnlock() {
        if (zkClient != null) {
            //删除临时节点
            zkClient.close();
        }
        System.out.println(Thread.currentThread().getName() + "\t 释放锁成功！");
        System.out.println();
        System.out.println();
    }
}
