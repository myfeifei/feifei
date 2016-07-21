package com.qiyi.mvptest.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by kezhan_sx on 2016/7/18.
 */
public class ProcessTool {

    public static void process(String clazz) {
        Class targetClass = null;

        try {
            targetClass = Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert targetClass != null;
        for (Method m : targetClass.getMethods()) {
            if(m.isAnnotationPresent(MyTag.class)) {
                System.out.println("被MyTag注解修饰的方法名：" + m.getName());
            } else {
                System.out.println("没有被MyTag注解修饰的方法名：" + m.getName());
            }
        }
    }

    public static void process1(String clazz) {
        Class targetClass = null;

        try {
            targetClass = Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert targetClass != null;
        for (Method m : targetClass.getMethods()) {
            if(m.isAnnotationPresent(MyTag1.class)) {
                MyTag1 tag = m.getAnnotation(MyTag1.class);
                System.out.println("方法：" + m.getName() + "的MyTag1注解内容为：" + tag.name() + "，" + tag.age());
            }
        }
    }
}
