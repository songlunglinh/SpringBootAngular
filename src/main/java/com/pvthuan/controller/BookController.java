package com.pvthuan.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pvthuan.service.BookService;
import com.pvthuan.model.Book;
@RestController
public class BookController {
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/book", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>> getAll() {
		return new ResponseEntity<List<Book>>(bookService.getBooks(), HttpStatus.OK);
	}
	@RequestMapping(value = "/book", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody Book book) throws URISyntaxException {
		
		if (book.getId() != null) {
			return ResponseEntity.badRequest().header("Failure", "A new book cannot already have an ID").build();
		}
		bookService.create(book);
		return ResponseEntity.created(new URI("/product/" + book.getId())).build();
	}
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> get(@PathVariable("id") Long id) {
		
		Book book = bookService.getBook(id);
		if (book == null) {
			
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Book> update(@PathVariable("id") Long id, @RequestBody Book book) {
		Book b = bookService.getBook(id);
		if (b == null) {
			
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		b.setName(book.getName());
		b.setAuthor(book.getAuthor());
		bookService.update(b);
		return new ResponseEntity<Book>(b, HttpStatus.OK);
	}

	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Book> delete(@PathVariable("id") Long id) {
		Book book = bookService.getBook(id);
		if (book == null) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		bookService.delete(id);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
}
