import java.util.*;
import java.io.*;

public class KansupadaA21 {

    public static void main(String[] args) throws IOException{

        Scanner input = new Scanner(System.in);
        System.out.print("How many steps can you take at once?\t");
        int numSteps = input.nextInt();

        for (int i = 1; i <= 50; i++) {
            long options = calc(i, numSteps);
            System.out.println("If you can take "+numSteps+" steps at a time you can traverse a "+i+ " sized set of stairs in "+fixNum(options)+" different ways.");
        }
    }

    public static String fixNum(long num){
        ArrayList<String> ret = new ArrayList<String>();
        if(num < 1000){
            return num+"";
        }

        if(num % 1000 < 100){
            ret.add(0, "0"+num % 1000);
        }else if(num % 1000 < 10){
            ret.add(0, "00"+num % 1000);
        }else{
            ret.add(0, num % 1000+"");
        }
        num /= 1000;

        while(num > 1000){
            if(num % 1000 < 100){
                ret.add(0, "0"+num % 1000 + ",");
            }else if(num % 1000 < 10){
                ret.add(0, "00"+num % 1000 + ",");
            }else{
                ret.add(0, num % 1000 + ",");
            }
            num /= 1000;
        }
        ret.add(0,num+",");

        String retFinal = "";
        for(int i = 0; i < ret.size(); i++){
            retFinal += ret.get(i);
        }
        return retFinal;
    }

    public static long calc(int i, int nS) {
        long[] data = new long[i+1];
        data[0] = 1;

        for (int x = 1; x <= i; x++) {
            for (int j = 1; j <= nS && x - j > -1; j++) {
                data[x] += data[x - j];
            }
        }

        return data[i];
    }
}
