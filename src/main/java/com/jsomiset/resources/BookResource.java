package com.jsomiset.resources;

import java.util.List;

import javax.validation.Valid;

import com.jsomiset.model.Book;

public interface BookResource {
	public void deleteAllBooks();

	public List<Book> getAllBooks();

	public Book saveOrUpdateBook(@Valid Book book);

	public Book getBook(String id);

	public Book deleteBook(String id);
}
