package com.avocadomayo.spring5webapp.repositories;

import com.avocadomayo.spring5webapp.model.Author;
import org.springframework.data.repository.CrudRepository;

/** Spring data will see this and wire this into Spring context for use.
 * CRUD stands for create, read, update, delete - we can do all these without writing SQL simply by extending the
 * CrudRepository<T, ID> interface. A Spring Data Repository like CRUD delegates to a specialized Repository object
 * such that alternative storage implementations may be interchanged. */
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
