package telran.java47.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import telran.java47.book.model.Author;
import telran.java47.book.model.Publisher;

public interface PublisherRepository {
	Stream<Publisher> findDistinctByBooksAuthorsName(String authorName);

	Optional<Publisher> findById(String publisher);

	Publisher save(Publisher publisher);
}
