package com.globant.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccount {
    private String username;
    private String password;
    private double balance;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
        }
    }

    public void transfer(UserAccount recipient, double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            recipient.deposit(amount);
        }
    }
}
