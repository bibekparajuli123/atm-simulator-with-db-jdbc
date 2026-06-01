package com.atm.model;

import java.sql.Timestamp;

public class Transaction {

    private int id;
    private String accountNo;
    private String type;
    private double amount;
    private Timestamp transactionDate;

    public Transaction() {
    }

    public Transaction(int id, String accountNo,
                       String type, double amount,
                       Timestamp transactionDate) {
        this.id = id;
        this.accountNo = accountNo;
        this.type = type;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    // Generate Getters and Setters
}