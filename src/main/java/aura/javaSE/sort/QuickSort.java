package aura.javaSE.sort;

import utils.MyArraysUtils;

public class QuickSort {
	/**
	 *
	 * @param srcAarr
	 * @param left  最左侧的下标  小数组
	 * @param right   最右侧的下标   小数组
	 * @return
	 */

	//基准点定位 10 2 5 30 25
	public static int getIndex(int[] srcAarr,int left,int right){
		//给一个初始的基准点 10
		int key=srcAarr[left];
		//循环遍历比较  外层执行一次   内层执行所有
		while(left<right){ // 0 4
			//从右向左   笔记准点大  向左移动
			while(srcAarr[right]>=key&&left<right){
				right--;//2
			}
			//比基准小,和基准换位置
			srcAarr[left]=srcAarr[right];//5 2 5 30 25

			//从左向右    比基准点小   继续向右
			while(srcAarr[left]<key&&left<right){//key = 10
				left++;
			}
			srcAarr[right]=srcAarr[left];
		}
		//对基准点重新赋值
		srcAarr[left]=key;
		return left;
	}

	//排序   递归操作   找出口
	public static void sort(int[] arr,int left,int right){
		if(left>=right){
			return;
		}
		//获取基准点
		int point=getIndex(arr, left, right);
		//基准点左侧  拆分的小数组
		sort(arr, left, point-1);
		//基准点右侧的
		sort(arr, point+1,right);
	}

	public static void main(String[] args) {
		MyArraysUtils arraysUtils = new MyArraysUtils();
		int[] array = arraysUtils.addIntArray(10);
		arraysUtils.showArr(array);
		sort(array, 0, array.length-1);
		arraysUtils.showArr(array);

	}

}
