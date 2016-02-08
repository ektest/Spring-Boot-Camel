package com.emrekoca.camel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.emrekoca.camel.domain.Todo;

public interface TodoRepository extends Repository<Todo, Long> {

	List<Todo> findBySearchTermNamed(@Param("searchTerm") String searchTerm);

	@Query(nativeQuery = true)
	List<Todo> findByTitleIs(@Param("searchTerm") String searchTerm);

	@Query(value = "SELECT * FROM todos t WHERE t.title = 'title'", nativeQuery = true)
	List<Todo> findByTitle();

	List<Todo> findByDescriptionContainsOrTitleContainsAllIgnoreCaseOrderByTitleAsc(String descriptionPart,
			String titlePart);

	// sort the query results using Sort.class
	@Query("SELECT t FROM Todo t WHERE " + "LOWER(t.title) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR "
			+ "LOWER(t.description) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
	List<Todo> findBySearchTerm(@Param("searchTerm") String searchTerm, Sort sort);

	// page the query results using Pageable.class
	// Decide the returned type. We can return List<T>, Slice<T>, or Page<T>
	// objects.
	@Query("SELECT t FROM Todo t WHERE " + "LOWER(t.title) LIKE LOWER(CONCAT('%',:searchTerm, '%')))")
	List<Todo> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageRequest);

	@Query("SELECT t FROM Todo t WHERE " + "LOWER(t.title) LIKE LOWER(CONCAT('%',:searchTerm, '%')))")
	Page<Todo> findBySearchTerm2(@Param("searchTerm") String searchTerm, Pageable pageRequest);

	@Query("SELECT t FROM Todo t WHERE " + "LOWER(t.title) LIKE LOWER(CONCAT('%',:searchTerm, '%')))")
	Slice<Todo> findBySearchTerm3(@Param("searchTerm") String searchTerm, Pageable pageRequest);

}