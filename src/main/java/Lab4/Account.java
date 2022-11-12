package Lab4;

import java.util.ArrayList;
import java.util.List;

/**
 * Class of account. Contains row number in a csv file, full name, date of birth, email and password.
 */
public class Account {
    private String rowNumber;
    private String fullName;
    private String dateOfBirth;
    private String email;
    private String password;
    private boolean blocked;
    private static List<Account> allAccounts = new ArrayList<>();

    public Account(String rowNumber,String fullName, String dateOfBirth, String email, String password) {
        this.rowNumber = rowNumber;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.blocked = false;
        Account.allAccounts.add(this);
    }

    public String getRowNumber() {
        return rowNumber;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public static List<Account> getAllAccounts() {
        return allAccounts;
    }

    public void setBlocked(boolean bool) {
        this.blocked = bool;
    }

    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    public static void setAllAccounts(List<Account> accountList) {
        allAccounts = accountList;
    }

    @Override
    public String toString() {
        return this.fullName + "__" + this.email;
    }
}