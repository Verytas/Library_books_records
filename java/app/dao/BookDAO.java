package app.dao;

import app.models.Book;
import app.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
@Component
public class BookDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Book> index() {
		return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
	}

	public Book show(int id) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id)
				.stream().findAny().orElse(null);
	}

	public Optional<Book> checkPresence(String author, String title, int yearOfPublishing) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE author=? AND title=? AND year_of_publishing=?",
				new BeanPropertyRowMapper<>(Book.class), author, title, yearOfPublishing).stream().findAny();
	}

	public void save(Book book) {
		jdbcTemplate.update("INSERT INTO Book(title, author, year_of_publishing) VALUES (?, ?, ?)",
				book.getTitle(), book.getAuthor(), book.getYearOfPublishing());
	}

	public void update(int id, Book updatedBook) {
		jdbcTemplate.update("UPDATE Book SET title=?, author=?, year_of_publishing=? WHERE id=?",
				updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYearOfPublishing(), id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
	}

	public Optional<Person> getBookOwner(int id) {
		return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
				"WHERE Book.id=?", new BeanPropertyRowMapper<>(Person.class), id).stream().findAny();
	}

	public void release(int id) {
		jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);
	}

	public void assign(int id, Person chosenPerson) {
		jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", chosenPerson.getId(), id);
	}
}
