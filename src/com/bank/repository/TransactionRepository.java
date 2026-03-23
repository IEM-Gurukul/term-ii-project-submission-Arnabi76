 package com.bank.repository;

import com.bank.model.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();

    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> findByAccountId(String accountId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAccountId().equals(accountId)) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Transaction> getAll() {
        return transactions;
    }
}
