package ApnaBank;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.imageio.spi.RegisterableService;

import Controller.JDBCProperties;

public class ApnaBank2 {
	// password = @1234567Tt#
	List<Account> accounts = new ArrayList<Account>();
	Base64.Encoder encoder = Base64.getEncoder();
	Base64.Decoder decoder = Base64.getDecoder();
	String accountName = "";
	String query = "";
	int accnoo = 0;
	int accountNo = 1000;
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	CallableStatement cst = null;
	int balance = 0;

	void choice() throws SQLException {
		con = JDBCProperties.getConnection();
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

	void homePage() throws SQLException {
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

	void options2(int choice) throws SQLException {
		switch (choice) {
		case 1:
			System.out.println("-----Deposit-----");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Amount to deposit");
			int amount = sc.nextInt();
			query = "insert into Transactions(accno,money) Values(?,?)";
			pst = con.prepareStatement(query);
			pst.setInt(1, accnoo);
			pst.setInt(2, amount);
			pst.executeUpdate();

			query = "Select balance from Accounts where accname = ?; ";
			pst = con.prepareStatement(query);
			pst.setString(1, accountName);
			rs = pst.executeQuery();
			int bal = 0;
			if (rs.next())
				bal = rs.getInt("balance");
			bal += amount;
			String updateQuery = "UPDATE Accounts set balance=? where  accno = ?;";
			pst = con.prepareStatement(updateQuery);
			pst.setInt(1, bal);
			pst.setInt(2, accnoo);
			pst.executeUpdate();

			System.out.println("Amount Deposited Succesfully:");

			this.homePage();
			break;
		case 2:
			System.out.println("-----Transfer-----");
			Scanner sck = new Scanner(System.in);
			System.out.println("Enter payee Account No: ");
			int acNo = sck.nextInt();
			boolean chec = acNo == accnoo;
			System.out.println(chec+"\t"+acNo+"\t"+accnoo);
			while (chec) {
				System.out.println("Enter payee Account No , Not Yours: ");
				acNo = sck.nextInt();
				chec = acNo == accnoo;

			}
			System.out.println("Enter amount: ");
			int amm = sck.nextInt();
			query = "insert into Transactions(accno,money) Values(?,?)";
			pst = con.prepareStatement(query);
			pst.setInt(1, accnoo);
			pst.setInt(2, -amm);
			pst.executeUpdate();

			query = "insert into Transactions(accno,money) Values(?,?)";
			pst = con.prepareStatement(query);
			pst.setInt(1, acNo);
			pst.setInt(2, amm);
			pst.executeUpdate();

			query = "select sum(money) as sum from  Transactions where accno =? group by accno ;";
			pst = con.prepareStatement(query);
			pst.setInt(1, accnoo);
			rs = pst.executeQuery();
			int bal11 = 0;
			if (rs.next())
				bal11 = rs.getInt("sum");

			query = "select sum(money) as sum from  Transactions where accno =? group by accno ;";
			pst.setInt(1, acNo);
			rs = pst.executeQuery();
			int bal22 = 0;
			if (rs.next())
				bal22 = rs.getInt("sum");

			System.out.println(bal11 + "\t" + bal22);
			query = "UPDATE Accounts set balance=? where  accno = ?;";
			pst = con.prepareStatement(query);
			pst.setInt(1, bal11);
			pst.setInt(2, accnoo);
			pst.executeUpdate();

			query = "UPDATE Accounts set balance=? where  accno = ?;";
			pst = con.prepareStatement(query);
			pst.setInt(1, bal22);
			pst.setInt(2, acNo);
			pst.executeUpdate();
			
			System.out.println("Amount Transfered Sucessfully");
			this.homePage();
			break;
		case 3:
			query = "select t.money , a.initialDepo , a.balance from Accounts a inner join Transactions t using (accno) where accno = ?;";
			pst = con.prepareStatement(query);
			pst.setInt(1, accnoo);
			rs = pst.executeQuery();
			int bal2 = 0;
			while (rs.next()) {
				bal2 = rs.getInt("money");
				if (bal2 > 0) {
					System.out.println("Rs. " + bal2 + ", credited to your account ");
				} else {
					System.out.println("Rs. " + bal2 + ", Debitted from your account ");
				}
				System.out.println("Initial Deposit = Rs. " + rs.getInt("initialDepo") + " , " + "Balance Amount = Rs. "
						+ rs.getInt("balance"));

			}
			this.homePage();
			break;
		case 4:

			query = "select accname,accno,phoneno,address,balance from Accounts where accname = ?;";
			pst = con.prepareStatement(query);
			pst.setString(1, accountName);
			rs = pst.executeQuery();
			if (rs.next()) {
				System.out.println("Accountholder name: " + "\t" + rs.getString("accname"));
				System.out.println("Accountholder Number: " + "\t" + rs.getInt("accno"));
				System.out.println("Accountholder Contact No: " + "\t" + rs.getLong("phoneno"));
				System.out.println("Accountholder Address: " + "\t" + rs.getString("address"));
				System.out.println("Accountholder Balance: " + "\t" + rs.getInt("balance"));
				this.homePage();
				break;
			}
		case 5:
			String query = "Select balance,initialDepo from Accounts where accname = ?;";
			pst = con.prepareStatement(query);
			pst.setString(1, accountName);
			rs = pst.executeQuery();
			if (rs.next()) {
				System.out.println("Account Balance: " + "\t" + rs.getInt("balance"));
				System.out.println("Initial Deposit: " + "\t" + rs.getInt("initialDepo"));
			}
			this.homePage();
			break;

		case 6:
			accountName="";
			accnoo=0;
			this.choice();
			break;

		default:
			break;
		}
	}

	void login() throws SQLException {
		System.out.println("-----Login-----");
		Scanner tc = new Scanner(System.in);
		System.out.println("Enter Username");
		String name = tc.nextLine();
		System.out.println("Enter Password");
		String pass = tc.nextLine();

		query = "Select uname , accpassword , accno,accname,phoneno,address,balance,initialDepo from Accounts;";
		pst = con.prepareStatement(query);
		rs = pst.executeQuery();
		while (rs.next()) {
			String uname = rs.getString("uname");
			String passd = rs.getString("accpassword");
			if (name.equals(uname)) {
				System.out.println("username matched");
				String decodedMsg = new String(decoder.decode(passd));
				if (pass.equals(decodedMsg)) {
					accnoo = rs.getInt("accno");
					accountName = rs.getString("accname");
					System.out.println("----Login Successfull-----"+ accnoo+"\t"+accountName);
					this.homePage();
				} else {
					System.out.println("Incorrect Password, Login Again");
					this.login();
				}
			}
		}
		rs.close();
		pst.close();

	}

	void options(int choice) throws SQLException {
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
			BigDecimal phone = BigDecimal.valueOf(Long.valueOf(contactNo));
			System.out.println(phone);
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
			balance += Integer.parseInt(initialDepo);
			accountNo++;
			query = "insert into Accounts values(?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setInt(1, accountNo);
			pst.setString(2, name);
			pst.setString(3, username);
			pst.setString(4, encodedMsg);
			pst.setBigDecimal(5, phone);
			pst.setString(6, address);
			pst.setInt(7, Integer.parseInt(initialDepo));
			pst.setInt(8, balance);
			System.out.println("prepared record inserted :" + pst.executeUpdate());
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

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ApnaBank2 apnaBank = new ApnaBank2();
		apnaBank.choice();

	}
}
