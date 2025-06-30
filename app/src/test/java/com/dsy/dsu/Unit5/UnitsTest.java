package com.dsy.dsu.Unit5;

import static org.junit.Assert.*;

import junit.framework.TestCase;
import junit.framework.TestResult;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class UnitsTest extends TestCase {

    @Override
    public TestResult run() {
        System.out.println("run !!! " +new Date().toLocaleString());
        return super.run();
    }

    @Override
    protected void runTest() throws Throwable {
        super.runTest();
        System.out.println("runTest !!! " +new Date().toLocaleString());
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp !!! " +new Date().toLocaleString());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown !!! " +new Date().toLocaleString());
    }


    @Test
    public void test(){
        Assert.fail();

        System.out.println("test !!! " +new Date().toLocaleString());
    }

}