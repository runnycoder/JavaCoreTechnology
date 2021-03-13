package com.icehan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TraceHandler implements InvocationHandler{
        private  Object target;

        public TraceHandler(Object t) {
            target = t;
        }



        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.print("target="+target);//被代理对象 proxy是创建出来的代理对象
            System.out.print("."+method.getName()+"(");
            if(args!=null){
                for (int i = 0; i < args.length; i++) {
                    System.out.print(args[i]);
                    if(i<args.length-1){
                        System.out.print(",");
                    }
                }
            }
            System.out.println(")");
            return method.invoke(target, args);
        }
}
