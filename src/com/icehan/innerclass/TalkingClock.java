package com.icehan.innerclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start(){
        TimePrinter timePrinter = new TimePrinter();
        Timer timer = new Timer(interval, timePrinter);
        timer.start();
    }
    /**
     * 使用  javap -private  TalkingClock.TalkingClock\$TimePrinter
     * 反汇编生成的class文件 可以发现内部类 TimePrinter中多了一个外部类的引用
     */

    public class TimePrinter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("At the tone,the time is "+new Date());
            if(beep) Toolkit.getDefaultToolkit().beep();
        }
    }

    public static void main(String[] args) {
        TalkingClock talkingClock = new TalkingClock(1000, true);
        talkingClock.start();
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
