package aura.javaSE;
//        T 类型参数  (形式类型参数)
class Point<T>{
	private T x;
	private T y;
	public T getX() {
		return x;
	}
	public void setX(T x) {
		this.x = x;
	}
	public T getY() {
		return y;
	}
	public void setY(T y) {
		this.y = y;
	}
}
public class TestPoint1 {

	public static void main(String[] args) {
		//应用
		//类型<具体的类型参数：实际类型参数>  只能是引用类型
		//Point<Integer>：参数化类型
		Point<Integer> p1 = new Point<Integer>();
		p1.setX(11);
		p1.setY(22);
		System.out.println(p1.getX()+","+p1.getY());
		Point<Double> p2 = new Point<Double>();
		p2.setX(11.1);
		p2.setY(22.2);
		System.out.println(p2.getX()+","+p2.getY());
		//  类型推断  菱形语法 7.0
		Point<String> p3 = new Point<>();
		p3.setX("11.1");
		p3.setY("22.2");
		System.out.println(p3.getX()+","+p3.getY());
		//
		Point<Object> p4 = new Point<>();
		p4.setX(11);// int -> Integer -> Object
		p4.setY(22);//String -> Object
		Integer x = (Integer)p4.getX();//Object
		Integer y = (Integer)p4.getY();
		System.out.println(x+","+y);
		TestPoint1 t1 = new TestPoint1();
//		t1.show(p4);// Point<Object>
	}

	public void show(Point<String> ps,Point<? extends Object> po) {
		po = ps;
	}
/*	public void show(Point<? super Number> p) {
		System.out.println(p.getX()+":"+p.getY());
	}*/
	//类型的限制：只接受数值  Byte,Short,Integer,Long,Float,Double
	/*public void show(Point<? extends Number> p) {
		System.out.println(p.getX()+":"+p.getY());
	}*/
	//?
/*	public void show(Point<?> p) {
		System.out.println(p.getX()+":"+p.getY());
	}*/



}
