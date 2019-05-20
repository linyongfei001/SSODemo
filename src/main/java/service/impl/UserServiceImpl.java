package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.UserMapper;
import pojo.Book;
import pojo.UserInfo;
import service.UserService;

@Service("servcie")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;
	
	@Override
	public UserInfo login(String sname, String password) {
		UserInfo info = mapper.login(sname, password);
		return info;
	}

	@Override
	public int insert(String sname, String password) {
		int row = mapper.insert(sname, password);	
		return row;
	}


}
