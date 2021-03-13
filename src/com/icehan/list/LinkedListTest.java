package com.icehan.list;

import org.junit.Test;

import java.util.*;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> a = new LinkedList<>();
        a.add("A");
        a.add("C");
        a.add("E");

        LinkedList<String> b = new LinkedList<>();
        b.add("B");
        b.add("D");
        b.add("F");
        b.add("G");

        System.out.println("origin list a="+a);
        System.out.println("origin list b="+b);

        ListIterator<String> aiterator = a.listIterator();
        Iterator<String> biterator = b.iterator();
        while (biterator.hasNext()){
            if(aiterator.hasNext())
                aiterator.next();
            aiterator.add(biterator.next());
        }
        System.out.println("b insert to a ="+a);

        biterator = b.iterator();
        while (biterator.hasNext()){
            biterator.next();
            if(biterator.hasNext()){
                biterator.next();
                biterator.remove();
            }
        }
//        biterator.next();
//        biterator.remove();
        System.out.println("b interval deleted="+b);

        a.removeAll(b);
        System.out.println("a remove b="+a);

        TreeSet<String> treesets = new TreeSet<>();
    }

    @Test
    public void test(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        List<String> strings1 = strings.subList(1, 3);


        System.out.println(strings);
        System.out.println(strings1);
        strings1.clear();
        System.out.println(strings.toString());

        List normalList = strings;
        normalList.add(new Date());//可以添加进去 之后取出来转换成String的时候就会出问题

        //使用受查列表
        List<String> checkedList = Collections.checkedList(strings, String.class);
        normalList = checkedList;
        normalList.add(new Date());//受查列表在添加的时候就会对类型进行校验 而不是等到后续处理的时候出现转换错误


    }



}
