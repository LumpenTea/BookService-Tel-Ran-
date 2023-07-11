package telran.java47.book.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import telran.java47.book.model.Book;
import telran.java47.book.model.Publisher;

public interface BookRepository {
	@Query(value="SELECT * FROM BOOK JOIN BOOK_AUTHORS ON ISBN=BOOK_ISBN WHERE AUTHORS_NAME=?1", nativeQuery=true)
	List<Book> findByAuthor(String authorName);

	boolean existsById(String isbn);

	Book save(Book book);

	Optional<Book> findById(String isbn);

	Book deleteById(String isbn);
}
