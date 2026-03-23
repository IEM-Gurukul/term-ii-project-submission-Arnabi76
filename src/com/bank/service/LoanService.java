 package com.bank.service;

import com.bank.exception.*;
import com.bank.model.LoanAccount;
import com.bank.repository.AccountRepository;
import java.util.UUID;

public class LoanService {
    private AccountRepository accountRepository;

    public LoanService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public LoanAccount applyForLoan(String customerId, double amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Loan amount must be positive");
        String accountId = "LOAN" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        LoanAccount loan = new LoanAccount(accountId, customerId, amount);
        accountRepository.save(loan);
        return loan;
    }

    public void repayLoan(String accountId, double amount)
            throws AccountNotFoundException, InvalidAmountException {
        LoanAccount loan = (LoanAccount) accountRepository.findById(accountId);
        if (loan == null) throw new AccountNotFoundException("Loan account not found: " + accountId);
        if (amount <= 0) throw new InvalidAmountException("Repayment amount must be positive");
        try {
            loan.repay(amount);
        } catch (Exception e) {
            throw new InvalidAmountException(e.getMessage());
        }
        if (loan.isFullyRepaid()) {
            System.out.println("✅ Loan fully repaid! Account: " + accountId);
        }
    }
}
