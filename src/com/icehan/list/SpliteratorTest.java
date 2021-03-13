package com.icehan.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Spliterator;

/**
 * spliterator是java8之后提供的迭代器
 * 传统的iterator可以实现顺序或者逆序的迭代操作
 * spliterator可以对集合分片迭代 适用于多线程处理
 */
public class SpliteratorTest {

    @Test
    public void test(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("7");

        Spliterator<String> s1 = strings.spliterator();

        System.out.println(s1);

        System.out.println(s1.estimateSize());

        System.out.println("s1:=======");

        while (s1.tryAdvance(System.out::println));

        int characteristics = s1.characteristics();
        System.out.println("characteristics="+characteristics);

    }

    @Test
    public void test1(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("7");

        Spliterator<String> s1 = strings.spliterator();
        /**
         * public static final int ORDERED    = 0x00000010;
         *
         *
         *     public static final int DISTINCT   = 0x00000001;
         *
         *
         *     public static final int SORTED     = 0x00000004;
         *
         *
         *     public static final int SIZED      = 0x00000040;
         *
         *
         *     public static final int NONNULL    = 0x00000100;
         *
         *
         *     public static final int IMMUTABLE  = 0x00000400;
         *
         *
         *     public static final int CONCURRENT = 0x00001000;
         *
         *
         *     public static final int SUBSIZED = 0x00004000;
         */
        System.out.println("characteristics="+s1.characteristics());
        Spliterator<String> s2 = s1.trySplit();
        System.out.println("characteristics="+s1.characteristics());
        Spliterator<String> s3 = s1.trySplit();
        System.out.println("characteristics="+s1.characteristics());
        //打印出来十六进制是4050 我猜想这个值应该是 SUBSIZED(0x00004000) SIZED(0x00000040) ORDERED(0x00000010)三个常量值相加的结果
        System.out.println("0x"+Integer.toHexString(s1.characteristics()));

//        strings.add("8");
//        System.out.println("characteristics="+s1.characteristics());

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println(s1.estimateSize());
        System.out.println(s2.estimateSize());
        System.out.println(s3.estimateSize());

        System.out.println("s1:=======");
        while (s1.tryAdvance(System.out::println));
        System.out.println("s2:=======");
        while (s2.tryAdvance(System.out::println));
        System.out.println("s3:=======");
        while (s3.tryAdvance(System.out::println));
    }
}
