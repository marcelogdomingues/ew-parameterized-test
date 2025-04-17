package nl.engineers.week.library;

import nl.engineers.week.parameterizedtest.library.Book;
import nl.engineers.week.parameterizedtest.library.Library;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibraryTest {

    @Test
    @DisplayName("Test adding a book to the library")
    void testAddBook() {
        Library library = new Library();
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        library.addBook(book);
        assertTrue(library.getAllBooks().contains(book));
    }

    @Test
    @DisplayName("Test removing a book from the library")
    void testRemoveBook() {
        Library library = new Library();
        Book book = new Book("1984", "George Orwell");
        library.addBook(book);
        library.removeBook(book);
        assertFalse(library.getAllBooks().contains(book));
    }

    @Test
    @DisplayName("Test searching for books by title")
    void testSearchByTitle() {
        Library library = new Library();
        Book book1 = new Book("To Kill a Mockingbird", "Harper Lee");
        Book book2 = new Book("To Kill a Mockingbird", "Another Author");
        library.addBook(book1);
        library.addBook(book2);
        List<Book> result = library.searchByTitle("To Kill a Mockingbird");
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Test searching for books by author")
    void testSearchByAuthor() {
        Library library = new Library();
        Book book1 = new Book("Book One", "Author A");
        Book book2 = new Book("Book Two", "Author A");
        library.addBook(book1);
        library.addBook(book2);
        List<Book> result = library.searchByAuthor("Author A");
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Test adding a null book throws exception")
    void testAddNullBook() {
        Library library = new Library();
        assertThrows(IllegalArgumentException.class, () -> library.addBook(null));
    }

    @Test
    @DisplayName("Test removing a non-existent book throws exception")
    void testRemoveNonExistentBook() {
        Library library = new Library();
        Book book = new Book("Non-Existent Book", "Unknown Author");
        assertThrows(IllegalArgumentException.class, () -> library.removeBook(book));
    }



}