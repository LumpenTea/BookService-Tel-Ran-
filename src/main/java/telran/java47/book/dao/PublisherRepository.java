package telran.java47.book.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import telran.java47.book.model.Publisher;

public interface PublisherRepository extends PagingAndSortingRepository<Publisher, String> {
	@Query(value="SELECT * FROM  BOOK JOIN PUBLISHER ON PUBLISHER_PUBLISHER_NAME=PUBLISHER_NAME JOIN BOOK_AUTHORS ON ISBN=BOOK_ISBN WHERE AUTHORS_NAME=?1", nativeQuery=true)
	List<Publisher> findAuthorsPublishers(String authorName); 
}
