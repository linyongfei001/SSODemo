package aura.javaSE;

import java.util.Scanner;
/**��ϰ��ö��*/
enum Gender{
	MALE,FEMALE;
}
public class TestGanderEnum {

	public boolean isMale(Gender gender) {
		if(gender == Gender.MALE) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		TestGanderEnum t = new TestGanderEnum();
		Scanner input = new Scanner(System.in);
		System.out.println("�����Ա�");
		String sex = input.next();
		Gender gender = Gender.valueOf(sex);
		boolean r = t.isMale(gender);
		if(r) {
			System.out.println("���е�");
		}else {
			System.out.println("�����е�");
		}

	}

}
