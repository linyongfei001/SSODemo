package service;

import pojo.UserInfo;

public interface UserService {
	
	UserInfo login(String sname, String password);
	
	int insert(String sname, String password);
}
