
package telran.java47.book.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="name")
@Entity
@Table(name="Author")
public class Author implements Serializable {
	private static final long serialVersionUID = -1958354963944315511L;
	@Id
	private String name;
	private LocalDate birthDate;
	@ManyToMany(mappedBy="authors", cascade=CascadeType.ALL)
	private Set<Book> books;
	
	public Author(String name, LocalDate birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}
}
