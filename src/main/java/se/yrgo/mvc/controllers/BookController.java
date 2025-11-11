package se.yrgo.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import se.yrgo.mvc.domain.Book;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BookController {

    @GetMapping("/home")
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("time", LocalDateTime.now());
        mav.addObject("greeting", "Hello customer");
        return mav;
    }

    @GetMapping("/books")
    public ModelAndView books() {
        return new ModelAndView("booklist", "books",
                List.of(new Book("Earth", "John Doe", "Science"),
                        new Book("UFO", "Jack", "Mystery")));
    }

    @GetMapping("/genre")
    public ModelAndView genre() {
        return new ModelAndView("genre", "book", new Book());
    }

    @PostMapping("/submit")
    public ModelAndView handleForm(@ModelAttribute Book book) {
        ModelAndView modelAndView = new ModelAndView("result");
        if (book.getGenre().equalsIgnoreCase("science")) {
            modelAndView.addObject("book", new Book("Earth", "John Doe", "Science"));
        } else if (book.getGenre().equalsIgnoreCase("mystery")) {
            modelAndView.addObject("book", new Book("UFO", "Jack", "Mystery"));
        }
        return modelAndView;
    }
}
