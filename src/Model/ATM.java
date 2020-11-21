package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Repositories.BankPlan;

public class ATM implements BankPlan{

	ArrayList <User> users = new ArrayList<>();
	int User_ID = 0;
	//change connection
	static final String DB_URL 	= "jdbc:mysql://127.0.0.1/cfmatm";
	static final String USER 	= "root";
	static final String PASS 	= "root";
	static Connection connection;

	static Scanner sc = new Scanner(System.in);

	@Override
	public void login() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Make the connection to the DB server and DB by passing in server credentials
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//would you like to create an account? 
// or do you have a login?
		System.out.println("Login");
		System.out.println("Enter Debit/Credit Card Number:");
		String cc = sc.next();
		System.out.println("Enter Pin:");
		String pin = sc.next();
		try {
			// Write the SQL with ? as placeholders for user or code variables
			String sql = "SELECT User_id, CC_number, Pin FROM User WHERE CC_number = ? AND Pin = ?";

			// Create a PreparedStatement object so it look for the ? placeholders
			PreparedStatement stmt = connection.prepareStatement(sql);

			// Replace the ? with the correct number and type of variable (e.g. String)
			stmt.setString(1, cc);
			stmt.setString(2, pin);

			// Execute the query based on the PreparedStatement object, do not pass the sql
			// variable
			ResultSet results = stmt.executeQuery();

			// Loop through the results of the query
			while (results.next()) {
				System.out.println(User_ID = results.getInt("User_id"));
				System.out.println("Card Number = " + results.getString("CC_number"));
				System.out.println("Pin = " + results.getString("Pin"));
			}
			// Credentials are valid so return TRUE
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (User_ID >= 0) {
			System.out.println("Congratulations you are logged in!");
			menu();
		} 
		else {
			System.out.println("Who are you? I don't recognize those credentials.");
			//create User? 
			login(); // or do until login = true, if logged in, boolean = true
		}
		
	}

	@Override
	public void menu() {
		main: do {
			System.out.println("       MENU      ");
			System.out.println("1 ] Create Account");
			System.out.println("2 ] Deposit");
			System.out.println("3 ] View Account Info");
			System.out.println("4 ] Transfer");
			System.out.println("5 ] Edit Pin");
			System.out.println("6 ] Transfer");
			System.out.println("6 ] Transfer and delete an Account"); 
			System.out.println("Enter [0] to exit, which option?");
			int opt = sc.nextInt();
			switch (opt) {
			case 1:
				createAccount();
				break;
			case 2:
				createDeposit();
				break;
			case 3:
				readAccountInfo();
				break;
			case 4:
				updateTransfer();
				break;
			case 5:
				updatePin();
				break;
			case 6:
				deleteWithdrawal();
				break;
			case 7:
				transferAndDeleteAnAccount();
				break;
			case 8:
				readTransactions();
				break;
			case 0:
				System.out.println("Exiting application.");			
				break main;
			default:
				System.out.println("Not a valid entry.");
			}
		} while (true);
		System.out.println("Thank you for using our application!");
	}
	
