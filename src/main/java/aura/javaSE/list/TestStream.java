package aura.javaSE.list;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestStream {

	public static void main(String[] args) {
		IntStream i = IntStream.builder().add(11).add(22).add(33).build();
		//末端方法
//		System.out.println(i.max().getAsInt());//3
//		IntStream i1 = IntStream.builder().add(11).add(22).add(33).build();
//		System.out.println(i1.min().getAsInt());
//		System.out.println(i.min().getAsInt());
		//和
//		System.out.println(i.sum());
		//平均
//		System.out.println(i.average().getAsDouble());
		//个数
//		System.out.println(i.count());
		//流中的所有 数据 都 满足条件 返回true
/*		System.out.println(i.allMatch(new IntPredicate() {

			@Override
			public boolean test(int value) {
				// TODO Auto-generated method stub
				return value>10;
			}
		}));*/
		//11,22,33
//		System.out.println(i.allMatch(v->v > 20));//false
		//只要有 一个 数据 满足条件 就返回 true
//		System.out.println(i.anyMatch(v-> v > 20));//true
		//中间方法
		i.filter(v-> v > 20).forEach(System.out::println);

		//--------------Stream对集合 聚集运算--------------------------------------------
		List<Integer> list = new ArrayList<>();
		list.add(111);
		list.add(222);
		list.add(333);
		list.stream().forEach(System.out::println);
		list.stream().filter(v -> v > 200).forEach(System.out::println);
		System.out.println(list.stream().filter(v -> v>200).count());





	}

}



















