package com.pvthuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvthuan.dao.BookDAO;
import com.pvthuan.model.Book;

@Service
public class BookService {
	@Autowired
	BookDAO bookDAO;
	
	public void create(Book book) {
		
		bookDAO.saveAndFlush(book);
	}

	public void update(Book book) {
		
		if (book.getId() != null) {
			bookDAO.save(book);
		}
	}

	public void delete(Long id) {
		
		bookDAO.delete(id);
	}

	public Book getBook(Long id) {
		
		return bookDAO.findOne(id);
	}

	public List<Book> getBooks() {
		return bookDAO.findAll();
	}

}
