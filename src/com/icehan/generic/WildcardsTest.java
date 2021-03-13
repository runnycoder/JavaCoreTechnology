package com.icehan.generic;

import org.junit.Test;

public class WildcardsTest {
    @Test
    public void test1(){
        Manager ceo = new Manager();
        Manager cfo = new Manager();
        Pair<Manager> manageBuddies = new Pair<Manager>(ceo,cfo);

        Pair<? extends Employee> wildcardBuddies = manageBuddies;
        Employee employee = new Employee();
//        wildcardBuddies.setFirst(employee); //不能设置 编译器只直到需要Employee的子类型但是不知道具体类型
        Employee first = wildcardBuddies.getFirst();//可以使用get
    }
    @Test
    public void test2(){
        Manager ceo = new Manager();
        Manager cfo = new Manager();
        Pair<Employee> manageBuddies = new Pair<Employee>(ceo,cfo);

        Pair<? super Employee> wildcardBuddies = manageBuddies;
        wildcardBuddies.setFirst(ceo); //可以设置 Employee及其子类型因为编译器只知道 参数是Employee或其父类型 所以可以设置其子类对象
//        Employee first = wildcardBuddies.getFirst();//但是不能使用get 编译器只知道返回的是Employee或其父类 不知道是那个固定的类型
    }
}
