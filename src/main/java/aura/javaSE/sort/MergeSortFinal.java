package aura.javaSE.sort;

import utils.MyArraysUtils;

public class MergeSortFinal {


	//两部   第一步   拆  //递归操作  出口
	public static void sortChai(int[] arr,int first,int last,int[] newarr){
		if(first<last){
			//计算需要进行拆分别位置
			int mid01=(first+last)/2;
			sortChai(arr, first, mid01, newarr);
			sortChai(arr,mid01+1, last, newarr);
			//是不是递归的
			mergeResult(arr, first, last, mid01, newarr);
		}
	}
	//合并
	public static void mergeResult(int[] arr,int first,int last,int mid,int[] newarr){
		//定义一个数组
		//int[] newarr=new int[arr.length];

		//循环遍历合并  元素
		int m=first,n=mid;
		int k=mid+1,j=last;
		int index=0;
		while(m<=n&&k<=j){
			if(arr[m]<arr[k]){
				newarr[index++]=arr[m++];
			}else{
				newarr[index++]=arr[k++];
			}
		}
		while(m<=n){
			newarr[index++]=arr[m++];
		}
		while(k<=j){
			newarr[index++]=arr[k++];
		}

		for(int i=0;i<index;i++){
			arr[i+first]=newarr[i];
		}
	}

	public static void main(String[] args) {
		MyArraysUtils arraysUtils = new MyArraysUtils();
		int[] array = arraysUtils.addIntArray(10);
		arraysUtils.showArr(array);
		int[] tmp=new int[10];
		sortChai(array, 0,  9,tmp );
		arraysUtils.showArr(array);


	}

}
