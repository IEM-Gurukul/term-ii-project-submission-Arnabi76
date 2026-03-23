 package com.bank.service;

import com.bank.exception.*;
import com.bank.model.*;
import com.bank.observer.BankObserver;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private List<BankObserver> observers = new ArrayList<>();

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void addObserver(BankObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String accountId, double amount, String type) {
        for (BankObserver o : observers) {
            o.update(accountId, amount, type);
        }
    }

    public Account openAccount(String customerId, AccountType type, double initialBalance)
            throws InvalidAmountException {
        if (initialBalance < 0) throw new InvalidAmountException("Initial balance cannot be negative");
        String accountId = "ACC" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        Account account;
        switch (type) {
            case SAVINGS: account = new SavingsAccount(accountId, customerId, initialBalance); break;
            case CURRENT: account = new CurrentAccount(accountId, customerId, initialBalance); break;
            case LOAN: account = new LoanAccount(accountId, customerId, initialBalance); break;
            default: throw new InvalidAmountException("Invalid account type");
        }
        accountRepository.save(account);
        return account;
    }

    public void deposit(String accountId, double amount)
            throws AccountNotFoundException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be positive");
        Account account = accountRepository.findById(accountId);
        if (account == null) throw new AccountNotFoundException("Account not found: " + accountId);
        try {
            account.deposit(amount);
        } catch (Exception e) {
            throw new InvalidAmountException(e.getMessage());
        }
        Transaction t = new Transaction("TXN" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),
                accountId, "DEPOSIT", amount, "Deposit to account");
        transactionRepository.save(t);
        account.addTransaction(t);
        notifyObservers(accountId, amount, "DEPOSIT");
    }

    public void withdraw(String accountId, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be positive");
        Account account = accountRepository.findById(accountId);
        if (account == null) throw new AccountNotFoundException("Account not found: " + accountId);
        if (amount > account.getBalance()) throw new InsufficientFundsException("Insufficient funds");
        try {
            account.withdraw(amount);
        } catch (Exception e) {
            throw new InsufficientFundsException(e.getMessage());
        }
        Transaction t = new Transaction("TXN" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),
                accountId, "WITHDRAW", amount, "Withdrawal from account");
        transactionRepository.save(t);
        account.addTransaction(t);
        notifyObservers(accountId, amount, "WITHDRAW");
    }

    public Account getAccount(String accountId) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId);
        if (account == null) throw new AccountNotFoundException("Account not found: " + accountId);
        return account;
    }

    public List<Account> getAccountsByCustomer(String customerId) {
        return accountRepository.findByCustomerId(customerId);
    }
}
