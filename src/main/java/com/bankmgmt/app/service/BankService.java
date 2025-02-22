package com.bankmgmt.app.service;

import com.bankmgmt.app.entity.Account;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BankService {

    private List<Account> accounts = new ArrayList<>();
    private Integer currentId = 1;

    // TODO: Method to Create a new Account
    public Account createAccount(String accountHolderName, String accountType, String email) {
        if (!accountType.equals("SAVINGS") && !accountType.equals("CURRENT")) {
            throw new IllegalArgumentException("Invalid account type. It must be either SAVINGS or CURRENT.");
        }
        for (Account account : accounts) {
            if (account.getEmail().equals(email)) {
                throw new IllegalArgumentException("Email Already Exists");
            }
        }
        Account account = new Account(currentId++, accountHolderName, accountType, email, 0.0);
        accounts.add(account);
        return account;
    }

    // TODO: Method to Get All Accounts
    public List<Account> getAllAccounts() {
        return accounts;
    }

    // TODO: Method to Get Account by ID
    public Account getAccountById(Integer id) {
        return accounts.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));
    }

    // TODO: Method to Deposit Money to the specified account id
    public Account depositFunds(Integer id, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        Account account = getAccountById(id);
        account.setBalance(account.getBalance() + amount);
        return account;
    }

    // TODO: Method to Withdraw Money from the specified account id
    public Account withdrawFunds(Integer id, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        Account account = getAccountById(id);
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        account.setBalance(account.getBalance() - amount);
        return account;
    }

    // TODO: Method to Transfer Money from one account to another
    public List<Account> transferFunds(Integer fromId, Integer toId, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        Account fromAccount = getAccountById(fromId);
        Account toAccount = getAccountById(toId);

        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        return List.of(fromAccount, toAccount);
    }


    // TODO: Method to Delete Account given a account id
    public void deleteAccount(Integer id) {
        Account account = getAccountById(id);
        accounts.remove(account);
    }

}
