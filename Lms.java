package com.ss.lms2.classes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashMap;

public class Lms {

	Map<Book, Book> bookMap;
	Map<Author, Author> authorMap;
	Map<Publisher, Publisher> publisherMap;
	// sequence integers are used to guarantee unique id
	int seqBooks;
	int seqAuthors;
	int seqPublishers;
	

	public Lms() {
		this.bookMap = new HashMap<>();
		this.authorMap = new HashMap<>();
		this.publisherMap = new HashMap<>();
	}

// method to initialize book map in LMS from  books.text file
	public void initBooks(Map<Book, Book> bookMap) {

		try (BufferedReader bufr = new BufferedReader(new FileReader("books.txt"))) {

			String line = bufr.readLine();
			// while there is next line in buffer
			while (line != null) {
				// delimits according to text format
				String[] parts = line.split(",");

				Book book = new Book();
				book.setBookID(Integer.parseInt(parts[0]));
				book.setName(parts[1]);
				book.setPublisherID(Integer.parseInt(parts[2]));
				book.setAuthorID(Integer.parseInt(parts[3]));
				// adds book to map
				bookMap.put(book, book);
				// gets next line of file
				line = bufr.readLine();
			}
			seqBooks = bookMap.size() + 100;// initializes seqBooks to number of books +100
		} catch (Exception e) {
			System.out.println("Failed to read from book file");
		}
	}

	// method to initialize author map in LMS from authors.text file
	public void initAuthors(Map<Author, Author> authorMap) {

		try (BufferedReader bufr = new BufferedReader(new FileReader("authors.txt"))) {

			String line = bufr.readLine();
			// while there is next line in buffer
			while (line != null) {
				// delimits line according to txt format
				String[] parts = line.split(",");

				// instantiates author and adds to map
				Author author = new Author();
				author.setAuthorID(Integer.parseInt(parts[0]));
				author.setName(parts[1]);
				authorMap.put(author, author);
				// gets next line of file
				line = bufr.readLine();
			}
			seqAuthors = authorMap.size() + 100;// initializes seqBooks to number of authors +100
		} catch (Exception e) {
			System.out.println("Failed to read author file");
		}
	}

	// method used to intialize publisher map in LMS from publishers.txt file
	public void initPublishers(Map<Publisher, Publisher> publisherMap) {

		try (BufferedReader bufr = new BufferedReader(new FileReader("publishers.txt"))) {

			String line = bufr.readLine();
			// while there is next line in buffer
			while (line != null) {
				// delimits line according to text format
				String[] parts = line.split(",");

				// instantiates publisher and adds to map
				Publisher publisher = new Publisher();
				publisher.setPublisherID(Integer.parseInt(parts[0]));
				publisher.setName(parts[1]);
				publisherMap.put(publisher, publisher);
				// gets next line of file
				line = bufr.readLine();
			}
			seqPublishers = publisherMap.size() + 100;// initializes seqPublishers to number of publishers +2
		} catch (Exception e) {
			System.out.println("Failed to initialize publishers file");
			System.out.println(e.getMessage());
		}
	}

	// method to print books to screen
	public void readBooks() {

		try (BufferedReader bufr = new BufferedReader(new FileReader("books.txt"))) {

			String line = bufr.readLine();
			// while there is next line in buffer
			while (line != null) {
				// delimits according to format, then prints out corresponding label with data
				String[] parts = line.split(",");
				System.out.println("ID: " + parts[0] + "\t\tTitle: " + parts[1] + "\t\t\tpublisher ID: " + parts[2]
						+ "\t\tAuthor Id: " + parts[3]);
				// gets next line of file
				line = bufr.readLine();
			}
		} catch (Exception e) {
			System.out.println("Failed to read from books file");
		}
	}

	// method to print authors to screen
	public void readAuthors() {

		try (BufferedReader bufr = new BufferedReader(new FileReader("authors.txt"))) {

			String line = bufr.readLine();
			// while there is next line in buffer
			while (line != null) {
				// delimits according to format, then prints out corresponding label with data
				String[] parts = line.split(",");
				System.out.println("ID: " + parts[0] + "\t\tName: " + parts[1]);
				// gets next line of file
				line = bufr.readLine();
			}
		} catch (Exception e) {
			System.out.println("Failed to read from authors file");
		}
	}

