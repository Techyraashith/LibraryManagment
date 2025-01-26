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
        bookValidity.put("book1", 3);
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
                	
                	//Admin side.
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

	public static void custLogin() { //Method starts
        
        boolean userMenu = true;
        
    	System.out.println("Enter your username:");
        String custName = scan.next();
        
        while (userMenu) {
        	
        	System.out.println("\n1. Borrow Book\n2. Return Book\n3. View Borrowed Details\n4. View Books\n5. exit\n");
        	
            System.out.println("\nSelect an option: \n");
            int choice = scan.nextInt();
            
            switch (choice) {
                case 1:
                	
                    System.out.println("Welcome, " + custName);
                	
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
                        System.out.println("\nNo borrowed records found for " + custName + ".\n");
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
		
	    boolean recordFound = custBorrowRecords.removeIf(record -> 
        record.getUsername().equals(returnCustName) && record.getBookName().equals(userBookname));
	    
	    if(recordFound) {
	    	System.out.println("\nThe Name of Customer" +returnCustName+" and the Name of Book "+bookName+" is Perfectly Matched\n"); 
	    	
	    	bookCount.put(userBookname, bookCount.get(userBookname)+1);
	    	
	        System.out.println("\nThe book " + userBookname + " has been successfully returned. Thank you!\n");
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




