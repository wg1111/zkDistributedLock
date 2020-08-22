package com.insane.zkdistributedlock.distributedlock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * author:insane
 * Date:2020/8/2215:58
 **/
public class ZkDistributedLock extends ZkAbstractTemplateLock {
    @Override
    public void waitZkLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            /*当临时节点被删除时做以下处理*/
            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };
        //添加监听器监听已经产生的zookeeper临时节点
        zkClient.subscribeDataChanges(path,iZkDataListener);
        //判断若是被监听的临时节点已经存在，则wait，不再继续执行，直到监听的临时节点不存在
        if (zkClient.exists(path)) {
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(path, iZkDataListener);
    }

    @Override
    public boolean tryZkLock() {

        try {
            zkClient.createEphemeral(path);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