	// method to print publishers to screen
	public void readPublishers() {

		try (BufferedReader bufr = new BufferedReader(new FileReader("publishers.txt"))) {

			String line = bufr.readLine();
			// while there is next line in buffer
			while (line != null) {
				// delimits according to format, then prints out corresponding label with data
				String[] parts = line.split(",");
				System.out.println("ID: " + parts[0] + "\t\tName: " + parts[1]);
				// gets next line of file
				line = bufr.readLine();
			}
		} catch (Exception e) {
			System.out.println("Failed to read from publishers file");
		}
	}

	// method used to create Publisher, given user input of publisher name
	public void createPublisher(String publisherName) {

		// instantiates publisher with user input
		Publisher publisher = new Publisher();
		publisher.setName(publisherName);

		// if publisher already exists
		if (publisherMap.containsKey(publisher)) {
			System.out.println("sorry, that publisher already exists");
		} else {
			// writes new publisher to file, and adds publisher to map

			// formats according to text file, with unique publisherID
			int s = seqPublishers;
			String seqNum = String.valueOf(s);
			String data = "\n" + seqNum + "," + publisherName;

			// appends data to publishers.txt file
			try {
				Path path = Paths.get("publishers.txt");
				Files.write(path, data.getBytes(), StandardOpenOption.APPEND);
			} catch (Exception e) {
				System.out.println("Failed to write to file");
			}

			// inserts publisher into hashMap
			publisherMap.put(publisher, publisher);
			//increments publisher sequence id number
			seqPublishers ++;
			
		}
		
	}

	// method used to create Author, given user input of author name
	public void createAuthor(String authorName) {

		// instantiates author with user input
		Author author = new Author();
		author.setName(authorName);

		// if author already exists
		if (authorMap.containsKey(author)) {
			System.out.println("sorry, that author already exists");
		} else {
			// writes new author to file, and adds author to map

			// formats according to text file, with unique authorID
			int s = seqAuthors;
			String seqNum = String.valueOf(s);
			String data = "\n" + seqNum + "," + authorName;

			// appends data to authors.txt file
			try {
				Path path = Paths.get("authors.txt");
				Files.write(path, data.getBytes(), StandardOpenOption.APPEND);
			} catch (Exception e) {
				System.out.println("Failed to write to file");
			}

			// inserts publisher into hashMap
			authorMap.put(author, author);
			//increments sequence id 
			seqAuthors ++;
		}
	}
	
		// method used to create book, business rules dictate that book names do not have to be unique
		// book, author, and publisher all together dictate unique book
		// publisher and author must exist for book to be created to maintain referential integrity
		public void createBook(String bookName, String publisherName, String authorName) {

			// ids of publisher and author will be needed since book object references both
			// publisher and author
			int publisherID = 0;
			int authorID = 0;

			// instantiates author and publisher objects to first check if both exist
			Publisher publisher = new Publisher();
			publisher.setName(publisherName);
			Author author = new Author();
			author.setName(authorName);

			// if publisher and author exist in system
			if (publisherMap.containsKey(publisher) && authorMap.containsKey(author)) {
				// gets publisherID, will use to instantiate Book object
				Publisher publisherCopy = publisherMap.get(publisher);
				publisherID = publisherCopy.getPublisherID();

				// gets authorID, will use to instantiate Book object
				Author authorCopy = authorMap.get(author);
				authorID = authorCopy.getAuthorID();
				
				Book book = new Book();
				book.setName(bookName);
				book.setPublisherID(publisherID);
				book.setAuthorID(authorID);

				// if book exists
				if (bookMap.containsKey(book)) {
					System.out.println("Book already exists in system");
				}else{
					// writes new book to file, and adds book to map
	
					// formats data of book according to text file, with unique bookID
					int s = seqBooks;
					String seqNum = String.valueOf(s);
					String data = "\n" + seqNum + "," + bookName + "," + String.valueOf(publisherID) + ","+String.valueOf(authorID);
	
					// appends data of book to books.txt file
					try {
						Path path = Paths.get("books.txt");
						Files.write(path, data.getBytes(), StandardOpenOption.APPEND);
					} catch (Exception e) {
						System.out.println("Failed to write to book file");
					}
	
					// inserts book into hashMap
					bookMap.put(book, book);
					//increments sequence id for books
					seqBooks ++;
					System.out.println("book created");
				}
			}else{
				System.out.println("Sorry, in order to create book the publisher and author must be in system");
			}
		}



