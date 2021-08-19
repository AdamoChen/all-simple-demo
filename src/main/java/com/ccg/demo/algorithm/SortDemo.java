package com.ccg.demo.algorithm;

import java.util.Arrays;

public class SortDemo {

    /**
     * quickSort((a, b)) = quickSort((a, c)) + quickSort((c, b))
     *
     * 非稳定排序， 因为存在 比如 6 6 4 通过第一轮交换， 6 6的位置就发生了变化。
     *
     * @param arr
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int point = arr[end];
        int j = start;
        int i = start;
        while (j <= end) {
            if (arr[j] > point) {
                j++;
            }else{
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j++;
            }
        }
        // 这里一定要 -2 要排除掉 point 元素， 此元素因为一定是在最分界点上不用再参与排序 否则会存在死循环，
        quickSort(arr, start, i - 2);
        quickSort(arr, i , end);
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2, 7,8,5,6,4,2,3,8,6,1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }
}
