package com.atm.main;

import com.atm.dao.ATMDao;
import com.atm.model.Account;
import com.atm.service.ATMService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ATMDao dao = new ATMDao();
        ATMService service = new ATMService();

        System.out.println("=================================");
        System.out.println("         ATM SYSTEM");
        System.out.println("=================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Enter Choice: ");

        int startChoice = sc.nextInt();
        sc.nextLine();

        // Registration
        if (startChoice == 2) {

            System.out.print("Enter Account Number: ");
            String accNo = sc.nextLine();

            System.out.print("Enter Holder Name: ");
            String name = sc.nextLine();

            System.out.print("Enter PIN: ");
            String pin = sc.nextLine();

            System.out.print("Enter Initial Deposit: ");
            double balance = sc.nextDouble();

            Account newAccount =
                    new Account(accNo, name, pin, balance);

            service.registerAccount(newAccount, dao);

            sc.close();
            return;
        }

        // Login
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        Account account = dao.login(accNo, pin);

        if (account == null) {
            System.out.println("Invalid Credentials!");
            sc.close();
            return;
        }

        System.out.println("\nWelcome " + account.getHolderName());

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("         ATM SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    service.checkBalance(account);
                    break;

                case 2:
                    System.out.print("Enter Amount: ");
                    double depositAmount = sc.nextDouble();

                    service.deposit(account, depositAmount, dao);
                    break;

                case 3:
                    System.out.print("Enter Amount: ");
                    double withdrawAmount = sc.nextDouble();

                    service.withdraw(account, withdrawAmount, dao);
                    break;

                case 4:
                    service.viewTransactions(account, dao);
                    break;

                case 5:
                    System.out.println("Thank You For Using ATM!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}