	// method used to update publisher name, keeps same ID
	public void updatePublisher(String originalName,String modifiedName) {

		// instantiates publisher object with publisher name given from user input
		Publisher publisher = new Publisher();
		publisher.setName(originalName);

		// if publisher exists
		if (publisherMap.containsKey(publisher)) {
			// gets the id of the publisher, casts to string
			Publisher publisherCopy = publisherMap.get(publisher);
			String pubID = String.valueOf(publisherCopy.getPublisherID());

			// instantiates new publisher object with user input for modified name
			Publisher p = new Publisher();
			p.setName(modifiedName);

			// if makes sure the name isn't already in publisher map
			if (!publisherMap.containsKey(p)) {
				try (Stream<String> stream = Files.lines(Paths.get("publishers.txt"))) {

					// rewrites the file with filtered list that has been modified with new name
					List<String> list = new ArrayList<>();
					list = stream
							.filter(line -> !line.contains(originalName)).collect(Collectors.toList());
					list.add(pubID + "," + modifiedName);
					Files.write(Paths.get("publishers.txt"), list);

					// removes the old publisher from map
					publisherMap.remove(publisher);
					// modifies the name of the copy, then adds to map
					publisherCopy.setName(modifiedName);
					publisherMap.put(publisherCopy, publisherCopy);
				} catch (Exception e) {
					System.out.println("an error occured while trying to modify publisher name");
				}
			} else {
				System.out.println("Sorry the modified publisher name already exists");
			}

		} else {// first input of publisher does not exist
			System.out.println("sorry that publisher does not exist");
		}
	}

	// method used to update Author name, keeps same ID
	public void updateAuthor(String originalName, String modifiedName) {

		// instantiates author object with author name given from user input
		Author author = new Author();
		author.setName(originalName);

		// if author exists
		if (authorMap.containsKey(author)) {
			// gets the id of the author, casts to string
			Author authorCopy = authorMap.get(author);
			String authorID = String.valueOf(authorCopy.getAuthorID());

			// instantiates new publisher object with user input for modified name
			Author a = new Author();
			a.setName(modifiedName);

			// if makes sure the name isn't already in author map
			if (!authorMap.containsKey(a)) {
				try (Stream<String> stream = Files.lines(Paths.get("authors.txt"))) {

					// rewrites the file with filtered list that has been modified with new name
					List<String> list = new ArrayList<>();
					list = stream
							.filter(line -> !line.contains(originalName))
							.collect(Collectors.toList());
					list.add(authorID + "," + modifiedName);
					Files.write(Paths.get("authors.txt"), list);

					// removes the old author from map
					authorMap.remove(author);
					// modifies the name of the copy, then adds to map
					authorCopy.setName(modifiedName);
					authorMap.put(authorCopy, authorCopy);
				} catch (Exception e) {
					System.out.println("an error occured while trying to modify author name");
				}
			} else {
				System.out.println("Sorry the modified author name already exists");
			}
		} else {// first input of author does not exist
			System.out.println("sorry that author does not exist");
		}
	}

