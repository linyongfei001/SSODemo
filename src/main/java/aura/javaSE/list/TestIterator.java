package aura.javaSE.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**集合遍历*/
public class TestIterator {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		//----------------------遍历-------------------------------
		//1.List独特的 索引 :普通for循环遍历
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		//2.增强for
		for(String s : list) {
			System.out.println(s);
		}
		//3 集合对象的forEach方法遍历
/*		list.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});*/
//		list.forEach(t->{System.out.println(t);});
		list.forEach(System.out::println);
		//4 获得 某一个 集合 的迭代器
		System.out.println("--------------------------");
		Iterator<String> i = list.iterator();
	/*	System.out.println(i.next());//
		System.out.println(i.next());
		i.remove();//删除最近一次访问的元素
		System.out.println(list);*/
		//判断 是否存在下一个元素，存在 true
		while(i.hasNext()) {
			System.out.println(i.next());

		}
		//5迭代器的方法
		Iterator<String> i1 = list.iterator();
		i1.forEachRemaining(System.out::println);
		//6.使用 Stream流的 方法
		list.stream().forEach(System.out::println);
		//ListIterator专门为 List迭代的
		//7.ListIterator是Iterator的子接口
		System.out.println("-----------List-----------");
		ListIterator<String> li = list.listIterator();
		while(li.hasNext()) {
			System.out.println(li.next());
			li.add("hello");
		}
		System.out.println("--------------------------");
		//向上
		while(li.hasPrevious()) {
			System.out.println(li.previous());
		}



	}

}
