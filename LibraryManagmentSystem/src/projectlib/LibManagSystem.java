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
        while (userMenu) {
        	
        	System.out.println("1. Borrow Book\n2. Return Book\n3. View Borrowed Details\n4. exit");
        	
            System.out.println("\nSelect an option:");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                	
                	System.out.println("Enter your username:");
                    String custName = scan.next();

                    System.out.println("Welcome, " + custName);
                	
                	System.out.println("Please enter the book name: ");           
                	String bname = scan.next();
                    borrowBook(custName,bname);
                    break;
                    
                case 2:
                	
                	System.out.println("Enter your username:");
                    String custName1 = scan.next();

                    System.out.println("Welcome, " + custName1);
                	
                    System.out.print("Enter the name of the book you return: ");          
                	String bname1 = scan.next();
                    returnBook(custName1,bname1);
                    break;
                    
                case 3:
                	System.out.println("Enter your username:");
                    String custName2 = scan.next();

                    System.out.println("Welcome, " + custName2);

                    boolean recordFound = false;

                    for (int i = 0; i < custBorrowRecords.size(); i++) {
                        BorrowRecord record = custBorrowRecords.get(i);
                        if (record.getUsername().equals(custName2)) {
                            System.out.println(record);
                            recordFound = true;
                        }
                    }

                    if (!recordFound) {
                        System.out.println("No borrowed records found for " + custName2 + ".");
                    }
                    break;
                    
                case 4:
                    System.out.println("Exit from the menu...");
                    userMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
	
	
	} //Method end

	private static void returnBook(String returnCustName,String bookName) {
		// TODO Auto-generated method stub
				
		String userBookname = bookName;
		
	    boolean recordFound = custBorrowRecords.removeIf(record -> 
        record.getUsername().equals(returnCustName) && record.getBookName().equals(userBookname));
	    
	    if(recordFound) {
	    	System.out.println("The Name of Customer" +returnCustName+" and the Name of Book "+bookName+" is Perfectly Matched"); 
	    	
	    	bookCount.put(userBookname, bookCount.get(userBookname)+1);
	    	
	        System.out.println("The book " + userBookname + " has been successfully returned. Thank you!");
	    }
	    
	    else {
	        System.out.println("No record found for " + returnCustName + " borrowing the book " + userBookname + ".");

	    }
	}

	private static void borrowBook(String custName,String bookName) {
		// TODO Auto-generated method stub
				
		if(bookCount.containsKey(bookName)) {
			int copiesAvailable = bookCount.get(bookName);
			
			
			if(copiesAvailable > 0) {
					            
				bookCount.put(bookName, copiesAvailable - 1);

				custBorrowRecords.add(new BorrowRecord(custName, bookName, LocalDateTime.now()));
				
				System.out.println("The Book "+bookName+" is Borrowed Sucessfully in This Date "+LocalDateTime.now()+".");

			}
			
			else {
				System.out.println("Sorry The Book: "+bookName+" is Currently Not Available.");
			}
		}
		
		else {
			System.out.println("Sorry The Given Book:"+bookName+" is Not Here.");
		}
	}
}




