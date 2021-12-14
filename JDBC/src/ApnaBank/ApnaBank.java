package ApnaBank;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class ApnaBank {
	// password = @1234567Tt#
	List<Account> accounts = new ArrayList<Account>();
	Base64.Encoder encoder = Base64.getEncoder();
	Base64.Decoder decoder = Base64.getDecoder();
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String outputFile = "resources/accounts.db";
	String inputFile = "resources/accounts.db";
	Account register;
	int accountNo = 1000;
	

	void choice() {
		System.out.println("--------------------");
		System.out.println("Welcome To Apna Bank");
		System.out.println("--------------------");
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.println("3. Update Accounts");
		System.out.println("4. Exit");
		System.out.println(" ");
		System.out.println("Enter your choice by number :");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		this.options(choice);
	}

	void homePage() {
		System.out.println("--------");
		System.out.println("WELCOME");
		System.out.println("--------");
		System.out.println("1. Deposit");
		System.out.println("2. Transfer");
		System.out.println("3. Last Transactions");
		System.out.println("4. User Information");
		System.out.println("5  Show Balance");
		System.out.println("6. Logout");
		System.out.println(" ");
		System.out.println("Enter your choice by number :");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		this.options2(choice);
	}

	void options2(int choice) {
		switch (choice) {
		case 1:
			System.out.println("-----Deposit-----");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Amount to deposit");
			int amount = sc.nextInt();
			register.depositAmount(amount);
			System.out.println("Deposited sucessfully" + register.transaction);
			
			this.homePage();
			break;
		case 2:			
			System.out.println("-----Transfer-----");
			Scanner sck = new Scanner(System.in);
			System.out.println("Enter payee Account No: ");
			int acNo = sck.nextInt();
			boolean chec = acNo == register.accountNo;
			while (chec) {
				System.out.println("Enter payee Account No , Not Yours: ");
				acNo = sck.nextInt();
				chec = acNo == register.accountNo;

			}
			System.out.println("Enter amount: ");
			int amm = sck.nextInt();
			
			for (Account regis : accounts) {
				boolean c = acNo == regis.accountNo;
				if (acNo == regis.accountNo) {
					regis.transaction.add(amm);
					register.transaction.add(-amm);
					System.out.println("Amount Transfered Successfully");
					this.homePage();
					break;
				}

			}

			break;
		case 3:
			System.out.println("3");
			int temp = 0;
			for (int i = register.transaction.size() - 1; i >= 0; i--) {
				temp = temp + register.transaction.get(i);
				if (register.transaction.get(i) > 0) {
					System.out.println("Rs. " + register.transaction.get(i) + ", credited to your account ");
				} else {
					System.out.println("Rs. " + register.transaction.get(i) + ", Debitted from your account ");
				}
			}

			System.out.println("Initial deposit - " + register.getInitialDepo() + ", Balance Amount: " + temp);
			this.homePage();
			break;
		case 4:
			System.out.println("Accountholder name: " + register.getName());
			System.out.println("Accountholder Nuber: " + register.accountNo);
			System.out.println("Accountholder Contact No: " + register.getContactNo());
			System.out.println("Accountholder Address: " + register.getAddress());
			this.homePage();
			break;
		case 5:
			int tempp = 0;
			for (int i = register.transaction.size() - 1; i >= 0; i--) {
				tempp = tempp + register.transaction.get(i);
			}
			System.out.println("Balance Amount: " + tempp);
			this.homePage();
			break;
		case 6:
			this.choice();
			break;

		default:
			break;
		}
	}

	void login() {
		System.out.println("-----Login-----");
		Scanner tc = new Scanner(System.in);
		System.out.println("Enter Username");
		String name = tc.nextLine();
		System.out.println("Enter Password");
		String pass = tc.nextLine();

		for (Account regi : accounts) {
			if (name.equals(regi.getName())) {
				System.out.println("username matched");
				String decodedMsg = new String(decoder.decode(regi.getPassword()));
				if (pass.equals(decodedMsg)) {
					System.out.println("----Login Successfull-----");
					register = regi;
					this.homePage();
//					break;
				}else {
						System.out.println("Incorrect Password, Login Again");
						this.login();
				}
			}
		}		
	}

	void options(int choice) {
		switch (choice) {
		case 1:
			System.out.println("-----Registering An account-----");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Name");
			String name = sc.nextLine();
			System.out.println("Enter Address");
			String address = sc.nextLine();
			System.out.println("Enter Contact Number");
			String contactNo = sc.nextLine();
			if (!(contactNo.length() >= 10)) {
				while (!(contactNo.length() >= 10)) {
					System.out.println("Enter valid phone Number");
					contactNo = sc.nextLine();

				}
			}
			System.out.println("Set Username");
			String username = sc.nextLine();
			if (!(username.length() >= 4)) {
				while (!(username.length() >= 4)) {
					System.out.println("Enter minmum 4 character username");
					username = sc.nextLine();

				}
			}
			System.out.println("Enter Password");
			String password = sc.nextLine();
			boolean check = Pattern
					.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,14}$", password);
			System.out.println(check);
			while (!check) {
				System.out.println("Set a password (minimum 8 chars; minimum 1 digit, 1 lowercase, 1 \r\n"
						+ "uppercase, 1 special character[!@#$%^&*_]) :");
				password = sc.nextLine();
				check = Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,14}$",
						password);
			}
			String encodedMsg = encoder.encodeToString(password.getBytes());

			System.out.println("Enter Initial Deposit");
			String initialDepo = sc.nextLine();
			accountNo++;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(outputFile));
				register = new Account(accountNo, name, address, username, encodedMsg, contactNo, initialDepo);
				System.out.println(register);
				accounts.add(register);
				for (Account acc : accounts) {
					oos.writeObject(acc);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (oos != null)
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

			this.choice();
			break;
		case 2:
			this.login();
			break;
		case 3:
			System.out.println("3");
			break;
		case 4:
			System.out.println("4");
			break;

		default:
			break;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApnaBank apnaBank = new ApnaBank();
		apnaBank.choice();
		
	}
}
