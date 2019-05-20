package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mapper.BookMapper;
import pojo.Book;
import service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	BookMapper mapper;
	
	@Override
	public List<Book> selBooks() {	
		List<Book> books = mapper.selBooks();
		return books;
	}

	@Override
	public int delete(int bid) {
		
		return mapper.delete(bid);
		
	}

	@Override
	public int update(Book book) {
		
		return mapper.update(book);
	}

	@Override
	public int insert(Book book) {
		
		return mapper.insert(book);
	}

	@Override
	public Book queryOne(int bid) {
		
		return mapper.queryOne(bid);
	}

}
