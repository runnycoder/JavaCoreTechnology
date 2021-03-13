package com.icehan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class ProxyTest {


    public static void main(String[] args) {
        Object[] elements = new Object[100];
        //创建Integer的代理对象
        for (int i = 0; i <elements.length; i++) {
            Integer value = i+1;
            InvocationHandler traceHandler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, traceHandler);
            elements[i]=proxy;
        }

        int key = new Random().nextInt(elements.length + 1);
        int result = Arrays.binarySearch(elements, key);//如果找到返回的是元素的索引
        if(result>0){
            System.out.println(elements[result]);
        }
    }
}
