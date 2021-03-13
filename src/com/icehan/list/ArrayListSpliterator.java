package com.icehan.list;

import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Spliterator;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * 自定义实现一个ArrayList的spliterator
 * @param <E>
 */
public class ArrayListSpliterator<E> implements Spliterator<E> {

    private final ArrayList<E> list;//存放source ArrayList

    private int startIndex;//起始位置 tryAdvance和trySplit操作时会修改
    
    private int endIndex;//末端位置 -1表示到最后一个元素

    private int expectedCount;//存放list的modCount

    public ArrayListSpliterator(ArrayList<E> list, int startIndex, int endIndex, int expectedCount) {
        this.list = list;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.expectedCount = expectedCount;
    }

    private int getEndIndex(){
        int tempindex = endIndex;
        ArrayList<E> lst = list;
        if(tempindex<0){
            if(lst==null){
                tempindex = endIndex = 0;
            }else{
                tempindex = endIndex = list.size();
                expectedCount = lst.size();
            }
        }
        return tempindex;
    }



    @Override
    public boolean tryAdvance(Consumer<? super E> action) {
        if(action==null){
            throw new NullPointerException();
        }
        if(getSize()>0){
            action.accept(list.get(startIndex++));
            return true;
        }
        return false;
    }


    @Override
    public Spliterator<E> trySplit() {
        if(getSize()==1){
            return null;
        }
        int start = startIndex,end = endIndex,mid=(start+end)>>>1;
        this.startIndex=mid+1;
        return start>=mid ? null:new ArrayListSpliterator<E>(list,start,mid,mid-start-1);
    }

    public int getSize(){
        return endIndex-startIndex+1;
    }

    @Override
    public long estimateSize() {
        return getSize();
    }

    @Override
    public int characteristics() {
        return SUBSIZED|ORDERED|SIZED;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("7");

        ArrayListSpliterator<String> s1 = new ArrayListSpliterator<>(strings, 0, strings.size()-1, strings.size());
        Spliterator<String> s2 = s1.trySplit();

        System.out.println("characteristics "+s1.characteristics());
        System.out.println("s1 size "+s1.estimateSize());
        System.out.println("s2 size "+s2.estimateSize());
        System.out.println("s1========");
        while (s1.tryAdvance(System.out::println));
        System.out.println("s2========");
        while (s2.tryAdvance(System.out::println));

        TreeSet<Object> objects = new TreeSet<>();
        new PriorityQueue<>();

    }
}
