package com.zzq.reflect;

import java.util.ArrayList;


/**
 * @author zhuzaiqing
 * @describe
 * @time 2020/08/11 11:03
 */
public class Main3 {
    public static void main(String[] args) {
        findChar("acbcbccdd");
    }
    public static void findChar(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        // ArrayList保存字符串中出现的字符
        ArrayList<Character> list = new ArrayList<Character>(str.length());
        for (int i = 0; i < str.length(); i++) {
            // 只要列表中不包含，将它添加到表中
            if (!list.contains(str.charAt(i))) {
                list.add(str.charAt(i));
            }
        }
        int[] count = new int[list.size()];
        // 这里外循环就不用遍历整个字符串，仅仅按照List表中所保存的字符进行遍历
        for (int i = 0; i < list.size(); i++) {
            char mid = (Character) list.get(i);
            for (int j = 0; j < str.length(); j++) {
                if (mid == str.charAt(j)) {
                    count[i]++;
                }
            }
        }
        int index = 0;
        int max = count[0];
        for (int i = 0; i < count.length; i++) {
            if (max < count[i]) {
                max = count[i];
                index = i;
            }
        }
        for (int k = 0; k < list.size(); k++) {
            System.out.print(list.get(k) + " ");
        }
        System.out.println("char :" + list.get(index));
        System.out.println("count :" + count[index]);
    }
}
