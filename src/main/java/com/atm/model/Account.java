package com.atm.model;

public class Account {

    private String accountNo;
    private String holderName;
    private String pin;
    private double balance;

    public Account() {
    }

    public Account(String accountNo, String holderName,
                   String pin, double balance) {
        this.accountNo = accountNo;
        this.holderName = holderName;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}