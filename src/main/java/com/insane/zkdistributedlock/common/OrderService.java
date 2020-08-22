package com.insane.zkdistributedlock.common;

import com.insane.zkdistributedlock.distributedlock.ZkDistributedLock;
import com.insane.zkdistributedlock.distributedlock.ZkLock;
import com.insane.zkdistributedlock.util.OrderNumCreatesUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author:insane
 * Date:2020/8/2215:14
 **/
public class OrderService {

    OrderNumCreatesUtil orderNumCreatesUtil = new OrderNumCreatesUtil();

    private ZkLock zkLock = new ZkDistributedLock();

    /**
     * 多系统多线程间的分布式锁（zookeeper）
     * @return
     */
    public void getOrderNum() {
        zkLock.zkLock();
        try {
            System.out.println("获得订单编号------->: " + orderNumCreatesUtil.getOrderNum());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            zkLock.zkUnlock();
        }
    }

    /*//单系统多线程间的锁
    private Lock lock = new ReentrantLock();

    public String getOrderNum() {
        lock.lock();
        try {
            return orderNumCreatesUtil.getOrderNum();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }*/
}
