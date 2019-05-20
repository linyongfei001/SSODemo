package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.UserInfo;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {


	@Autowired
	UserService service;

	@RequestMapping("login.do")
	public String login(String sname, String password, HttpServletRequest request){
		UserInfo user = service.login(sname, password);
		if( user== null){
			return "fail";
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("sname", sname);
			return "forward:book.do";
		}
	}

	//注册
	@RequestMapping("reg.do")
	public String reg(String sname,String password,Model model){
		int row = service.insert(sname, password);
		if(row >0){
			model.addAttribute("success","注册成功，请登录");
			return "login";
		}else{
			model.addAttribute("defeat","注册失败，请重新注册");
			return "reg";
		}

	}

}
