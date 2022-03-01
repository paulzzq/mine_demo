package com.zzq.reflect;
/**
 * @author zhuzaiqing
 * @describe
 * @time 2020/08/11 10:01
 */
public class Main1 {
    public static void main(String[] args) {
        String inputStr = "Cv 79 agctu qh cig, vjg yknn tgkipu; cv 69, vjg ykv; cpf cv 59, vjg lwfiogpv.";
        int length = inputStr.length();
        int tempAscii = 0;
        int moveCount = 2;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            String indexStr=inputStr.charAt(i)+"";
            if (Character.isDigit(inputStr.charAt(i))){
                //是数字
                tempAscii=Integer.valueOf(indexStr);
                tempAscii=9-tempAscii;
                sb.append(tempAscii+ "");
            }else{
                //不是数字
                if(indexStr.equals(" ")){
                    sb.append(" ");
                }else{
                    if(Character.isLowerCase(inputStr.charAt(i))){
                        if(inputStr.charAt(i)=='a'){
                            sb.append( "y");
                        }else if(inputStr.charAt(i)=='b'){
                            sb.append("z");
                        }else{
                            tempAscii = Integer.valueOf(inputStr.charAt(i));//97
                            tempAscii = ((tempAscii - moveCount) - 97) % 26 + 97;
                            sb.append((char) Integer.parseInt(tempAscii + ""));
                        }
                    }else if(Character.isUpperCase(inputStr.charAt(i))){
                        if(inputStr.charAt(i)=='A'){
                            sb.append( "Y");
                        }else if(inputStr.charAt(i)=='B'){
                            sb.append("Z");
                        }else{
                            tempAscii = Integer.valueOf(inputStr.charAt(i));//97
                            tempAscii = ((tempAscii - moveCount) - 65) % 26 + 65;
                            sb.append((char) Integer.parseInt(tempAscii + ""));
                        }
                    }else{
                        sb.append(indexStr);
                    }
                }
            }
        }
        System.out.println("reslut:"+sb.toString());
        //reslut：At 20 years of age, the will reigns; at 30, the wit; and at 40, the judgment.

    }
}
