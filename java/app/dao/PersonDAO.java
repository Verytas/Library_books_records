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
public class PersonDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Person> index() {
		return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
	}

	public Person show(int id) {
		return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id)
				.stream().findAny().orElse(null);
	}

	public Optional<Person> show(String fullName, int yearOfBirth) {
		return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=? AND year_of_birth=?", new BeanPropertyRowMapper<>(Person.class),
						fullName, yearOfBirth).stream().findAny();
	}

	public void save(Person person) {
		jdbcTemplate.update("INSERT INTO Person(full_name, year_of_birth) VALUES (?, ?)",
				person.getFullName(), person.getYearOfBirth());
	}

	public void update(int id, Person updatedPerson) {
		jdbcTemplate.update("UPDATE Person SET full_name=?,year_of_birth=? WHERE id=?",
				updatedPerson.getFullName(), updatedPerson.getYearOfBirth(), id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
	}

	public List<Book> getBooksByPersonId(int id) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?",
				new BeanPropertyRowMapper<>(Book.class), id);
	}
}
