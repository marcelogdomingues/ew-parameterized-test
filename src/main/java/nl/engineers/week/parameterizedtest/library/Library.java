package nl.engineers.week.parameterizedtest.library;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private final List<Book> books = new ArrayList<>();

    /**
     * Adds a book to the library.
     *
     * @param book the book to add
     * @throws IllegalArgumentException if the book is null
     */
    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
        books.add(book);
    }

    /**
     * Removes a book from the library.
     *
     * @param book the book to remove
     * @throws IllegalArgumentException if the book is not in the library
     */
    public void removeBook(Book book) {
        if (!books.contains(book)) {
            throw new IllegalArgumentException("Book not found in the library.");
        }
        books.remove(book);
    }

    /**
     * Searches for books by title.
     *
     * @param title the title to search for
     * @return a list of books with the matching title
     */
    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Searches for books by author.
     *
     * @param author the author to search for
     * @return a list of books by the matching author
     */
    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Returns the list of all books in the library.
     *
     * @return the list of books
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}