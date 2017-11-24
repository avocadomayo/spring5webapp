package com.avocadomayo.spring5webapp.controllers;

import com.avocadomayo.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/** Registers class as a Spring Bean, and wire it into the Spring Context. */
@Controller
public class BookController {

    private BookRepository bookRepository;

    /** Spring will auto-wire in a BookRepository at runtime .*/
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * - This maps it to an HTTP request made to /books. Each time a request comes into /books, this method is invoked.
     * - Spring MVC will pass in an instance of the Model.
     * - Add an attribute to the Model called books, and that's a list of books from the BookRepository. BookRepository
     *   uses Spring Data JPA and uses Hibernate to get a list of Book entries from DB.
     * - Returning "books" we tell Spring MVC to associate this with a view called "books."
     * */
    @RequestMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());

        // Associate it with a Thymeleaf View.
        return "books";
    }
}
