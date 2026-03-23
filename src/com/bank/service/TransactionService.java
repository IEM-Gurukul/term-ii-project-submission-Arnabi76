 package com.bank.service;

import com.bank.exception.*;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private AccountService accountService;

    public TransactionService(AccountRepository accountRepository,
                               TransactionRepository transactionRepository,
                               AccountService accountService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public void transfer(String fromAccountId, String toAccountId, double amount)
            throws AccountNotFoundException, InsufficientFundsException, InvalidAmountException {
        accountService.withdraw(fromAccountId, amount);
        accountService.deposit(toAccountId, amount);
        Transaction t = new Transaction(
                "TXN" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),
                fromAccountId, "TRANSFER", amount,
                "Transfer to " + toAccountId);
        transactionRepository.save(t);
    }

    public List<Transaction> getTransactionHistory(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
