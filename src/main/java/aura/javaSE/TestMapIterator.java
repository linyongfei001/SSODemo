package aura.javaSE;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

public class TestMapIterator {

	public static void main(String[] args) {
		// 遍历
		Map<Integer,String> map = new HashMap<>();
		map.put(11, "aa");
		map.put(22, "bb");
		map.put(33, "cc");
		System.out.println(map);
		//遍历
		//1
		map.forEach(new BiConsumer<Integer, String>() {
			@Override
			public void accept(Integer t, String u) {
				System.out.println(t+","+u);
			}
		});
		map.forEach((k,v)->System.out.println(k+","+v));
		//2 各自遍历 键的集合 set和 值的集合 Collection
		map.keySet().iterator().forEachRemaining(System.out::println);
		map.values().iterator().forEachRemaining(System.out::println);
		//3 整体 作为一个 键值对 遍历
		Iterator<Entry<Integer,String>> i = map.entrySet().iterator();
		while(i.hasNext()) {
			Entry<Integer,String> e = i.next();//键值对
			System.out.println(e);
			System.out.println(e.getKey());
			System.out.println(e.getValue());
		}
		map.entrySet().iterator().forEachRemaining(e->System.out.println(e+","+e.getKey()+","+e.getValue()));
	}

}
