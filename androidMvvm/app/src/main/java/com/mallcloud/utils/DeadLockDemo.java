package com.mallcloud.utils;

/**
 * @author xzk
 * @data 2019/11/1
 * @email xiezhengkun@beyondsoft.com
 * @remark 死锁demo   两个线程，方法都没有走完，（关键在于，锁了两个对象之后，其中一个线程还sleep了3秒，导致二线程锁了一个对象阻塞了）
 */
public class DeadLockDemo {

//    public static String str1 = "str1";
//    public static String str2 = "str2";
//
//    public static void deadLock() {
//        System.out.println("lock start");
//        Thread a = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    while (true) {
//                        synchronized (DeadLockDemo.str1) {
//                            System.out.println(Thread.currentThread().getName() + "锁住 str1");
//                            Thread.sleep(1000);
//                            synchronized (DeadLockDemo.str2) {
//                                System.out.println(Thread.currentThread().getName() + "锁住 str2");
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        Thread b = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (true) {
//                        synchronized (DeadLockDemo.str2) {
//                            System.out.println(Thread.currentThread().getName() + "锁住 str2");
//                            Thread.sleep(1000);
//                            synchronized (DeadLockDemo.str1) {
//                                System.out.println(Thread.currentThread().getName() + "锁住 str1");
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        a.start();
//        b.start();
//    }

        public static String resourceA = "A";

        public static String resourceB = "B";

        public static void main(String[] args) {
            deadLock();
        }

        public static void deadLock(){
            System.out.println("lock start");
            Thread threadA = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (resourceA){
                        System.out.println("lock resourceA");
                        try {


                            Thread.sleep(3000);
                            synchronized (resourceB){
                                System.out.println("lock resourceB");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("end lock resourceA");
                }
            },"threadA");

            Thread threadB = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (resourceB){
                        System.out.println("lock resourceB");
                        synchronized (resourceA){
                            System.out.println("lock resourceA");
                        }
                    }
                    System.out.println("end lock resourceB");
                }
            },"threadB");

            threadA.start();
            threadB.start();
        }
}
