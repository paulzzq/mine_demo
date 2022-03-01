package com.zzq.reflect;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {


        String temp = "cv 79 agctu qh cigg,vig yknn tgkipu;cv 69,vjg ykv;cpf cv 59,vjg lwfiogpv.";
        String temp2 = " at 20 years of age,the will reigns; at 30,the wit;and at 40,the judgment.";
        int length = temp.length();
        int tempAscii = 0;
        int shift = 2;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            String str=temp.charAt(i)+"";
            if(str.equals(" ")){
                sb.append(" ");
            }else{
                tempAscii = Integer.valueOf(temp.charAt(i));//97
                tempAscii = ((tempAscii - shift) - 97) % 26 + 97;
                sb.append((char) Integer.parseInt(tempAscii + ""));
            }
        }
        System.out.println("打印:"+sb.toString());
    }
}
