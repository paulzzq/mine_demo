package com.zzq.paul_tools;

public class TestMain {

    public static void main(String[] args) {
        int[] data={1,2,3,4,5,6};
        int[] data2={1,2,3,4,5,6,2,2000,100};
        int number=2;
        int result=deleteData(data,number);
        System.out.println(result);
        System.out.println("--------------");
//        printData(result,data);
        System.out.println("--------------："+ chooseBigestData(data2));

    }

    /**
     * 找出一组数据中的最大的值
     * @param nums
     * @return
     */
    public static int chooseBigestData(int[] nums){
        int max=0;
        if(nums.length>0){
            max=nums[0];
        }
        if(nums.length==1){
            return max;
        }
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]>max){
                max=nums[i];
            }
        }
        return max;
    }

    /**
     * 移除一组数据中的某个指定的数据
     * @param nums
     * @param number
     * @return
     */
    public static int deleteData(int[] nums, int number) {
        if (nums.length == 0){
            return 0;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=number) {
                nums[j]=nums[i];
                j++;
            }
        }
        return j;
    }

    public static void printData(int length,int[] nums){
        if(length>nums.length){
            return;
        }
        for (int i = 0; i < length; i++) {
            System.out.println(nums[i]);
        }
    }
}
