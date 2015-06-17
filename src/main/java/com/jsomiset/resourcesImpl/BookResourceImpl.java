package com.jsomiset.resourcesImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.JSONP;

import com.jsomiset.model.Book;
import com.jsomiset.resources.BookResource;

/**
 * Book Resource
 * @author jsomiset
 *
 */
@Path("v1/books")
public class BookResourceImpl implements BookResource{
//	private static final Logger logger = LoggerFactory
//			.getLogger(BookResourceImpl.class);
	public static final String ITEMS_URL = "/api/v1/books";

	private List<Book> mybooks = new ArrayList<Book>();

	public BookResourceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("Book Resource was initilized");
		Book book = new Book();
		book.setId(1);
		book.setImage(":)");
		book.setTitle("My Life");
		book.setAuthor("Jayanth");
		book.setPrice(4);
		book.setAvailable(true);
		this.mybooks.add(book);
	}

	@GET
	@Path("/welcome")
	@Produces("text/plain")
	@JSONP(queryParam = "callback")
	public String getHello() {
		return "HeLLO, User Welcome";
	}

	@DELETE
	@Override
	public void deleteAllBooks() {
		// TODO Auto-generated method stub
		mybooks.clear();
	}

	@GET
	@Override
	@PermitAll
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		System.out.println("All books are going to return");
		return this.mybooks;
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Pattern(regexp = "[0-9]+", message = "The id must be a valid number")
	@RolesAllowed("ADMIN")
	@Override
	public Book getBook(@PathParam("id") String id) {
		// TODO Auto-generated method stub
		Book foundBook = new Book();
		for (Book book : this.mybooks) {
			if (book.getId() == Integer.parseInt(id)) {
				foundBook = book;
				break;
			}
		}
		return foundBook;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/add")
	@Override
	public Book saveOrUpdateBook(@Valid Book book) { 
		// {"id":1,"image":":)","title":"My life","author":"Jayanth","price":4}
		// "1">   <author>KUMAR</author>   <image>:)</image>   <price>4.0</price>   <title>My life</title></student>
		System.out.println("Request ==>"+ book);
		mybooks.add(book);
		return book;
	}

	@DELETE
	@Path("/{id}")
	@Override
	public Book deleteBook(@PathParam("id") String id) {
		// TODO Auto-generated method stub
		Book foundBook = null;
		for (Book book : this.mybooks) {
			if (book.getId() == Integer.parseInt(id)) {
				foundBook = book;
				break;
			}
		}
		if (null != foundBook) {
			this.mybooks.remove(foundBook);
		}
		return foundBook;
	}

}
