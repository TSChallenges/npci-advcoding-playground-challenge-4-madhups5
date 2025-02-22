package com.bankmgmt.app.rest;

import com.bankmgmt.app.entity.Account;
import com.bankmgmt.app.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: Make this class a Rest Controller
@RestController
@RequestMapping("/accounts")
public class BankController {

    @Autowired
    private BankService bankService;

    // TODO: API to Create a new account
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        try {
            Account createdAccount = bankService.createAccount(account.getAccountHolderName(), account.getAccountType(), account.getEmail());
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: API to Get all accounts
    @GetMapping
    public List<Account> getAllAccounts() {
        return bankService.getAllAccounts();
    }

    // TODO: API to Get an account by ID
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Integer id) {
        try {
            Account account = bankService.getAccountById(id);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // TODO: API to Deposit money
    @PutMapping("/{id}")
    public ResponseEntity<Account> deposit(@PathVariable Integer id, @RequestBody Double amount) {
        try {
            Account account = bankService.depositFunds(id, amount);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: API to Withdraw money
    @PutMapping("/{id}")
    public ResponseEntity<Account> withdraw(@PathVariable Integer id, @RequestBody Double amount) {
        try {
            Account account = bankService.withdrawFunds(id, amount);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: API to Transfer money between accounts
    @PutMapping
    public ResponseEntity<List<Account>> transferFunds(@RequestParam Integer fromId, @RequestParam Integer toId, @RequestParam Double amount) {
        try {
            List<Account> updatedAccounts = bankService.transferFunds(fromId, toId, amount);
            return new ResponseEntity<>(updatedAccounts, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    // TODO: API to Delete an account
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) {
        try {
            bankService.deleteAccount(id);
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
