[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/pG3gvzt-)
# PCCCS495 – Term II Project

## Project Title
Mini Banking System

---

## Problem Statement (max 150 words)
Managing financial transactions manually is error-prone, insecure, and difficult to scale. Most student-level projects mix business logic with data access, making the codebase fragile and hard to maintain. This project addresses that by building a well-structured, console-based banking application that cleanly separates concerns across architectural layers. The system simulates real-world banking: customers can create accounts, perform deposits and withdrawals, apply for loans, and receive automated fraud alerts — all implemented in pure Java without any external frameworks. The goal is to demonstrate professional software engineering practices including layered architecture, design patterns, file-based persistence, and robust exception handling within a single, portable Java application.

---

## Target User
Bank staff (tellers) and customers interacting through a console menu. Tellers can register customers, open accounts, and process transactions. Customers can check balances, view transaction history, and apply for loans.

---

## Core Features
- Customer Management – Register and manage customer profiles with unique IDs
- Multi-Account Support – Open Savings, Current, or Loan accounts per customer
- Transactions – Deposit, withdraw, and transfer funds with full input validation
- Transaction History – Every transaction is timestamped and stored per account
- Fraud Detection – Observer pattern triggers automatic alerts for transactions above Rs.50,000
- Interest Calculation – Strategy pattern applies different rates for Savings vs Loan accounts
- Loan Management – Apply for loans, make repayments, auto-close on full repayment
- Console UI – Menu-driven interface with input validation; never crashes on bad input

---

## OOP Concepts Used
- **Abstraction:** Abstract class `Account` defines common state and methods; subclasses implement `calculateInterest()` differently
- **Inheritance:** `SavingsAccount`, `CurrentAccount`, and `LoanAccount` extend `Account`, inheriting core deposit/withdraw behaviour
- **Polymorphism:** `AccountService` calls `account.calculateInterest()` polymorphically; each subtype returns its own rate
- **Exception Handling:** Custom checked exceptions (`InsufficientFundsException`, `AccountNotFoundException`, `InvalidAmountException`) propagate from Repository → Service → UI with user-friendly messages
- **Collections / Threads:** `ArrayList` stores transaction history per account; `HashMap` maps customer and account IDs in memory across all repositories

---

## Proposed Architecture Description
The system follows a four-layer architecture. The **Presentation Layer** (`com.bank.ui`) contains `BankingConsoleUI`, which handles all user interaction and delegates every action to the service layer. The **Service Layer** (`com.bank.service`) encapsulates all business rules — `AccountService` enforces balance constraints, `TransactionService` coordinates fund transfers, and `LoanService` manages repayment schedules. The **Repository Layer** (`com.bank.repository`) abstracts all data access using in-memory HashMaps. The **Model Layer** (`com.bank.model`) contains pure POJOs — `Customer`, `Account`, and `Transaction` — with no dependencies on any other layer. Cross-cutting concerns are handled by the Observer pattern (`com.bank.observer`) for fraud alerts and the Strategy pattern (`com.bank.strategy`) for pluggable interest calculations. All error conditions surface through a custom exception hierarchy (`com.bank.exception`).

---

## How to Run
1. Clone the repository:
```
   git clone https://github.com/IEM-Gurukul/term-ii-project-submission-Arnabi76.git
```
2. Navigate to the source folder:
```
   cd term-ii-project-submission-Arnabi76/src
```
3. Compile the project:
```
   javac com/bank/ui/BankingConsoleUI.java
```
4. Run the application:
```
   java com.bank.ui.BankingConsoleUI
```
5. Follow the console menu to register customers, open accounts, deposit, withdraw, transfer, and manage loans.

**Requirements:** Java JDK 11 or higher. No external libraries needed.
```

---

## Git Discipline Notes
Minimum 10 meaningful commits required.
