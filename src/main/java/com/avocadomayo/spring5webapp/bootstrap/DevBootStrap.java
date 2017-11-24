package com.avocadomayo.spring5webapp.bootstrap;

import com.avocadomayo.spring5webapp.model.Author;
import com.avocadomayo.spring5webapp.model.Book;
import com.avocadomayo.spring5webapp.model.Publisher;
import com.avocadomayo.spring5webapp.repositories.AuthorRepository;
import com.avocadomayo.spring5webapp.repositories.BookRepository;
import com.avocadomayo.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** An initialization script to populate DB with some data. Adding @Component makes this a Spring Bean, so it will
 * get wired up by the Spring Context. */
@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    /** During runtime this will get created by the Spring framework. We will get Author and Book Repository
     * implementations from the Spring Data JPA.
     * @param authorRepository
     * @param bookRepository
     */
    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        // Publishers
        Publisher harperCollins = new Publisher("Harper Collins", "123 Main St.");
        Publisher worx = new Publisher("Worx", "567 Richmond Ave.");

        publisherRepository.save(harperCollins);
        publisherRepository.save(worx);

        // Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", harperCollins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
