package com.insane.zkdistributedlock.distributedlock;

/**
 * author:insane
 * Date:2020/8/2215:31
 **/
public interface ZkLock {

    public void zkLock();

    public void zkUnlock();
}
