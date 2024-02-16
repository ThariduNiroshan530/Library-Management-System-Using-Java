import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    // List to store the books in the library
    private List<Book> books;
    
    // Constructor to initialize the Library
    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", ISBN: " + book.getIsbn());
            }
        }
    }

    public Book findBookByISBN(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    // Add a new book to the library
    public void addNewBookToLibrary() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title of the new book:");
        String title = scanner.nextLine();

        System.out.println("Enter the author of the new book:");
        String author = scanner.nextLine();

        System.out.println("Enter the ISBN of the new book:");
        String isbn = scanner.nextLine();

        // Assuming the new book is initially available
        Book newBook = new Book(title, author, isbn);
        addBook(newBook);

        System.out.println("New book added to the library successfully!");
    }
}

class User {
    private static int userCount = 0;
    private int userId;
    private List<Book> borrowedBooks;

    public User() {
        this.userId = ++userCount;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
        System.out.println("Book borrowed successfully!");
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
        System.out.println("Book returned successfully!");
    }
}

class FictionBook extends Book {
    private String genre;

    public FictionBook(String title, String author, String isbn, String genre) {
        super(title, author, isbn);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}

class NonFictionBook extends Book {
    private String category;

    public NonFictionBook(String title, String author, String isbn, String category) {
        super(title, author, isbn);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        List<User> users = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Adding users
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        User user5 = new User();
        User user6 = new User();
        User user7 = new User();
        User user8 = new User();
        User user9 = new User();
        User user10 = new User();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);

        // Adding sample books to the library
        Book fictionBook1 = new FictionBook("The Lord of the Rings", "J.R.R. Tolkien", "978-0-618-25713-8", "Fantasy");
        Book fictionBook2 = new FictionBook("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "978-0-7475-3269-6", "Fantasy");
        Book nonFictionBook1 = new NonFictionBook("Sapiens", "Yuval Noah Harari", "978-0-06-231609-7", "History");
        Book nonFictionBook2 = new NonFictionBook("Cosmos", "Carl Sagan", "978-0-345-34881-0", "Science");

        library.addBook(fictionBook1);
        library.addBook(fictionBook2);
        library.addBook(nonFictionBook1);
        library.addBook(nonFictionBook2);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Add a New Book to the Library");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.println("\nEnter ISBN to borrow a book:");
                    String isbnToBorrow = scanner.nextLine();
                    Book bookToBorrow = library.findBookByISBN(isbnToBorrow);

                    if (bookToBorrow != null) {
                        System.out.println("Enter your user ID:");
                        int userId = scanner.nextInt();
                        User user = findUserById(users, userId);

                        if (user != null) {
                            user.borrowBook(bookToBorrow);
                        } else {
                            System.out.println("User not found.");
                        }
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.println("\nEnter ISBN to return a book:");
                    String isbnToReturn = scanner.nextLine();
                    Book bookToReturn = library.findBookByISBN(isbnToReturn);

                    if (bookToReturn != null) {
                        System.out.println("Enter your user ID:");
                        int userId = scanner.nextInt();
                        User user = findUserById(users, userId);

                        if (user != null) {
                            user.returnBook(bookToReturn);
                        } else {
                            System.out.println("User not found.");
                        }
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    library.addNewBookToLibrary();
                    break;
                case 5:
                    System.out.println("Exiting the Library Management System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static User findUserById(List<User> users, int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
}
