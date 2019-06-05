package utils;

import java.util.Arrays;
import java.util.Random;

public class MyArraysUtils {

    /**
     * 生成一个String类型数组
     * @param length，数组的长度
     * @return，返回一个String类型的数组
     */
    public String[] addArray(int length){
        String [] arr = new String[length];
        Random r = new Random(100);
        for (int i=0;i< length;i++){
            arr[i] = r.nextInt(100)+"";
        }
        return arr;
    }

    public int [] addIntArray(int length){
        int [] arr = new int[length];
        Random r = new Random(100);
        for (int i=0;i< length;i++){
            arr[i] = r.nextInt(100);
        }
        return arr;
    }

    /**
     * 遍历一个数组
     * @param arr，需要遍历的数组
     */
    public void showArr(String [] arr){
        for(int i =0;i<arr.length;i++){
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
    }

    public void showArr(int [] arr){
        for(int i =0;i<arr.length;i++){
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
    }

}
