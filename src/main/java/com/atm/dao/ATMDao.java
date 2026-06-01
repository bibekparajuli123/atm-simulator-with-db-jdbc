package com.atm.dao;
import java.sql.*;
import com.atm.db.DBConnection;
import com.atm.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ATMDao {

    public Account login(String accountNo, String pin) {

        String sql = "SELECT * FROM accounts WHERE account_no=? AND pin=?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, accountNo);
            ps.setString(2, pin);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(
                        rs.getString("account_no"),
                        rs.getString("holder_name"),
                        rs.getString("pin"),
                        rs.getDouble("balance")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public boolean deposit(String accountNo, double amount) {

        String updateBalance =
                "UPDATE accounts SET balance = balance + ? WHERE account_no = ?";

        String insertTransaction =
                "INSERT INTO transactions(account_no, type, amount) VALUES(?,?,?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps1 = con.prepareStatement(updateBalance);
            ps1.setDouble(1, amount);
            ps1.setString(2, accountNo);

            int rows = ps1.executeUpdate();

            if (rows > 0) {

                PreparedStatement ps2 = con.prepareStatement(insertTransaction);
                ps2.setString(1, accountNo);
                ps2.setString(2, "Deposit");
                ps2.setDouble(3, amount);

                ps2.executeUpdate();

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean withdraw(String accountNo, double amount) {

        String updateBalance =
                "UPDATE accounts SET balance = balance - ? WHERE account_no = ?";

        String insertTransaction =
                "INSERT INTO transactions(account_no, type, amount) VALUES(?,?,?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps1 = con.prepareStatement(updateBalance);
            ps1.setDouble(1, amount);
            ps1.setString(2, accountNo);

            int rows = ps1.executeUpdate();

            if (rows > 0) {

                PreparedStatement ps2 = con.prepareStatement(insertTransaction);
                ps2.setString(1, accountNo);
                ps2.setString(2, "Withdraw");
                ps2.setDouble(3, amount);

                ps2.executeUpdate();

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public void viewTransactions(String accountNo) {

        String sql =
                "SELECT * FROM transactions WHERE account_no=? ORDER BY transaction_date DESC";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, accountNo);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== TRANSACTION HISTORY =====");

            while (rs.next()) {
                System.out.println(
                        rs.getString("type") +
                                " | ₹" + rs.getDouble("amount") +
                                " | " + rs.getTimestamp("transaction_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean registerAccount(Account account) {

        String sql =
                "INSERT INTO accounts(account_no, holder_name, pin, balance) VALUES(?,?,?,?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, account.getAccountNo());
            ps.setString(2, account.getHolderName());
            ps.setString(3, account.getPin());
            ps.setDouble(4, account.getBalance());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}