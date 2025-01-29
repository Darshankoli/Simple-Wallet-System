import java.util.ArrayList;
import java.util.Scanner;

class Wallet {
    private double balance;
    private double dailyLimit;
    private ArrayList<String> transactions;

    public Wallet(double dailyLimit) {
        this.balance = 0.0;
        this.dailyLimit = dailyLimit;
        this.transactions = new ArrayList<>();
    }

    public void addMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("+ ₹" + amount + " (Added)");
            System.out.println("₹" + amount + " added successfully.");
        } else {
            System.out.println("Invalid amount! Enter a positive value.");
        }
    }

    public void removeMoney(double amount) {
        if (amount > 0 && amount <= balance) {
            if (amount > dailyLimit) {
                System.out.println("Transaction failed! Daily limit exceeded (₹" + dailyLimit + ")");
                return;
            }
            balance -= amount;
            transactions.add("- ₹" + amount + " (Removed)");
            System.out.println("₹" + amount + " removed successfully.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid amount! Enter a positive value.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public void showTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("\n=== Transaction History ===");
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    public void resetWallet() {
        balance = 0.0;
        transactions.clear();
        System.out.println("Wallet has been reset. Balance is now ₹0.00.");
    }
}

public class MoneyWallet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Authentication
        final int PIN = 1234;
        System.out.print("Enter your 4-digit PIN to access the wallet: ");
        int enteredPin = scanner.nextInt();

        if (enteredPin != PIN) {
            System.out.println("Incorrect PIN! Access denied.");
            return;
        }

        System.out.println("Login successful! Welcome to your Money Wallet.");

        System.out.print("Set your daily spending limit: ₹");
        double dailyLimit = scanner.nextDouble();

        Wallet wallet = new Wallet(dailyLimit);

        while (true) {
            System.out.println("\n=== Money Wallet Menu ===");
            System.out.println("1. Add Money");
            System.out.println("2. Remove Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Reset Wallet");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to add: ₹");
                    double addAmount = scanner.nextDouble();
                    wallet.addMoney(addAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to remove: ₹");
                    double removeAmount = scanner.nextDouble();
                    wallet.removeMoney(removeAmount);
                    break;
                case 3:
                    wallet.checkBalance();
                    break;
                case 4:
                    wallet.showTransactionHistory();
                    break;
                case 5:
                    wallet.resetWallet();
                    break;
                case 6:
                    System.out.println("Exiting... Thank you for using Money Wallet!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}
