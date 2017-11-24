package com.avocadomayo.spring5webapp.bootstrap;

import com.avocadomayo.spring5webapp.model.Author;
import com.avocadomayo.spring5webapp.model.Book;
import com.avocadomayo.spring5webapp.repositories.AuthorRepository;
import com.avocadomayo.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/** An initialization script to populate DB with some data. Adding @Component makes this a Spring Bean, so it will
 * get wired up by the Spring Context. */
@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    /** During runtime this will get created by the Spring framework. We will get Author and Book Repository
     * implementations from the Spring Data JPA.
     * @param authorRepository
     * @param bookRepository
     */
    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        // Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", "Harper Collins");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", "Worx");
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
