package com.dsy.dsu.AllDatabases.SQLTE;

import android.util.Log;



import junit.framework.TestCase;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class GetSQLiteDatabaseTest extends TestCase {
    CompletionService<String>   taskCompletionService=null;
    public void testOnOpen() {
        // TODO: 16.04.2025
        System.out.println("Start Test1");
        ExecutorService   executorService=Executors.newCachedThreadPool();
         taskCompletionService = new ExecutorCompletionService<String>(executorService);
        ConcurrentSkipListSet<String> concurrentSkipListSet=new ConcurrentSkipListSet();
        concurrentSkipListSet.add("1");
        concurrentSkipListSet.add("11");
        concurrentSkipListSet.add("111");
        concurrentSkipListSet.add("11111");
        concurrentSkipListSet.add("111111");
        concurrentSkipListSet.add("1111111");
        concurrentSkipListSet.add("11111111");
        concurrentSkipListSet.add("111111111");
        concurrentSkipListSet.add("1111111111");
        concurrentSkipListSet.forEach(con->{

            taskCompletionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("   CURRENT     "+" con  "+ con.toUpperCase()+" " +" "+Thread.currentThread().getName() +
                            " vremy  " +new Date().toLocaleString());
                    return null;
                }
            });
           // executorService.shutdown();
        });



        Log.d(this.getClass().getName(),"\n" + " onOpen  class " +
                Thread.currentThread().getStackTrace()[2].getClassName()
                + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
        System.out.println("STOP Test2");
    taskCompletionService.poll();
    Log.d(this.getClass().getName(),"\n" + " onOpen  class " +
    Thread.currentThread().getStackTrace()[2].getClassName()
    + "\n" +
    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                 }
}