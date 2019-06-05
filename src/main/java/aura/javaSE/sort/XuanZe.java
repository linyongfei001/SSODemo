package aura.javaSE.sort;

import utils.MyArraysUtils;

/**
 * 它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，
 * 存放在序列的起始位置，直到全部待排序的数据元素排完。
 */
public class XuanZe {
    public static void main(String[] args) {
        MyArraysUtils arraysUtils = new MyArraysUtils();
        int[] arr = arraysUtils.addIntArray(10);
        arraysUtils.showArr(arr);

//        int temp;
//        for (int i=0;i<arr.length-1;i++){//位置
//            for (int j=i+1;j<arr.length;j++){//后边的所有元素
//                if (arr[i]>arr[j]){
//                    temp =arr[i];
//                    arr[i]=arr[j];
//                    arr[j]=temp;
//                }
//            }
//        }
        XuanZeSort(arr);
        arraysUtils.showArr(arr);
    }

    public static int[] XuanZeSort(int[] arr){
        int temp;
        for (int i=0;i<arr.length-1;i++){//位置
            for (int j=i+1;j<arr.length;j++){//后边的所有元素
                if (arr[i]>arr[j]){
                    temp =arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        return arr;
    }

}
