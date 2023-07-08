package telran.java47.book.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import telran.java47.book.model.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {
	@Query(value="SELECT * FROM BOOK JOIN BOOK_AUTHORS ON ISBN=BOOK_ISBN WHERE AUTHORS_NAME=?1", nativeQuery=true)
	List<Book> findByAuthor(String authorName);
	
	@Query(value="SELECT * FROM BOOK WHERE PUBLISHER_PUBLISHER_NAME=?1", nativeQuery=true)
	List<Book> findByPubisher(String publisherName);
}
