package ru.akozlovskiy.springdz08.domain;

<<<<<<< HEAD
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Genre {

	@Id
=======
public class Genre {

>>>>>>> 207a043bde0d90ca0dc1ffd0239fbbf1e366848b
	private long id;

	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("ID = ").append(id).append(", description = ").append(description);
		return strb.toString();
	}
}
