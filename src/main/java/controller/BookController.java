package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.Book;
import service.BookService;

@Controller
public class BookController {

	@Autowired
	BookService service;
	
	@RequestMapping("book.do")
	public String show(Model model){
		List<Book> books = service.selBooks();
		model.addAttribute("books",books);
		return "show";
	}
	
	@RequestMapping("toUpdate.do")
	public String toUpdate(int bid,Model model){
		Book book = service.queryOne(bid);
		model.addAttribute(book);
		return "update";
	}
	
	@RequestMapping("update.do")
	public String update(Book book,Model model){
		int row = service.update(book);
		List<Book> books = service.selBooks();
		model.addAttribute("books",books);
		if(row >0){
			model.addAttribute("mgs","修改成功");
			return "show";
		}else{
			model.addAttribute("mgs","修改失败");
			return "show";
		}
		
	}
	
	@RequestMapping("delete.do")
	public String delete(int bid,Model model){
		int row = service.delete(bid);		
		List<Book> books = service.selBooks();
		model.addAttribute("books",books);
		if(row>0){
			model.addAttribute("mgs","删除成功");
			return "show";
		}else{
			model.addAttribute("mgs","删除失败");
			return "show";
		}
	}
	
	@RequestMapping("add.do")
	public String add(Book book,Model model){
		int row = service.insert(book);
		List<Book> books = service.selBooks();
		model.addAttribute("books",books);
		if(row>0){
			model.addAttribute("mgs","添加成功");
			return "show";
		}else{
			model.addAttribute("mgs","添加失败");
			return "show";
		}
	}
	
}
