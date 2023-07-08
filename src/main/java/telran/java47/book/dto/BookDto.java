package telran.java47.book.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
	    private String isbn;
	    private String title;
	    private Set<AuthorDto> authors;
	    private String publisher;
}
