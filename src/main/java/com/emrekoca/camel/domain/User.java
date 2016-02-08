package com.emrekoca.camel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;

import com.sun.istack.NotNull;

@Entity
@Table(name = "users")
@CsvRecord(separator = ",", crlf = "UNIX", skipFirstLine = true)
public class User {
	// An autogenerated id (unique for each user in the db)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// The user's email
	@NotNull
	private String email;

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", dateCreated=" + dateCreated + "]";
	}

	// The user's name
	@NotNull
	private String name;

	@Column(name = "CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dateCreated;

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(java.util.Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User() {
	}

	public User(int id) {
		this.id = id;
	}

	public User(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}