package aura.javaSE.proxy.dynamic;

public class Text01 {
	public static void main(String[] args) {
		ProxyBaseStudentdao pbsd=new ProxyBaseStudentdao();
		pbsd.insert(new Student());
	}

}
