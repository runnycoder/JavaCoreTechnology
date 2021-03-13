package com.icehan.generic;

public abstract class Block{
        public abstract void body() throws Exception;

        public Thread toThread(){
            return new Thread(){
                public void run(){
                    try {
                        body();
                    } catch (Exception e) {
                        Block.<RuntimeException>throwAs(e);
                    }
                }
            };
        }

        public static <T extends Throwable> void throwAs(Throwable e) throws T{
            throw (T) e;
        }
}
