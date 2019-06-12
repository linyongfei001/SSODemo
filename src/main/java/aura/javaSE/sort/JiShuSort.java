package aura.javaSE.sort;

import utils.MyArraysUtils;

/**
 * 记数排序
 */
public class JiShuSort {
    public static void jsSort(int[] arr){
        int max=arr[0];
        for(int a:arr){
            if(max<a){
                max=a;
            }
        }
        //定义计数排序的数组  数组长度就是max+1
        int[] newarr=new int[max+1];
        //循环遍历原数组   想计数排序数组中添加   下标代表元素值   存储的值代表次数
        for(int a:arr){
            newarr[a]=newarr[a]+1;
        }
        //对排好序的数组进行循环遍历
        //先循环最外层数组
        for(int i=0;i<newarr.length;i++){
            //计数排序数组中的元素值》0的时候
            //循环遍历数组中 的元素值  输出下边
            for(int j=1;j<=newarr[i];j++){
                System.out.print(i+"\t");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyArraysUtils arraysUtils = new MyArraysUtils();
        int[] array = arraysUtils.addIntArray(10);
        arraysUtils.showArr(array);
        jsSort(array);
    }

}
