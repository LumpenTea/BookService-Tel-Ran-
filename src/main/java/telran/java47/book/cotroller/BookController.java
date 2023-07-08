package telran.java47.book.cotroller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java47.book.dto.AuthorDto;
import telran.java47.book.dto.BookDto;
import telran.java47.book.dto.PublisherDto;
import telran.java47.book.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {
	
	final BookService service;
	
	@PostMapping("/book")
	public boolean addBook(@RequestBody BookDto bookDto) {
		return service.addBook(bookDto);
	}

	@GetMapping("book/{isbn}")
	public BookDto findBookByIsbn(@PathVariable String isbn) {
		return service.findBookByIsbn(isbn);
	}

	@DeleteMapping("/book/{isbn}")
	public BookDto removeBook(@PathVariable String isbn) {
		return service.removeBook(isbn);
	}

	@PutMapping("/book/{isbn}/title/{newTitle}")
	public BookDto updateBookTitle(@PathVariable String isbn, @PathVariable String newTitle) {
		return service.updateBookTitle(isbn, newTitle);
	}

	@GetMapping("/books/author/{authorName}")
	public List<BookDto> findBooksByAuthor(@PathVariable String authorName) {
		return service.findBooksByAuthor(authorName);
	}

	@GetMapping("/books/publisher/{publisherName}")
	public List<BookDto> findBooksByPublisher(@PathVariable String publisherName) {
		return service.findBooksByPublisher(publisherName);
	}

	@GetMapping("/authors/book/{isbn}")
	public List<AuthorDto> findBookAuthors(@PathVariable String isbn) {
		return service.findBookAuthors(isbn);
	}

	@GetMapping("/publishers/author/{authorName}")
	public List<PublisherDto> findPublishersByAuthor(@PathVariable String authorName) {
		return service.findPublishersByAuthor(authorName);
	}

	@DeleteMapping("/author/{authorName}")
	public AuthorDto removeAuthorAndBooks(@PathVariable String authorName) {
		return service.removeAuthorAndBooks(authorName);
	}
}
