package telran.java47.book.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="isbn")
@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = 3189342446960430297L;
	@Id
	private String isbn;
	private String title;
	@ManyToMany
	private Set<Author> authors;
	@ManyToOne
	private Publisher publisher;

}
