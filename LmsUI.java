package com.ss.lms2.classes;
import java.util.Scanner;

public class LmsUI {
	
	//instance variables to display which menu to be displayed
	boolean mainMenu;
	boolean authorMenu;
	boolean publisherMenu;
	boolean bookMenu;
	static boolean keepGoing =true;
	
	Lms lms;
	
	public LmsUI(){
		this.mainMenu = true;
		this.authorMenu = false;
		this.bookMenu = false;
		this.publisherMenu = false;
		this.lms = new Lms();
		
	}
	
	public void displayMainMenu() {
		System.out.println("");
		System.out.println("all publisher names, book names, and author names are case sensitive, and must be at least 4 characters long");
		System.out.println("publisher names, and author names must be unique, books can have the same name with different author/publisher");
		System.out.println("");
		System.out.println("Hello, welcome to the Library Management System");
		System.out.println("Please select one of the following:");
		System.out.println("1 for authors");
		System.out.println("2 for books");
		System.out.println("3 for publishers");
		System.out.println("4 to quit");
	}

	public void displayAuthorMenu() {
		System.out.println("");
		System.out.println("Please select one of the following options for Authors:");
		System.out.println("1 for create author");
		System.out.println("2 for read author list");
		System.out.println("3 for update author list");
		System.out.println("4 for delete author list");
	}

	public void displayPublisherMenu() {
		System.out.println("");
		System.out.println("Please select one of the following options for Publishers:");
		System.out.println("1 for create publisher");
		System.out.println("2 for read publisher list");
		System.out.println("3 for update publisher list");
		System.out.println("4 for delete publisher list");
	}

	public void displayBookMenu() {
		System.out.println("");
		System.out.println("Please select one of the following options for Books:");
		System.out.println("1 for create book");
		System.out.println("2 for read book list");
		System.out.println("3 for update book list");
		System.out.println("4 for delete book list");
	}
	
	//utility function to check input, input must not be null, and at least 4 characters
	public boolean isValidInput(String s){
		if (s == null) {
			System.out.println("failed to enter valid input");
			return false;
		}else if (s != null && s.length() < 4) {
			System.out.println("failed to enter valid input");
			return false;
		}else {
			return true;
			}
		}
	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		LmsUI ui = new LmsUI();
		
