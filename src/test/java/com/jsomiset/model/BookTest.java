package com.jsomiset.model;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BookTest {
	private static Validator validator;
	@BeforeClass
	public static void setup() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	@Test
	public void idShouldHaveSettersAndGetters() {
		Book book = new Book();
		book.setId(123);
		assertEquals(123, book.getId());
	}

	@Test
	public void equalsShouldFailIfIdIsNotTheSame() {
		Book actual = new Book(111);
//		actual.setImage("image");
		actual.setTitle("title");
		actual.setAuthor("author");
//		actual.setPrice(12.34);
		Book expected = new Book(222);
//		expected.setImage("image");
		expected.setTitle("title");
		expected.setAuthor("author");
//		expected.setPrice(12.34);
		assertThat(actual, is(not(equalTo(expected))));
	}
	@Test
	public void validationShouldFailIfPropertiesNotPassed(){
		Book book = new Book();
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		for(ConstraintViolation<Book> violate: violations) {
			System.out.println("Each violate is "+ violate);
		}
		System.out.println("succesfully validated");
		assertEquals(violations.size(), 4);
//		System.out.println("** "+validator.validate(book));
	}
}
