package pojo;

public class UserInfo {
	
	private int sid;
	private String sname;
	private String password;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserInfo [sid=" + sid + ", sname=" + sname + ", password=" + password + "]";
	}
	
	
}
