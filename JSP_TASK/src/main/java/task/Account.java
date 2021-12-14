package task;

public class Account {
	
	public static int acc_balance = 100000;
	
	public void withdraw(int amount) throws WithdrawException{
		if(amount>acc_balance)
			throw new WithdrawException("You cannot Withdraw amount more than your account balance!!!<br>Enter a valid Amount to be withdrawn");
		else 
			acc_balance=acc_balance-amount;
	}
	
	public int showBalance() {
		return acc_balance;
	}
}
