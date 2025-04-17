package nl.engineers.week.parameterizedtest.library;

public class Book {

    private final String title;
    private final String author;

    /**
     * Creates a new Book with the given title and author.
     *
     * @param title  the title of the book
     * @param author the author of the book
     */
    public Book(String title, String author) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
        this.title = title;
        this.author = author;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return title.equalsIgnoreCase(book.title) && author.equalsIgnoreCase(book.author);
    }

    @Override
    public int hashCode() {
        return title.toLowerCase().hashCode() + author.toLowerCase().hashCode();
    }
}