		ui.lms.initBooks(ui.lms.bookMap);
		ui.lms.initAuthors(ui.lms.authorMap);
		ui.lms.initPublishers(ui.lms.publisherMap);
		
			
			while(keepGoing) {

				if(ui.mainMenu) {
					ui.displayMainMenu();
				}
				
				String input = scan.nextLine();
				
				switch(input)
				{
					case "1":
						if(ui.mainMenu) {
							//navigates to author menu
							ui.mainMenu = false;
							ui.authorMenu = true;
							ui.displayAuthorMenu();
							break;
						}
						if(ui.authorMenu) {
							//creates author and goes to main menu
							ui.authorMenu = false;
							ui.mainMenu = true;
							
							System.out.println("In order to create an author, we will need the name of the author.");
							System.out.println("Please enter the author name....");
							String authorName = scan.nextLine();
							if(!ui.isValidInput(authorName)) {
								break;
							}
							
							ui.lms.createAuthor(authorName);
							break;
						}
						if(ui.bookMenu) {
							//creates book and goes to main menu
							ui.bookMenu = false;
							ui.mainMenu = true;
							System.out.println("In order to create a book, we will need the name of the book, publisher, and author ");
							System.out.println("Please enter the book name: ");
							String bookName = scan.nextLine();

							System.out.println("Please enter the publisher name: ");
							String publisherName = scan.nextLine();

							System.out.println("Please enter the author name: ");
							String authorName = scan.nextLine();
							//validates input
							if(!ui.isValidInput(bookName) || !ui.isValidInput(publisherName) || !ui.isValidInput(authorName) ) {
								break;
							}
							
							ui.lms.createBook(bookName,publisherName,authorName);
							break;
						}
						if(ui.publisherMenu) {
							//creates publisher and goes to main menu
							ui.publisherMenu = false;
							ui.mainMenu = true;
							
							System.out.println("In order to create a Publisher, we will need the name of the Publisher.");
							System.out.println("Please enter the publisher name....");
							String publisherName = scan.nextLine();
							//validates input
							if(!ui.isValidInput(publisherName)) {
								break;
							}
							
							ui.lms.createPublisher(publisherName);
							break;
						}
					case "2":
						if(ui.mainMenu) {
							//goes to book menu
							ui.mainMenu = false;
							ui.bookMenu = true;
							ui.displayBookMenu();
							break;
						}
						if(ui.authorMenu) {
							//read author selected and goes back to main menu
							ui.authorMenu = false;
							ui.mainMenu = true;
							ui.lms.readAuthors();
							break;
						}
						if(ui.bookMenu) {
							//read book selected and goes back to main menu
							ui.bookMenu = false;
							ui.mainMenu = true;
							ui.lms.readBooks();
							break;
						}
						if(ui.publisherMenu) {
							//read publisher selected and goes back to main menu
							ui.publisherMenu = false;
							ui.mainMenu = true;
							ui.lms.readPublishers();
							break;
						}
					case "3":
							if(ui.mainMenu) {
								//navigates to publishers menu
								ui.mainMenu = false;
								ui.publisherMenu = true;
								ui.displayPublisherMenu();
								break;
							}
							if(ui.authorMenu) {
								//update author selected and goes back to main menu
								ui.authorMenu = false;
								ui.mainMenu = true;
								System.out.println("In order to update an author name, we will need the name of the author.");
								System.out.println("Please enter the author name....");
								String originalName = scan.nextLine();
								System.out.println("What do you wish to change the author name to?");
								String modifiedName = scan.nextLine();
								//validates input
								if(!ui.isValidInput(originalName) || !ui.isValidInput(modifiedName) ) {
									break;
								}
								
								ui.lms.updateAuthor(originalName,modifiedName);
								break;
							}
							if(ui.bookMenu) {
								//update book selected and goes back to main menu
								ui.bookMenu = false;
								ui.mainMenu = true;
								
								System.out.println("In order to update a book, we will need the name of the book, publisher, and author ");
								System.out.println("Please enter the book name: ");
								String bookName = scan.nextLine();

								System.out.println("Please enter the publisher name: ");
								String publisherName = scan.nextLine();

								System.out.println("Please enter the author name: ");
								String authorName = scan.nextLine();
								
								System.out.println("What do you wish to change the book name to?");
								String modifiedName = scan.nextLine();
								//validates input
								if(!ui.isValidInput(bookName) || !ui.isValidInput(publisherName) || !ui.isValidInput(authorName) || !ui.isValidInput(modifiedName) ) {
									break;
								}
								
								ui.lms.updateBook(bookName,publisherName,authorName,modifiedName);
								break;
							}
							if(ui.publisherMenu) {
								//update publisher selected and goes back to main menu
								ui.publisherMenu = false;
								ui.mainMenu = true;
								
								System.out.println("In order to update a publisher, we will need the name of the publisher.");
								System.out.println("Please enter the publisher name....");
								String originalName = scan.nextLine();
								
								System.out.println("What do you wish to change the publisher name to?");
								String modifiedName = scan.nextLine();
								//validates input
								if(!ui.isValidInput(originalName)  || !ui.isValidInput(modifiedName)) {
									break;
								}
								
								
								ui.lms.updatePublisher(originalName,modifiedName);
								break;
							}
					case "4":
						if(ui.mainMenu) {
							//terminates program
							keepGoing = false;
							break;
						}
						if(ui.authorMenu) {
							//delete author selected and goes back to main menu
							ui.authorMenu = false;
							ui.mainMenu = true;
							System.out.println("In order to delete an author, we will need the name of the author.");
							System.out.println("Please enter the author name....");
							String authorName = scan.nextLine();
							//validates input
							if(!ui.isValidInput(authorName)) {
								break;
							}
							
							ui.lms.deleteAuthor(authorName);
							break;
						}
						if(ui.bookMenu) {
							//delete book selected and goes back to main menu
							ui.bookMenu = false;
							ui.mainMenu = true;
							
							System.out.println("In order to delete a book, we will need the name of the book, publisher, and author ");
							System.out.println("Please enter the book name: ");
							String bookName = scan.nextLine();

							System.out.println("Please enter the publisher name: ");
							String publisherName = scan.nextLine();

							System.out.println("Please enter the author name: ");
							String authorName = scan.nextLine();
							//validates input
							if(!ui.isValidInput(bookName)  || !ui.isValidInput(publisherName) || !ui.isValidInput(authorName)) {
								break;
							}

							ui.lms.deleteBook(bookName, publisherName, authorName);
							break;
						}
						if(ui.publisherMenu) {
							//delete publisher selected and goes back to main menu
							ui.publisherMenu = false;
							ui.mainMenu = true;
							System.out.println("In order to delete a Publisher, we will need the name of the Publisher.");
							System.out.println("Please enter the publisher name....");
							String publisherName = scan.nextLine();
							//validates input
							if(!ui.isValidInput(publisherName)) {
								break;
							}
							
							ui.lms.deletePublisher(publisherName);
							break;
						}
				default:
					System.out.println("invalid input, try again");
				
				}
	}
			System.out.println("turning off...");
	}
}