	@Override
	public void createAccount() {
		System.out.println("Create Account");
		String sql = "INSERT INTO Bank_accnt (User_ID, Accnt_type, Accnt_balance) VALUES (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			System.out.print("Account Type (C for Checking, S for savings):");
			String type = sc.next();
			
			stmt.setInt(1, User_ID); 
			stmt.setString(2, type);
			stmt.setInt(3, 0);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createDeposit() {
		//Need to change account balance to 
		System.out.println(" Deposit ");
		String sql = "INSERT INTO transaction (User_ID, Account_ID, sign, Amount_trans) VALUES (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			System.out.print("Enter Account ID:");
			int Account_ID = sc.nextInt();
			System.out.print("Enter A for deposit, M for withdrawal:");
			String sign = sc.next();
			System.out.print("Enter Amount:");
			int amount = sc.nextInt();


			stmt.setInt(1, User_ID); // need to use sql stmt to get the max food_id and add 1
			stmt.setInt(2, Account_ID);
			stmt.setString(3, sign);
			stmt.setInt(4, amount);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void readAccountInfo() {
		//array list ?
		System.out.println("  List Account Information ");
		System.out.println("==================");
		System.out.println("\nID\tCard Number\t\tPin\n\tFirst Name\tLast Name\tPhone\tEmail\t");
		String sql = "SELECT User_ID, CC_number, First_name, Last_name, phone, email FROM User where User_ID = ";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, User_ID);
			stmt.execute();
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				System.out.print(results.getInt("food_id"));
				System.out.print("\t" + results.getString("food_name"));
				System.out.print("\t" + results.getInt("food_calories"));
				System.out.println("\t" + results.getString("food_category") + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void readTransactions() {
		System.out.println("  List Transactions ");
		System.out.println("\nUser ID\tAccount ID\tDeposit(A) or Withdrawal(M)\tAmount\n");
		String sql = "SELECT Transaction_ID, User_ID, Account_ID, sign, Amount_trans FROM Transaction order by Transaction_ID";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				System.out.print(results.getInt("Transaction_ID"));
				System.out.print("\t" + results.getInt("User_ID,"));
				System.out.print("\t" + results.getInt("Account_ID"));
				System.out.println("\t" + results.getString("sign") + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePin() {
		System.out.println(" Update Pin ");
		try {
			// GET LIST
			String sql = "SELECT food_id, food_name, food_calories, food_category FROM food";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				System.out.print(results.getInt("food_id") + "] ");
				System.out.print("\t" + results.getString("food_name"));
				System.out.print("\t" + results.getInt("food_calories") + "] ");
				System.out.print("\t" + results.getString("food_category") + "\n");
			}
			System.out.println("Which food item to update?");
			int opt = sc.nextInt();
			sc.nextLine();
			// GET INDIVIDUAL RECORD
			sql = "SELECT food_id, food_name, food_calories, food_category FROM food WHERE food_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, opt);
			results = stmt.executeQuery();
			while (results.next()) {
				// Allow the user to make changes
				System.out.println("FOOD NAME [" + results.getString("food_name") + "]: ");
				String name = sc.next();
				System.out.println("CALORIES [" + results.getInt("food_calories") + "]: ");
				Double calories = sc.nextDouble();
				System.out.println("FOOD CATEGORY [" + results.getString("food_category") + "]: ");
				String cat = sc.next(); //funny problem, nextLine would not allow user to input values
				//sc.nextLine();
				int id = results.getInt("food_id");
				// Update the table
				sql = "UPDATE food SET food_name = ?, food_calories = ?, food_category = ? WHERE food_id = ?";
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, name);
				stmt.setDouble(2, calories);
				stmt.setString(3, cat);
				stmt.setInt(4, id);
				stmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateTransfer() {
		
	}

	@Override
	public void deleteWithdrawal() {
		
		
	}
	
	@Override
	public void payBill() {
		
	}

	@Override
	public void transferAndDeleteAnAccount() {
		
		
	}
	
	@Override
	public void search() {
		System.out.println("Search for an Account\nEnter C or S to find Checking or Savings Account");
		String sql = "SELECT Transaction_ID, User_ID, Account_ID, sign, Amount_trans FROM Transaction order by Transaction_ID";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				System.out.print(results.getInt("Transaction_ID"));
				System.out.print("\t" + results.getInt("User_ID,"));
				System.out.print("\t" + results.getInt("Account_ID"));
				System.out.println("\t" + results.getString("sign") + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}



//@Override 
////utilizing abstraction and overriding from its "has-a" type of relationship
////it is instructing the compiler to ignore the interface and run the derived methods
//public void createUser() {
//	System.out.println("Create User");
//	String sql = "INSERT INTO User (CC_number, Pin, First_name, Last_name, Phone, Email) VALUES (?,?,?,??,?,?)";
//	try {
//		PreparedStatement stmt = connection.prepareStatement(sql);
//		System.out.print("Enter Card Number:");
//		int cc = sc.nextInt();
//		// Next step would have been to generate a random card number for them
//		System.out.print("Pin:");
//		int pin = sc.nextInt();
//		System.out.print("First Name:");
//		String fname = sc.next();
//		System.out.print("Last Name:");
//		String lname = sc.next();
//		System.out.print("Phone Number:");
//		int phone = sc.nextInt();
//		System.out.print("Email:");
//		String Email = sc.next();
//
//		stmt.setInt(1, cc); // need to use sql stmt to get the max food_id and add 1
//		stmt.setInt(2, pin);
//		stmt.setString(3, fname);
//		stmt.setString(4, lname);
//		stmt.setInt(5, phone);
//		stmt.setString(6, lname);
//		stmt.execute();
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//}
