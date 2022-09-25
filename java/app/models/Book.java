package app.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
	private int id;
	@NotEmpty(message = "Это поле не должно быть пустым")
	@Size(min = 2, max = 100, message = "Это поле должно содержать 2-100 символов")
	private String title;
	@NotEmpty(message = "Это поле не должно быть пустым")
	@Size(min = 2, max = 100, message = "Это поле должно содержать 2-100 символов")
	private String author;
	@Min(value = 1500, message = "Год публикации должен быть не раньше 1500")
	private int yearOfPublishing;

	public Book(String name, String author, int yearOfPublishing) {
		this.title = name;
		this.author = author;
		this.yearOfPublishing = yearOfPublishing;
	}

	public Book() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYearOfPublishing() {
		return yearOfPublishing;
	}

	public void setYearOfPublishing(int yearOfPublishing) {
		this.yearOfPublishing = yearOfPublishing;
	}
}
