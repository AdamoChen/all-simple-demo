package com.ccg.demo.algorithm;

public class BinSearchDemo {

    /**
     * 查找第一个与给定值相等的索引  极简代码版
     * @param arr
     * @param value
     * @return
     */
    public static int firstEqValue(int[] arr, int value){

        int end = arr.length -1;
        int start = 0;
        int mid = 0;
        while (start <= end){
            mid = start + ((end - start) >> 1);
            if (arr[mid] >= value) {
                //这里不用担心 end 会错过第一个相等的值
                end = mid - 1;
            }else{
                // 最终这里会把那个相等的值找到
                start = mid + 1;
            }
        }

        if(start < arr.length && (arr[start] == value)) return start;
        return -1;
    }


    /**
     * 查找第一个与给定值相等的索引 常规理解版
     * @param arr
     * @param value
     * @return
     */
    public static int firstEqValue2(int[] arr, int value){

        int end = arr.length -1;
        int start = 0;
        int mid = 0;
        while (start <= end){
            mid = start + ((end - start) >> 1);
            if (arr[mid] > value) {
                end = mid - 1;
            }else if(arr[mid] < value){
                start = mid + 1;
            }else{
                if(arr[0] == value || arr[mid - 1] != value){
                    return mid;
                }else{
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 最后一个与给定值相等的索引
     * @param arr
     * @param value
     * @return
     */
    public static int lastEqValue(int[] arr, int value){

        int end = arr.length -1;
        int start = 0;
        int mid = 0;
        while (start <= end){
            mid = start + ((end - start) >> 1);
            if (arr[mid] > value) {
                end = mid - 1;
            }else if(arr[mid] < value){
                start = mid + 1;
            }else{
                if(mid == (arr.length - 1) || arr[mid + 1] != value){
                    return mid;
                }else{
                    start = mid + 1;
                }
            }
        }

        return -1;
    }



    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,6,7,7,8,9,10};
        System.out.println(firstEqValue(arr, 7));
        System.out.println(firstEqValue2(arr, 6));
        System.out.println(lastEqValue(arr, 6));

    }



}
