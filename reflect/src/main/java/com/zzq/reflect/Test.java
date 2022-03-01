package com.zzq.reflect;


/**
 * @author zhuzaiqing
 * @describe
 * @time 2020/08/11 10:32
 */
public class Test {
    private int count = 1;
    private final Object lock = new Object();

    public void turning() {
        Thread even = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println("Thread2: " + count++);
                    lock.notifyAll();
                    try {
                        // 如果还没有结束，则让出当前的锁并休眠
                        if (count <= 100) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread odd = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println("Thread1: " + count++);
                    lock.notifyAll();
                    try {
                        // 如果还没有结束，则让出当前的锁并休眠
                        if (count <= 100) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // 偶数线程线先获取到锁
        odd.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        even.start();
    }
}
