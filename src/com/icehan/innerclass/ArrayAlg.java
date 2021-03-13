package com.icehan.innerclass;

public class ArrayAlg {

    public static class Pair{
        private double first;
        private double second;

        public Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }

        public  double getFirst(){
            return first;
        }

        public  double getSecond(){
            return second;
        }
    }

    public static Pair minmax(double[] values){
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double value : values){
            if(value<min) min=value;
            if(value>max) max=value;
        }
        return new Pair(min, max);
    }

    public static void main(String[] args) {
       double[] values  = new double[]{0.0,1.0,2.0,3.8,3.85,3.82};
        ArrayAlg.Pair minmax = minmax(values);
        System.out.println("values min value is :"+minmax.getFirst());
        System.out.println("values max value is :"+minmax.getSecond());
    }

}
