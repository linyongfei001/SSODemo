package aura.javaSE.search;

import org.junit.Test;
import utils.MyArraysUtils;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 二分查找
 */
public class EFSearch {
    public static void main(String[] args) {
        MyArraysUtils arraysUtils = new MyArraysUtils();
        int[] arr = arraysUtils.addIntArray(20);
        arraysUtils.showArr(arr);
        Scanner sc = new Scanner(System.in);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("输入要查找下标的数字");
        int num = sc.nextInt();
        System.out.println("要查询的数字为："+num);
        int index = Search(arr, num);
        System.out.println(index);
        sc.close();

    }


    /**
     * 二分查找
     * @param arr 需要查找的数组
     * @param num 需要查找的数字
     */
    public static int Search(int []arr,int num){
        int start = 0;
        int end = arr.length-1;
        int mid = (start+end)/2;
        while (num != arr[mid]){
            if (num>arr[mid]){
                start = mid + 1;
            }else if(num <arr[mid]){
                end = mid - 1;
            }
            if (start > end){
                mid = -1;
                break;
            }
            mid = (start+end)/2;
        }
        return mid;
    }

}
