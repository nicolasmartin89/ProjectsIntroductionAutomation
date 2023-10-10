package com.globant.Services;

import com.globant.Entities.Bank;
import com.globant.Entities.UserAccount;

import java.util.List;
import java.util.Scanner;

public class BankingApp {
    private static Bank bank;
    private static UserAccount currentUser;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        bank = new Bank("admin", "adminPassword");

        while (true) {
            System.out.println("Welcome to the Banking App");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                loginAsAdmin();
            } else if (choice == 2) {
                loginAsUser();
            } else if (choice == 3) {
                displayUserAccounts();
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loginAsAdmin() {
        System.out.print("Enter admin username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String adminPassword = scanner.nextLine();

        if (adminUsername.equals(bank.getAdminAccount().getUsername()) &&
                adminPassword.equals(bank.getAdminAccount().getPassword())) {
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Create User Account");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                createUserAccount();
            } else if (choice == 2) {
                displayUserAccounts();
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createUserAccount() {
        System.out.print("Enter username for the new user account: ");
        String username = scanner.nextLine();
        System.out.print("Enter password for the new user account: ");
        String password = scanner.nextLine();
        UserAccount newUser = new UserAccount(username, password);
        bank.addUserAccount(newUser);
        System.out.println("User account created successfully.");
    }

    private static void loginAsUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        currentUser = bank.getUserAccount(username);

        if (currentUser != null) {
            userMenu();
        } else {
            System.out.println("Invalid user credentials.");
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("User Menu");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    displayUserAccounts();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void deposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        currentUser.deposit(amount);
        System.out.println("Deposit successful. New balance: $" + currentUser.getBalance());
    }

    private static void withdraw() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        currentUser.withdraw(amount);
        System.out.println("Withdrawal successful. New balance: $" + currentUser.getBalance());
    }

    private static void transfer() {
        System.out.print("Enter recipient username: ");
        String recipientUsername = scanner.nextLine();
        UserAccount recipient = bank.getUserAccount(recipientUsername);

        if (recipient == null) {
            System.out.println("Recipient not found.");
            return;
        }

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        if (currentUser.getBalance() >= amount) {
            bank.transfer(currentUser, recipient, amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds for the transfer.");
        }
    }

    private static void checkBalance() {
        System.out.println("Your current balance: $" + currentUser.getBalance());
    }

    private static void displayUserAccounts() {
        System.out.println("Admin Accounts:");
        List<UserAccount> adminAccounts = bank.getAdminAccounts();
        for (UserAccount admin : adminAccounts) {
            System.out.println("Username: " + admin.getUsername() + ", Password: " + admin.getPassword());
        }

        System.out.println("\nUser Accounts:");
        List<UserAccount> userAccounts = bank.getUserAccounts();
        for (UserAccount user : userAccounts) {
            System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword() + ", Balance: $" + user.getBalance());
        }
    }
}
