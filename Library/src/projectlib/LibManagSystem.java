package projectlib;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BorrowRecord {
    private String custName;
    @Override
	public String toString() {
		return "Borrow Record Customer Name = " + custName + ", Book Name = " + bookName + ", Borrow Date = " + borrowDate;
	}

	private String bookName;
    private LocalDateTime borrowDate;

    public BorrowRecord(String custName, String bookName, LocalDateTime borrowDate) {
        this.custName = custName;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
    }

    public String getUsername() {
        return custName;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }
}




public class LibManagSystem {
	
    static Map<String, Integer> bookCount = new HashMap<>();
    static Map<String, Integer> bookValidity = new HashMap<>();
    static ArrayList<BorrowRecord> custBorrowRecords = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        bookCount.put("book1", 1);
        bookCount.put("book2", 2);
        bookCount.put("book3", 3);
        bookValidity.put("book1", 2);
        bookValidity.put("book2", 4);
        bookValidity.put("book3", 5);

        boolean system = true;
        while (system) {
            System.out.println("--------------------------------------");
            System.out.println("1. Customer \n2. Admin \n3. Logout From the System");
            System.out.println("Please select from the above options:");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    custLogin();
                    break;
                case 2:
                	
                	AdminLogin();
                    break;
                    
                case 3:
                	 system = false;
                	 System.out.println("Logging out.....");
                	 break;
                	
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    public static void AdminLogin(){

        
			System.out.println("\nplease enter the password\n");
			int password=scan.nextInt();
			if(password==123) {

                System.out.println("\nwelcome admin\n");
				boolean adminrole=true;
				while(adminrole) {
				System.out.println("\n1.view books\n2.editbooks\n3.Exit\n");
				int select=scan.nextInt();


				switch(select) {
				case 1:
					bookCount.entrySet().forEach(e->{
						System.out.println(e.getKey()+" -> "+e.getValue()+"pieces -> "+bookValidity.get(e.getKey())+"days");
					});
					break;
				case 2:
					editbooks();
					break;
				case 3:
					adminrole = false;
                    System.out.println("\nExit from the menu....\n");

                default:
                System.out.println("\nInvalid option. Please try again.\n");

				}
		
		    }//while end
	    }
			else {
                System.out.println("\npassword is incorrect\n");
	    }
    }


    private static void editbooks() {
		// TODO Auto-generated method stub
		System.out.println("1.add\n2.delete\n3.update");
		System.out.println("select from above option");
		int edit=scan.nextInt();


		switch(edit) {
		case 1:

            Add();
			break;

		case 2:
            
            Delete();
			break;
            
		case 3:

            Update();
			break;
		}
		
	}

    public static void Add(){
        System.out.println("Enter book name for add :");
			String addBook=scan.next();
			if(bookCount.containsKey(addBook)) {//allow only when book is not exist
				System.out.println("the book '"+addBook+"' is already exist");
				return;
			}
			System.out.println("Enter book count : ");
			int addCount=scan.nextInt();
			System.out.println("Enter book validity :");
			int addValidity=scan.nextInt();
			bookCount.put(addBook, addCount);
			bookValidity.put(addBook, addValidity);
			System.out.println("the book '"+addBook+" Is addeed successfullly....!");
			
    }

    public static void Delete(){

        System.out.println("Enter book name for delete :");
        String delbook=scan.next();
        if(bookCount.containsKey(delbook)) {
            bookCount.remove(delbook);
            bookValidity.remove(delbook);
            System.out.println("THE BOOK '"+delbook+"' IS DELETED");
            return;
        }
        System.out.println("the book '"+delbook+"' is not exist");
        System.out.println("YOU CAN'T DELETE '"+delbook+"' IS NOT EXIST");

    }

    public static void Update(){

        System.out.println("Enter book name for update :");
			String updBook=scan.next();
			
			if(bookCount.containsKey(updBook)) {//allow only when book is  exist
				System.out.println("Enter book count : ");
				int updCount=scan.nextInt();
				System.out.println("Enter book validity :");
				int updValidity=scan.nextInt();
				bookCount.put(updBook, updCount);
				bookValidity.put(updBook, updValidity);
				System.out.println("THE BOOK '"+updBook+"' IS UPDATED");
				return;
			}
			System.out.println("the book '"+updBook+"' is not exist");
			System.out.println("YOU CAN'T UPDATE '"+updBook+"' IS NOT EXIST");
    }


	public static void custLogin() { //Method starts
        
        boolean userMenu = true;
        
    	System.out.println("Enter your username:");
        String custName = scan.next();
        System.out.println("Welcome, " + custName);
        
        while (userMenu) {
        	
        	System.out.println("\n1. Borrow Book\n2. Return Book\n3. View Borrowed Details\n4. View Books\n5. Exit\n");
        	
            System.out.println("\nSelect an option: \n");
            int choice = scan.nextInt();
            
            switch (choice) {
                case 1:
                	
                	System.out.println("\nPlease enter the book name: \n");           
                	String bname = scan.next();
                    borrowBook(custName,bname);
                    break;
                    
                case 2:
         
                    System.out.println("Welcome Again, " + custName);
                	
                    System.out.print("\nEnter the name of the book you return: \n");          
                	String bname1 = scan.next();
                    returnBook(custName,bname1);
                    break;
                    
                case 3:
                	
                    boolean recordFound = false;

                    for (int i = 0; i < custBorrowRecords.size(); i++) {
                        BorrowRecord record = custBorrowRecords.get(i);
                        if (record.getUsername().equals(custName)) {
                            System.out.println(record);
                            recordFound = true;
                        }
                    }

                    if (!recordFound) {
                        System.out.println("\nNo borrowed records founded\n");
                    }
                    break;
                    
                    
                case 4:
                    System.out.println("\nAvailable Books:\n");
                    for (Map.Entry<String, Integer> entry : bookCount.entrySet()) {
                        String bookName = entry.getKey();
                        int availableCopies = entry.getValue();
                        System.out.println("Book Name: " + bookName + ", Available Copies: " + availableCopies);
                    }
                    break;

                	
                    
                case 5:
                    System.out.println("\nExit from the menu...\n");
                    userMenu = false;
                    break;
                    
                default:
                    System.out.println("\nInvalid option. Please try again.\n");
            }
        }
	
	
	} //Method end

	private static void returnBook(String returnCustName,String bookName) {
		// TODO Auto-generated method stub
			
		 	String userBookname = bookName;
		int fine = 0;

        int difference = LocalDateTime.now().compareTo(LocalDateTime.of(2025,1,25,0,0));
        
        
		boolean recordFound = custBorrowRecords.stream().anyMatch(record -> 
        record.getUsername().equals(returnCustName) && record.getBookName().equals(userBookname));

	    
	    if(recordFound) {
	    	

	    	if(difference > bookValidity.get(bookName)) {
	    		fine = difference * 20;
	    		
				System.out.println("You must to pay the Fine Amount to return the book: "+fine);
				
				System.out.println("Pay Fine Amount: ");
				int f = scan.nextInt();
				
				if(fine == f) {
					
					System.out.println("You Pay fine amount sucessfully.");
					
				    custBorrowRecords.removeIf(record -> 
			        record.getUsername().equals(returnCustName) && record.getBookName().equals(userBookname));
				    
			    	bookCount.put(userBookname, bookCount.get(userBookname)+1);


					
					System.out.println("The book " + userBookname + " has been successfully returned. Thank you!");
					
				}
				
				else {
					System.out.print("Please Pay the Fine Amount.");
				}
				
				
				
	    	
	    }
	    	
	    	else {
	    		  custBorrowRecords.removeIf(record -> 
			        record.getUsername().equals(returnCustName) && record.getBookName().equals(userBookname));
				    
					System.out.println("The book " + userBookname + " has been successfully returned. Thank you!");

	    		  
	    		  bookCount.put(userBookname, bookCount.get(userBookname)+1);
			    	
	    	}
	  	}
	    
	    
	    else {
	        System.out.println("\nNo record found for " + returnCustName + " borrowing the book " + userBookname + ".\n");
	       }
	}


	private static void borrowBook(String custName,String bookName) {
		// TODO Auto-generated method stub
				
		if(bookCount.containsKey(bookName)) {
			int copiesAvailable = bookCount.get(bookName);
			
			
			if(copiesAvailable > 0) {
					            
				bookCount.put(bookName, copiesAvailable - 1);

				custBorrowRecords.add(new BorrowRecord(custName, bookName, LocalDateTime.now()));
				
				System.out.println("\nThe Book "+bookName+" is Borrowed Sucessfully in This Date "+LocalDateTime.now()+".\n");

			}
			
			else {
				System.out.println("\nSorry The Book: "+bookName+" is Currently Not Available.\n");
			}
		}
		
		else {
			System.out.println("\nSorry The Given Book:"+bookName+" is Not Here.\n");
		}
	}
}