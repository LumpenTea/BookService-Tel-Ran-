package telran.java47.book.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class BookAuthorKey implements Serializable {
	private static final long serialVersionUID = 2881956086768080773L;
	
	@Column(name="book_id")
	String isbn;
	
	@Column(name="author_id")
	String authorName;
}
