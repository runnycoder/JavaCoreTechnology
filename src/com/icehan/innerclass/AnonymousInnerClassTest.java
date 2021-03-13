package com.icehan.innerclass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 匿名内部类测试
 */
public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(this.getClass().getName());
            }
        };
        System.out.println(listener.getClass().getName());//获取匿名内部类类名
        System.out.println(listener.getClass().getEnclosingClass().getName());//获取匿名内部类外部类类名
        //静态方法记录日志可以用这种方法获取当前执行类的类名 this.getClass()没办法使用
        System.out.println(new AnonymousInnerClassTest(){}.getClass().getEnclosingClass().getName());

        Timer timer = new Timer(1000, listener);
        timer.start();
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
