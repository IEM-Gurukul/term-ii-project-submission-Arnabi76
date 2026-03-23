 package com.bank.repository;

import com.bank.model.Account;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepository {
    private Map<String, Account> accounts = new HashMap<>();

    public void save(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public Account findById(String accountId) {
        return accounts.get(accountId);
    }

    public List<Account> findByCustomerId(String customerId) {
        List<Account> result = new ArrayList<>();
        for (Account a : accounts.values()) {
            if (a.getCustomerId().equals(customerId)) {
                result.add(a);
            }
        }
        return result;
    }

    public boolean exists(String accountId) {
        return accounts.containsKey(accountId);
    }

    public Map<String, Account> getAll() {
        return accounts;
    }
}