	// method used to update book, business rules dictate that book names do not
	// have to be unique
	// book, author, and publisher all together dictate unique book
	public void updateBook(String bookName, String publisherName, String authorName, String modifiedName) {

		// ids of publisher and author will be needed since book object references both
		// publisher and author
		int publisherID = 0;
		int authorID = 0;

		// instantiates author and publisher objects to first check if both exist
		Publisher publisher = new Publisher();
		publisher.setName(publisherName);
		Author author = new Author();
		author.setName(authorName);

		// check to see if publisher and author are valid, gets ids if they are
		if (publisherMap.containsKey(publisher) && authorMap.containsKey(author)) {
			// gets publisherID, will use to instantiate Book object
			Publisher publisherCopy = publisherMap.get(publisher);
			publisherID = publisherCopy.getPublisherID();

			// gets authorID, will use to instantiate Book object
			Author authorCopy = authorMap.get(author);
			authorID = authorCopy.getAuthorID();
		}

		Book book = new Book();
		book.setName(bookName);
		book.setPublisherID(publisherID);
		book.setAuthorID(authorID);

		// if book exists
		if (bookMap.containsKey(book)) {
			// gets the id of the book, casts to string
			Book bookCopy = bookMap.get(book);
			String bookID = String.valueOf(bookCopy.getBookID());

			// changes name of bookCopy with user input for modified name
			bookCopy.setName(modifiedName);
			
			// if makes sure the name isn't already in book map
			if (!bookMap.containsKey(bookCopy)) {
				try (Stream<String> stream = Files.lines(Paths.get("books.txt"))) {
					// IDs casted to string for predicate in filter
					String pID = String.valueOf(publisherID);
					String aID = String.valueOf(authorID);
					
					// rewrites the file with filtered list that has been modified with new name
					List<String> list = new ArrayList<>();
					list = stream.filter(line -> !(line.contains(bookName) && line.contains(pID) && line.contains(aID)))
							.collect(Collectors.toList());
					list.add(bookID + "," + modifiedName + "," +pID + ","+aID);
					Files.write(Paths.get("books.txt"), list);

					// removes the old book from map
					bookMap.remove(book);
					//then adds new bookCopy to map
					bookMap.put(bookCopy, bookCopy);
				} catch (Exception e) {
					System.out.println("an error occured while trying to modify book name");
				}
			} else {
				System.out.println("Sorry the modified book name already exists with that publisher and author");
			}
		} else {// first input of book does not exist
			System.out.println("sorry that book, publisher, author does not exist");
		}
	}

	// method used to delete publisher, if a book has publisher then it deletes the
	// book as well
	public void deletePublisher(String publisherName) {
		
		// instantiates publisher with name from user input
		Publisher publisher = new Publisher();
		publisher.setName(publisherName);

		// if publisher exists
		if (publisherMap.containsKey(publisher)) {
			// gets the id of the publisher, casts to string, will be used to reference id
			// in books file
			Publisher publisherCopy = publisherMap.get(publisher);
			String publisherID = String.valueOf(publisherCopy.getPublisherID());

			// streams the publisher file, then rewrites the file with filtered
			// list(excludes deleted publishers)
			try (Stream<String> stream = Files.lines(Paths.get("publishers.txt"))) {
				List<String> list = new ArrayList<>();

				list = stream.filter(line -> !line.contains(publisherName))
							 .collect(Collectors.toList());
				Files.write(Paths.get("publishers.txt"), list);

				// removes the publisher from map
				publisherMap.remove(publisher);

			} catch (Exception e) {
				System.out.println("an error occured while trying to rewrite publisher file");
			}

			// streams the book file to get attributes of books with the deleted publisher,
			// then deletes book from map
			try (Stream<String> bookstream = Files.lines(Paths.get("books.txt"))) {
				List<String> list = new ArrayList<>();

				list = bookstream
						.filter(line -> line.contains("," + publisherID + ","))
						.collect(Collectors.toList());

				// instantiates book, removes book from book map
				for (String s : list) {
					String[] values = s.split(",");
					Book book = new Book();
					book.setName(values[1]);
					book.setPublisherID(publisherCopy.getPublisherID());
					book.setAuthorID(Integer.parseInt(values[3]));

					bookMap.remove(book);
				}
			} catch (Exception e) {
				System.out.println("an error occured while trying to delete books from map");
			}

			// streams the book file, then rewrites the book file with filtered
			// list(excludes deleted books)
			try (Stream<String> bookstream = Files.lines(Paths.get("books.txt"))) {
				List<String> list = new ArrayList<>();

				list = bookstream
						.filter(line -> !line.contains("," + publisherID + ","))
						.collect(Collectors.toList());
				Files.write(Paths.get("books.txt"), list);

			} catch (Exception e) {
				System.out.println("an error occured while trying to rewrite books file");
			}
		} else {
			System.out.println("sorry that publisher does not exist");
		}


	}

