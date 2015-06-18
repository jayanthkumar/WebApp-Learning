package com.jsomiset.resources;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.jsomiset.common.RestBase;
import com.jsomiset.resourcesImpl.BookResourceImpl;

public class BookResourceTest extends RestBase{
	public BookResourceTest() {
		this.setResourcePath(BookResourceImpl.ITEMS_URL);
	}
	
	@Test
	public void v1ItemsShouldReturnStatus200() {
		assertThat(this.target.request().head().getStatus(), is(200));
	}
	
}
