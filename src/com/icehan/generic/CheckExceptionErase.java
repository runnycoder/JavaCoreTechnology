package com.icehan.generic;


import java.io.File;
import java.util.Scanner;

/**
 * 可以利用范型擦除让编译器忽略受查异常
 */
public class CheckExceptionErase {
    public static void main(String[] args) {
        new Block(){
            @Override
            public void body() throws Exception {
              new Scanner(new File("aaa"),"UTF-8") ;
            }
        }.toThread().start();
    }
}
