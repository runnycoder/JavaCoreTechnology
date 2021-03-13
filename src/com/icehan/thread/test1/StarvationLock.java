package com.icehan.thread.test1;

import java.util.concurrent.*;

/**
 * 饥饿死锁演示
 */
public class StarvationLock {
    private static ExecutorService single = Executors.newSingleThreadExecutor();

    public static class AnotherCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("in AnotherCallable");
            return "another success";
        }
    }

    public static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("in MyCallable");
            Future<String> submit = single.submit(new AnotherCallable());
            return "success:"+submit.get();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        Future<String> submit = single.submit(myCallable);
        System.out.println(submit.get());
        System.out.println("over");
        single.shutdown();
    }
}
