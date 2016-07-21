package com.qiyi.mvptest.annotation;

/**
 * Created by kezhan_sx on 2016/7/18.
 */
public class Test {
    @MyTag
    public static void method1() {}

    @MyTag1
    public static void method2() {}

    @MyTag1(name = "feifei")
    public static void method3() {}

    @MyTag1(name = "huihui", age = 25)
    public static void method4() {}

    public static void main(String[] args) {
        ProcessTool.process1("com.qiyi.mvptest.annotation.Test");
    }
}
