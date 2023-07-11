package telran.java47.book.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java47.book.dao.AuthorRepository;
import telran.java47.book.dao.BookRepository;
import telran.java47.book.dao.PublisherRepository;
import telran.java47.book.dto.AuthorDto;
import telran.java47.book.dto.BookDto;
import telran.java47.book.dto.PublisherDto;
import telran.java47.book.exceptions.BookNotFoundException;
import telran.java47.book.model.Author;
import telran.java47.book.model.Book;
import telran.java47.book.model.Publisher;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	final BookRepository bookRepository;
	final AuthorRepository authorRepository;
	final PublisherRepository publisherRepository;
	final ModelMapper modelMapper;
	
	@Override
	@Transactional
	public boolean addBook(BookDto bookDto) {
		if(bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
				.orElseGet(() -> publisherRepository.save(new Publisher(bookDto.getPublisher())));
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(author -> authorRepository.findById(author.getName())
						.orElseGet(() -> authorRepository.save(new Author(author.getName(), author.getBirthDate()))))
				.collect(Collectors.toSet());
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(BookNotFoundException::new);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional
	public BookDto removeBook(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(BookNotFoundException::new);
		bookRepository.deleteById(isbn);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional
	public BookDto updateBookTitle(String isbn, String newTitle) {
		Book book = bookRepository.findById(isbn).orElseThrow(BookNotFoundException::new);
		book.setTitle(newTitle);
		bookRepository.save(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	public List<BookDto> findBooksByAuthor(String authorName) {
		Author author = authorRepository.findById(authorName).orElseThrow(BookNotFoundException::new);
		return author.getBooks().stream().map(b -> modelMapper.map(b, BookDto.class)).toList();
	}

	@Override
	public List<BookDto> findBooksByPublisher(String publisherName) {
		Publisher publisher = publisherRepository.findById(publisherName).orElseThrow(BookNotFoundException::new);
		return publisher.getBooks().stream().map(b -> modelMapper.map(b, BookDto.class)).toList();
	}

	@Override
	public List<AuthorDto> findBookAuthors(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(BookNotFoundException::new);
		return book.getAuthors().stream().map(a -> modelMapper.map(a, AuthorDto.class)).toList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<PublisherDto> findPublishersByAuthor(String authorName) {
		return publisherRepository.findDistinctByBooksAuthorsName(authorName).map(p -> modelMapper.map(p, PublisherDto.class)).toList();
	}

	@Override
	@Transactional
	public AuthorDto removeAuthorAndBooks(String authorName) {
		Author author = authorRepository.deleteById(authorName);
		return modelMapper.map(author, AuthorDto.class);
	}
}
