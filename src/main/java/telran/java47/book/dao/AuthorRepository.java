package telran.java47.book.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import telran.java47.book.model.Author;

public interface AuthorRepository extends PagingAndSortingRepository<Author, String> {
	@Query(value="SELECT * FROM AUTHOR JOIN BOOK_AUTHORS ON NAME=AUTHORS_NAME WHERE BOOK_ISBN=?1", nativeQuery=true)
	List<Author> findBookAuthors(String isbn);
}
