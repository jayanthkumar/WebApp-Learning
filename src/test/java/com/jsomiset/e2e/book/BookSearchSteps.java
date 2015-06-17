package com.jsomiset.e2e.book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jsomiset.model.Book;
import com.jsomiset.utils.Library;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import cucumber.api.Format;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BookSearchSteps {
	Library library;
	List<Book> result;

	@Before
	public void init() {
		if (library == null) {
			library = new Library();
		}
		if (result == null) {
			result = new ArrayList<>();
		}
	}

	@Given(".+book with the title '(.+)', written by '(.+)', published in (.+)")
	public void addNewBook(final String title, final String author,
			@Format("dd MMMMM yyyy") final Date published) {
//		Book book = new Book(title, author, published);
//		library.addBook(book);
	}

	@When("^the customer searches for books published between (\\d+) and (\\d+)$")
	public void setSearchParameters(@Format("yyyy") final Date from,
			@Format("yyyy") final Date to) {
		result = library.findBooks(from, to);
	}

	@Then("(\\d+) books should have been found$")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}

	@Then("Book (\\d+) should have the title '(.+)'$")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}
}
