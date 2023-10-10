package com.globant.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Bank {
    private UserAccount adminAccount;
    private List<UserAccount> userAccounts;

    public Bank(String adminUsername, String adminPassword) {
        this.adminAccount = new UserAccount(adminUsername, adminPassword);
        this.userAccounts = new ArrayList<>();
    }

    public void addUserAccount(UserAccount account) {
        userAccounts.add(account);
    }

    public UserAccount getUserAccount(String username) {
        for (UserAccount account : userAccounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null; // User not found
    }

    // Nuevo método para obtener una cuenta de usuario por su nombre de usuario
    public UserAccount getUserAccountByUsername(String username) {
        for (UserAccount account : userAccounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null; // User not found
    }

    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public List<UserAccount> getAdminAccounts() {
        List<UserAccount> adminAccounts = new ArrayList<>();
        adminAccounts.add(adminAccount);
        return adminAccounts;
    }

    public void transfer(UserAccount fromAccount, UserAccount toAccount, double amount) {
        fromAccount.transfer(toAccount, amount);
    }
}
