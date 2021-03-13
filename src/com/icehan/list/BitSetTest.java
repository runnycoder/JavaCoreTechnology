package com.icehan.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;

public class BitSetTest {
    @Test
    public void bitSetTest(){
        BitSet bitSet = new BitSet(129);
        bitSet.set(0);
        bitSet.set(1);
        bitSet.set(3, 50);

        System.out.println(bitSet);
        System.out.println(bitSet.get(0));
        System.out.println(bitSet.get(1));
        System.out.println(bitSet.size());
        System.out.println(bitSet.length());
        System.out.println(Arrays.toString(bitSet.toByteArray()));
    }

    @Test
    public void searchPrimeNum(){
        int n = 2000000;
        long start = System.currentTimeMillis();
        BitSet bitSet = new BitSet(n );
        System.out.println(bitSet.size());
        int count = 0;
        int i;
        for (i=2;i<=n;i++){
            bitSet.set(i);
        }
        i = 2;
        while (i*i<n){
            if(bitSet.get(i)){
                count++;
                int k = 2*i;
                while (k<n){
                    bitSet.clear(k);
                    k +=i;
                }
            }
            i++;
        }
        while (i<=n){
            if(bitSet.get(i)) count++;
            i++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("primeNum count is "+count);
        System.out.println((endTime-start)+"milliseconds");

    }
}
