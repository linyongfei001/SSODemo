package aura.javaSE.sort;

import utils.MyArraysUtils;

/**
 * 冒泡排序
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */
public class MaoPao {
    public static void main(String[] args) {
        MyArraysUtils arrUtils = new MyArraysUtils();
//        String[] arr = arrUtils.addArray(10);
        int[] arr = arrUtils.addIntArray(10);
        arrUtils.showArr(arr);

//        int temp;
//        for (int i=0;i<arr.length-1;i++){//比较的趟数
//            for (int j=0;j<arr.length-i-1;j++){//内循环为每趟比较的次数
//                if (arr[j] >(arr[j+1])){
//                    temp = arr[j+1];
//                    arr[j+1]=arr[j];
//                    arr[j]=temp;
//                }
//            }
//        }

//        String temp;
//        for (int i=0;i<arr.length-1;i++){//比较的趟数
//            for (int j=0;j<arr.length-i-1;j++){//内循环为每趟比较的次数
//                boolean b = arr[j].equals(arr[j + 1]);
//                if (arr[j].compareTo(arr[j+1])>0){//字符串比较大小
//                    temp = arr[j+1];
//                    arr[j+1]=arr[j];
//                    arr[j]=temp;
//                }
//            }
//        }
    MaoPaoSort(arr);
    arrUtils.showArr(arr);
    }

    /**
     *
     * @param arr 需要排序的数组
     * @return 返回一个排完序的数组
     */
    public static int[] MaoPaoSort(int[] arr){
        int temp;
        for (int i=0;i<arr.length-1;i++){//比较的趟数
            for (int j=0;j<arr.length-i-1;j++){//内循环为每趟比较的次数
                if (arr[j] >(arr[j+1])){
                    temp = arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        return arr;
    }



}
