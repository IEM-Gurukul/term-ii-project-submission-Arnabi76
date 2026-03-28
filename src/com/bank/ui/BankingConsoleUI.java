package com.bank.ui;

import com.bank.model.*;
import com.bank.observer.FraudAlertObserver;
import com.bank.observer.SmsNotificationObserver;
import com.bank.repository.*;
import com.bank.service.*;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BankingConsoleUI {
    private Scanner scanner = new Scanner(System.in);
    private CustomerRepository customerRepository = new CustomerRepository();
    private AccountRepository accountRepository = new AccountRepository();
    private TransactionRepository transactionRepository = new TransactionRepository();
    private AccountService accountService = new AccountService(accountRepository, transactionRepository);
    private TransactionService transactionService = new TransactionService(transactionRepository, accountService);
    private LoanService loanService = new LoanService(accountRepository);

    public BankingConsoleUI() {
        accountService.addObserver(new FraudAlertObserver());
        accountService.addObserver(new SmsNotificationObserver());
    }

    public void start() {
        System.out.println("=============================");
        System.out.println("   MINI BANKING SYSTEM");
        System.out.println("=============================");
        while (true) {
            System.out.println("\n1. Register Customer");
            System.out.println("2. Open Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. View Balance");
            System.out.println("7. Transaction History");
            System.out.println("8. Apply for Loan");
            System.out.println("9. Repay Loan");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": registerCustomer(); break;
                case "2": openAccount(); break;
                case "3": deposit(); break;
                case "4": withdraw(); break;
                case "5": transfer(); break;
                case "6": viewBalance(); break;
                case "7": viewHistory(); break;
                case "8": applyLoan(); break;
                case "9": repayLoan(); break;
                case "0": System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void registerCustomer() {
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Phone: "); String phone = scanner.nextLine();
        System.out.print("PIN: "); String pin = scanner.nextLine();
        String id = "CUST" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        Customer c = new Customer(id, name, email, phone, pin);
        customerRepository.save(c);
        System.out.println("✅ Customer registered! ID: " + id);
    }

    private void openAccount() {
        System.out.print("Customer ID: "); String custId = scanner.nextLine();
        if (!customerRepository.exists(custId)) { System.out.println("Customer not found."); return; }
        System.out.println("Account Type: 1. Savings  2. Current");
        System.out.print("Choose: "); String type = scanner.nextLine();
        System.out.print("Initial Deposit: ");
        double amount = 0;
        try { amount = Double.parseDouble(scanner.nextLine()); }
        catch (NumberFormatException e) { System.out.println("Invalid amount."); return; }
        try {
            AccountType at = type.equals("1") ? AccountType.SAVINGS : AccountType.CURRENT;
            Account acc = accountService.openAccount(custId, at, amount);
            System.out.println("✅ Account opened! ID: " + acc.getAccountId());
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private void deposit() {
        System.out.print("Account ID: "); String accId = scanner.nextLine();
        System.out.print("Amount: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            accountService.deposit(accId, amount);
            System.out.println("✅ Deposited Rs." + amount);
        } catch (NumberFormatException e) { System.out.println("Invalid amount.");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private void withdraw() {
        System.out.print("Account ID: "); String accId = scanner.nextLine();
        System.out.print("Amount: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            accountService.withdraw(accId, amount);
            System.out.println("✅ Withdrawn Rs." + amount);
        } catch (NumberFormatException e) { System.out.println("Invalid amount.");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private void transfer() {
        System.out.print("From Account ID: "); String from = scanner.nextLine();
        System.out.print("To Account ID: "); String to = scanner.nextLine();
        System.out.print("Amount: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            transactionService.transfer(from, to, amount);
            System.out.println("✅ Transfer successful!");
        } catch (NumberFormatException e) { System.out.println("Invalid amount.");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private void viewBalance() {
        System.out.print("Account ID: "); String accId = scanner.nextLine();
        try {
            Account acc = accountService.getAccount(accId);
            System.out.println("Balance: Rs." + acc.getBalance());
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private void viewHistory() {
        System.out.print("Account ID: "); String accId = scanner.nextLine();
        List<Transaction> history = transactionService.getTransactionHistory(accId);
        if (history.isEmpty()) { System.out.println("No transactions found."); return; }
        for (Transaction t : history) {
            System.out.println(t.getType() + " | Rs." + t.getAmount() + " | " + t.getTimestamp());
        }
    }

    private void applyLoan() {
        System.out.print("Customer ID: "); String custId = scanner.nextLine();
        if (!customerRepository.exists(custId)) { System.out.println("Customer not found."); return; }
        System.out.print("Loan Amount: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            LoanAccount loan = loanService.applyForLoan(custId, amount);
            System.out.println("✅ Loan approved! Loan Account ID: " + loan.getAccountId());
        } catch (NumberFormatException e) { System.out.println("Invalid amount.");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private void repayLoan() {
        System.out.print("Loan Account ID: "); String accId = scanner.nextLine();
        System.out.print("Repayment Amount: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            loanService.repayLoan(accId, amount);
            System.out.println("✅ Repayment of Rs." + amount + " successful!");
        } catch (NumberFormatException e) { System.out.println("Invalid amount.");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    public static void main(String[] args) {
        new BankingConsoleUI().start();
    }
} 
