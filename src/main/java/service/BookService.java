package service;

import java.util.List;

import pojo.Book;

public interface BookService {
	
	List<Book> selBooks();
	
	int delete(int bid);
	
	int update(Book book);
	
	int insert(Book book);
	
	Book queryOne(int bid);
}
