package com.insane.zkdistributedlock.util;

/**
 * author:insane
 * Date:2020/8/2215:02
 **/
public class OrderNumCreatesUtil {
    public static int num = 0;

    public String getOrderNum() {
        return "\t 生成订单号：" + (++num);
    }

}
