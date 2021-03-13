package com.icehan.innerclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class LocalTalkingClock {
    private int interval;
    private boolean beep;

    public LocalTalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start(int interval,boolean beep){
        /**
         * 此处局部内部类使用了外部的局部参数
         * 注意！！！当actionPerformed方法被调用时 局部参数beep肯定已经是不存在的了
         * 所以局部内部类会持有引用外部参数的一个备份 将它存放在自己的实力域中
         */
//        beep = false; 内部类使用的外部变量也必须是final的不能进行修改 在声明的时候最好就使用final关键字
        int[] counter = new int[1];//如果想使用可变的参数进行计数只能用这种形式代替
        class TimePrinter implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone,the time is "+new Date());
                counter[0]++;
                System.out.println("current times is "+counter[0]);
                if(beep) Toolkit.getDefaultToolkit().beep();
            }
        }
        TimePrinter timePrinter = new TimePrinter();
        Timer timer = new Timer(interval, timePrinter);
        timer.start();
    }



    public static void main(String[] args) {
        LocalTalkingClock talkingClock = new LocalTalkingClock(1000, true);
        talkingClock.start(1000,true);
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
