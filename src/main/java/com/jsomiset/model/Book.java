package com.jsomiset.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
public class Book implements Serializable,Comparable<Book> {
	// Must have no argument constructor
	public Book() {
	}

	public Book(int id) {
		setId(id);
	}

	private int id;

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}

	private String image;

	@NotNull
	public String getImage() {
		return image;
	}

	@XmlElement
	public void setImage(String image) {
		this.image = image;
	}

	private String title;

	@Size(min = 2)
	@NotNull
	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	private String author;

	@NotNull(message = "{book.author.notnull}")
	public String getAuthor() {
		return author;
	}

	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}

	private double price;

	// @Pattern(regexp = "^\\d+$") // Causing error lets debug TODO
	// @Pattern(regexp = "[0-9]+", message = "{person.id.pattern}")
	// HV000030: No validator could be found for type: java.lang.Double
	@NotNull
	public double getPrice() {
		return price;
	}

	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}

	private String link;

	@NotNull
	public String getLink() {
		return link;
	}

	@XmlElement
	public void setLink(String link) {
		this.link = link;
	}

	// Default true availablity
	private boolean isAvailable = true;

	public boolean isAvailable() {
		return isAvailable;
	}

	@XmlElement(defaultValue = "true")
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	private Date published;

	public Date getPublished() {
		return published;
	}

	@XmlElement
	public void setPublished(Date published) {
		this.published = published;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Book book = (Book) o;

		if (id != book.id)
			return false;
		if (Double.compare(book.price, price) != 0)
			return false;
		if (author != null ? !author.equals(book.author) : book.author != null)
			return false;
		if (image != null ? !image.equals(book.image) : book.image != null)
			return false;
		if (title != null ? !title.equals(book.title) : book.title != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id;
		result = 31 * result + (image != null ? image.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (author != null ? author.hashCode() : 0);
		temp = Double.doubleToLongBits(price);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", image=" + image + ", title=" + title
				+ ", author=" + author + ", price=" + price + ", link=" + link
				+ ", isAvailable=" + isAvailable + "]";
	}

	@Override
	public int compareTo(Book o) {
		// TODO Auto-generated method stub
		return 0;
	}

}