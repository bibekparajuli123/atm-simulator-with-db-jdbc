package com.atm.service;

import com.atm.dao.ATMDao;
import com.atm.model.Account;

public class ATMService {

    public void checkBalance(Account account) {
        System.out.println("\nCurrent Balance: ₹" + account.getBalance());
    }
    public void deposit(Account account, double amount, ATMDao dao) {

        if (dao.deposit(account.getAccountNo(), amount)) {

            account.setBalance(account.getBalance() + amount);

            System.out.println("Deposit Successful!");
            System.out.println("Updated Balance: ₹" + account.getBalance());

        } else {
            System.out.println("Deposit Failed!");
        }
    }
    public void withdraw(Account account, double amount, ATMDao dao) {

        if (amount > account.getBalance()) {
            System.out.println("Insufficient Balance!");
            return;
        }

        if (dao.withdraw(account.getAccountNo(), amount)) {

            account.setBalance(account.getBalance() - amount);

            System.out.println("Withdrawal Successful!");
            System.out.println("Remaining Balance: ₹" + account.getBalance());

        } else {
            System.out.println("Withdrawal Failed!");
        }
    }
    public void viewTransactions(Account account, ATMDao dao) {
        dao.viewTransactions(account.getAccountNo());
    }
    public void registerAccount(Account account, ATMDao dao) {

        if (dao.registerAccount(account)) {
            System.out.println("Account Created Successfully!");
        } else {
            System.out.println("Account Creation Failed!");
        }
    }

}