package com.liveweather.test;

public class TestInstanceManager {
    TestClass1 test = new TestClass1();
    public void simulateTestClass() {
        test = new TestClass2();
    }
    public void get() {

    }
}
