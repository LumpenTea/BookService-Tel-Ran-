package telran.java47.book.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import telran.java47.book.exceptions.BookNotFoundException;
import telran.java47.book.model.Author;
import telran.java47.book.model.Publisher;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Optional<Author> findById(String authorName) {
		return Optional.ofNullable(em.find(Author.class, authorName));
	}

	@Override
	public Author save(Author author) {
		em.persist(author);
		return author;
	}

	@Override
	public Author deleteById(String authorName) {
		Author author = findById(authorName).orElseThrow(BookNotFoundException::new);
		em.remove(author);
		return author;
	}
}
