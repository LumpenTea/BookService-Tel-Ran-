package telran.java47.book.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import telran.java47.book.exceptions.BookNotFoundException;
import telran.java47.book.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Book> findByAuthor(String authorName) {
		TypedQuery<Book> query = em.createQuery("select b Book from Book b join b.authors a where a.name=?1", Book.class);
		query.setParameter(1, authorName);
		return query.getResultList();
	}

	@Override
	public boolean existsById(String isbn) {
		return em.find(Book.class, isbn) != null;
	}

	@Override
	public Book save(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public Optional<Book> findById(String isbn) {
		return Optional.ofNullable(em.find(Book.class, isbn));
	}

	@Override
	public Book deleteById(String isbn) {
		Book book = findById(isbn).orElseThrow(BookNotFoundException::new);
		em.remove(book);
		return book;
	}

}
