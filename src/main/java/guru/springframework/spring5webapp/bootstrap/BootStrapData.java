package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository= publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book newBook = new Book("Test Book", "123456");
        eric.getBooks().add(newBook);
        newBook.getAuthors().add(eric);

        Publisher publisherNew = new Publisher();
        publisherNew.setName("Owiin");
        publisherNew.setCity("Cirta");
        publisherNew.setState("Constantine");

        publisherRepository.save(publisherNew);

        newBook.setPublisher(publisherNew);
        publisherNew.getBooks().add(newBook);

        authorRepository.save(eric);
        bookRepository.save(newBook);


        Author rod = new Author("rod", "Evans");
        Book newBookRod = new Book("Test Book rod", "123456");
        rod.getBooks().add(newBookRod);
        newBookRod.getAuthors().add(rod);

        newBookRod.setPublisher(publisherNew);
        publisherNew.getBooks().add(newBookRod);

        authorRepository.save(rod);
        bookRepository.save(newBookRod);

        System.out.println("Started in BootStrapData");
        System.out.println("Number of books : "+ bookRepository.count());
        System.out.println("Publisher books : "+ publisherNew.getBooks().size());

    }
}
