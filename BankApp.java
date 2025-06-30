package Task_5;

import java.util.ArrayList;
import java.util.Scanner;

class Account {
    String accountHolderName;
    String accountNumber;
    double balance;
    ArrayList<String> transactionHistory = new ArrayList<String>();

    Account(String n, String no, double b) {
        this.accountHolderName = n;
        this.accountNumber = no;
        this.balance = b;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        balance = balance + amount;
        transactionHistory.add("Deposited : ₹" + amount);
        System.out.println("Balance after depositing " + amount + " : " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance = balance - amount;
            transactionHistory.add("Withdrawn : ₹" + amount);
            System.out.println("Balance after withdrawing " + amount + " : " + balance);
        } else {
            System.out.println("Balance not sufficient to withdraw... Current Balance = " + balance);
        }
    }

    public void getBalance() {
        System.out.println("Balance = ₹" + balance);
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("Transaction History:");
        for (String t : transactionHistory) {
            System.out.println(t);
        }
    }

    public void updateAccountHolderName(String newName) {
        this.accountHolderName = newName;
        System.out.println("Account holder name updated to: " + newName);
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(String name, String accNumber, double balance) {
        super(name, accNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        if (balance - amount >= 1000) {
            balance -= amount;
            transactionHistory.add("Withdrawn: ₹" + amount);
            System.out.println("Withdrawal successful. Remaining balance: ₹" + balance);
        } else {
            System.out.println("Withdrawal denied. Balance must not fall below ₹1000.");
        }
    }
}

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account holder name : ");
        String name = sc.nextLine();
        System.out.println("Enter Account number : ");
        String accnumber = sc.nextLine();
        System.out.println("Enter initial balance : ");
        double balance = sc.nextDouble();
        sc.nextLine();
        SavingsAccount account = new SavingsAccount(name, accnumber, balance);
        System.out.println("Account created for: " + account.accountHolderName);
        System.out.println("Account Number: " + account.accountNumber);
        System.out.println("Initial Balance: ₹" + account.balance);

        while (true) {
            System.out.println("***** Banking Management System *****");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Update Account Holder Name");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double wd = sc.nextDouble();
                    account.withdraw(wd);
                    break;
                case 3:
                    account.getBalance();
                    break;
                case 4:
                    account.showTransactionHistory();
                    break;
                case 5:
                    sc.nextLine();
                    System.out.print("Enter new account holder name: ");
                    String newName = sc.nextLine();
                    account.updateAccountHolderName(newName);
                    break;
                case 6:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
