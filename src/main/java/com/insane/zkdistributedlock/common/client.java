package com.insane.zkdistributedlock.common;

/**
 * author:insane
 * Date:2020/8/2215:18
 **/
public class client {
    public static void main(String[] args) {
        // OrderService orderService = new OrderService();
        for (int i = 0; i <30 ; i++) {
            new Thread(() -> {
                // String str = orderService.getOrderNum();
                new OrderService().getOrderNum();//每个线程创建一个jvm来模拟多系统多线程
            }).start();
        }
    }
}
