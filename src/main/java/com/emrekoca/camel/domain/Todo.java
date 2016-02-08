package com.emrekoca.camel.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

@Entity
@NamedQuery(name = "Todo.findBySearchTermNamed", query = "SELECT t FROM Todo t WHERE "
		+ "LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
		+ "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
@NamedNativeQuery(name = "Todo.findByTitleIs", query = "SELECT * FROM todos t WHERE "
		+ "t.title = :searchTerm")
@Table(name = "todos")
public final class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "creation_time", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
	private ZonedDateTime creationTime;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "modification_time")
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
	private ZonedDateTime modificationTime;

	@Column(name = "title", nullable = false, length = 100)
	private String title;

	@Version
	private long version;

	public Todo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(ZonedDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(ZonedDateTime modificationTime) {
		this.modificationTime = modificationTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", creationTime=" + creationTime + ", description=" + description
				+ ", modificationTime=" + modificationTime + ", title=" + title + ", version=" + version + "]";
	}
}
