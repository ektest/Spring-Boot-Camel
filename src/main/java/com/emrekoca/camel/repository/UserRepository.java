package com.emrekoca.camel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.emrekoca.camel.domain.User;

@Transactional
public interface UserRepository extends Repository<User, Long> {
	// Spring Data JPA methods and queries
	//@Query(nativeQuery = true) // if you declare named query we can do this
	List<User> findAll();

	// Creating Database Queries With the @Query Annotation
	@Query("SELECT u FROM User u where u.name = :name AND u.email = :email")
	public Optional<User> findByNameAndEmail(@Param("name") String name, @Param("email") String email);

	@Query("SELECT u FROM User u WHERE u.name = 'Emre'")
	public List<User> findById();

	@Query(value = "SELECT * FROM users u WHERE " + "LOWER(u.name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR "
			+ "LOWER(u.email) LIKE LOWER(CONCAT('%',:searchTerm, '%'))", nativeQuery = true)
	List<User> findBySearchTermNative(@Param("searchTerm") String searchTerm);

	// Creating Database Queries From Method Names using Spring Data JPA
	public List<User> findFirst3ByEmailOrderByEmailAsc(String email);

	public List<User> findTop3ByEmailOrderByEmailAsc(String email);

	public long countByEmail(String email);

	public List<User> findByNameOrEmail(String name, String email);

	public List<User> findDistinctByEmail(String title);

	public List<User> findByNameContainsOrEmailContainsAllIgnoreCase(String name, String email);

}