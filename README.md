# zkDistributedLock
zookeeper实现分布式锁的demo

代码中CountDownLatch的简介：
CountDownLatch可以使一个获多个线程等待其他线程各自执行完毕后再执行。

CountDownLatch 定义了一个计数器，和一个阻塞队列， 当计数器的值递减为0之前，阻塞队列里面的线程处于挂起状态，当计数器递减到0时会唤醒阻塞队列所有线程，这里的计数器是一个标志，可以表示一个任务一个线程，也可以表示一个倒计时器，CountDownLatch可以解决那些一个或者多个线程在执行之前必须依赖于某些必要的前提业务先执行的场景。



CountDownLatch中的方法：

CountDownLatch(int count); //构造方法，创建一个值为count 的计数器。

await();//阻塞当前线程，将当前线程加入阻塞队列。

await(long timeout, TimeUnit unit);//在timeout的时间之内阻塞当前线程,时间一过则当前线程可以执行，

countDown();//对计数器进行递减1操作，当计数器递减至0时，当前线程会去唤醒阻塞队列里的所有线程。


ps：zookeeper安装可前往http://120.79.211.221:39900/archives/zookeeper%E4%B8%8B%E8%BD%BD%E5%AE%89%E8%A3%85%E6%95%99%E7%A8%8B

安装完成后一定要关闭centos服务器的防火墙以及上阿里云开启2181端口。