	// method used to delete author, if a book has author then it deletes the
	// book as well
	public void deleteAuthor(String authorName) {
		
		// instantiates author with given input name
		Author author = new Author();
		author.setName(authorName);

		// if author exists
		if (authorMap.containsKey(author)) {
			// gets the id of the author, casts to string, will be used to reference id
			// in books file
			Author authorCopy = authorMap.get(author);
			String authorID = String.valueOf(authorCopy.getAuthorID());

			// streams the author file, then rewrites the file with filtered
			// list(excludes deleted author)
			try (Stream<String> stream = Files.lines(Paths.get("authors.txt"))) {
				List<String> list = new ArrayList<>();

				list = stream
						.filter(line -> !line.contains(authorName))
						.collect(Collectors.toList());
				Files.write(Paths.get("authors.txt"), list);

				// removes the author from map
				authorMap.remove(author);

			} catch (Exception e) {
				System.out.println("an error occured while trying to rewrite author file");
			}

			// streams the book file to get attributes of books with the deleted author,
			// then deletes book from map
			try (Stream<String> bookstream = Files.lines(Paths.get("books.txt"))) {
				List<String> list = new ArrayList<>();

				list = bookstream
						.filter(line -> line.endsWith(authorID))
						.collect(Collectors.toList());

				// instantiates book, removes book from book map
				for (String s : list) {
					String[] values = s.split(",");
					Book book = new Book();
					book.setName(values[1]);
					book.setPublisherID(Integer.parseInt(values[2]));
					book.setAuthorID(authorCopy.getAuthorID());

					bookMap.remove(book);
				}
			} catch (Exception e) {
				System.out.println("an error occured while trying to delete books from map");
			}

			// streams the book file, then rewrites the book file with filtered
			// list(excludes deleted books)
			try (Stream<String> bookstream = Files.lines(Paths.get("books.txt"))) {
				List<String> list = new ArrayList<>();

				list = bookstream
						.filter(line -> !line.endsWith(authorID))
						.collect(Collectors.toList());
				Files.write(Paths.get("books.txt"), list);

			} catch (Exception e) {
				System.out.println("an error occured while trying to rewrite books file");
			}
		} else {
			System.out.println("sorry that author does not exist");
		}

	}

	// method used to delete book, business rules dictate that book names do not
	// have to be unique
	// book, author, and publisher all together dictate unique book
	public void deleteBook(String bookName, String publisherName, String authorName) {

		// ids of publisher and author will be needed since book object references both
		// publisher and author
		int publisherID = 0;
		int authorID = 0;

		// instantiates author and publisher objects to first check if both exist
		Publisher publisher = new Publisher();
		publisher.setName(publisherName);
		Author author = new Author();
		author.setName(authorName);

		// check to see if publisher and author are valid, gets ids if they are
		if (publisherMap.containsKey(publisher) && authorMap.containsKey(author)) {
			// gets publisherID, will use to instantiate Book object
			Publisher publisherCopy = publisherMap.get(publisher);
			publisherID = publisherCopy.getPublisherID();

			// gets authorID, will use to instantiate Book object
			Author authorCopy = authorMap.get(author);
			authorID = authorCopy.getAuthorID();
		}

		Book book = new Book();
		book.setName(bookName);
		book.setPublisherID(publisherID);
		book.setAuthorID(authorID);

		// if book exists
		if (bookMap.containsKey(book)) {
			// streams the book file, then rewrites the file with filtered
			// list(excludes deleted book)
			try (Stream<String> stream = Files.lines(Paths.get("books.txt"))) {
				List<String> list = new ArrayList<>();

				// IDs casted to string for predicate in filter
				String pID = String.valueOf(publisherID);
				String aID = String.valueOf(authorID);

				list = stream
						.filter(line -> !(line.contains(bookName) && line.contains(pID) && line.contains(aID)))
						.collect(Collectors.toList());
				Files.write(Paths.get("books.txt"), list);

				// removes the book from map
				bookMap.remove(book);

			} catch (Exception e) {
				System.out.println("an error occured while trying to rewrite book file");
			}
		} else {
			System.out.println("sorry the combination of book name, publisher name, and author name does not exist");
		}
	}
}
