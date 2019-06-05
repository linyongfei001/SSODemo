package aura.javaSE;


class AgeException extends RuntimeException{
	/*AgeException(String str){
		super(str);
	}*/
	private String str;
	AgeException(String str){
		this.str = str;
	}
	/*	@Override
        public String getMessage() {
            // TODO Auto-generated method stub
            return "message:年龄 必须 0-150之间";
        }*/
	@Override
	public String toString() {

		return "string:年龄必须0-150之间";
	}
	@Override
	public void printStackTrace() {
		System.out.println("print:年龄必须0-150之间");
	}

}


class GenderException extends Exception{
	private String str;
	GenderException(String str){
		this.str = str;
	}
	@Override
	public String getMessage() {
		return str;
	}
}


class Person{
	private int age;
	private String sex;
	public void setSex(String sex) throws GenderException{
		if(sex.equals("男") || sex.equals("女")) {
			this.sex = sex;
		}else {
			throw new GenderException("性别必须是男或女");
		}

	}
	public String getSex() {
		return sex;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if(age >= 0 && age <= 150) {
			this.age = age;
		}else {
			/*this.age = 0;
			System.out.println("出错了");*/
			// AgeException
//			throw new RuntimeException("年龄必须 是 0-150之间");
			throw new AgeException("年龄 必须 是 0-150之间");
		}

	}

}
public class TestException5 {

	public static void main(String[] args) {
		Person zhangsan = new Person();


		try {
			zhangsan.setAge(500);
		} catch (Exception e) {

//				e.printStackTrace();
//				System.out.println(e);
			System.out.println(e.getMessage());
		}

		// alt + shift + z

		try {
			zhangsan.setSex("其它");
		} catch (GenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("其它代码");




	}

}
