package aura.javaSE.sort;

import utils.MyArraysUtils;

/**
 * 插入排序的基本思想是：每步将一个待排序的记录，
 * 按其关键码值的大小插入前面已经排序的文件中适当位置上，
 * 直到全部插入完为止。
 */
public class ChaRu {
    public static void main(String[] args) {
        MyArraysUtils arraysUtils = new MyArraysUtils();
        int[] arr = arraysUtils.addIntArray(10);
        arraysUtils.showArr(arr);

//        int temp;
//        for (int i=1;i<arr.length;i++){//位置
//            for (int j=0;j<i;j++){//前边所有元素
//                if (arr[j]>arr[i]){
//                    temp = arr[i];
//                    for (int k=i;k>j;k--){//覆盖
//                        arr[k]=arr[k-1];
//                    }
//                    arr[j] =temp;
//                }
//            }
//        }
        ChaRuSort(arr);
        arraysUtils.showArr(arr);
    }

    /**
     *
     * @param arr 需要排序的数组
     * @return 返回一个排序后的数组
     */
    public static int [] ChaRuSort(int[] arr){
        int temp;
        for (int i=1;i<arr.length;i++){//位置
            for (int j=0;j<i;j++){//前边所有元素
                if (arr[j]>arr[i]){
                    temp = arr[i];
                    for (int k=i;k>j;k--){//覆盖
                        arr[k]=arr[k-1];
                    }
                    arr[j] =temp;
                }
            }
        }
        return arr;
    }

}
