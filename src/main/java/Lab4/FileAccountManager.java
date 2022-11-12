package Lab4;

import java.util.ArrayList;
import java.util.List;

/** Class for working with accounts. Includes register, login and remove methods. */
public class FileAccountManager implements AccountManager{

    public FileAccountManager(){}

    @Override
    public void register(Account account) throws Exception {
        if (FileService.getInstance().isAlreadyInFile(account.getEmail())) {
            throw new AccountAlreadyExistsException("This account is already exist. ");
        }
        String[] data = new String[] {account.getRowNumber(), account.getFullName(), account.getDateOfBirth(), account.getEmail(), account.getPassword()};
        FileService fileService = FileService.getInstance();
        fileService.addRecord(data);
    }

    @Override
    public Account login(String email, String password) {
        try {
            for (Account account : Account.getAllAccounts()) {
                if (email.equals(account.getEmail()) && password.equals(account.getPassword())) {
                    if (account.getBlocked()) {
                        throw new AccountBlockedException("This account is already blocked.");
                    } else {
                        FailedLoginCounter.getInstance().nullify(email);
                        return account;
                    }
                } else if (email.equals(account.getEmail())) {
                    FailedLoginCounter.getInstance().increaseCounter(email);
                    if (FailedLoginCounter.getInstance().doNeedToBlock(email)) {
                        account.setBlocked(true);
                        throw new AccountBlockedException("Now the account is blocked.");
                    }
                    throw new WrongCredentialsException("The email or password is not correct.");
                }
            }
            throw new WrongCredentialsException("The email or password is not correct.");
            } catch (AccountBlockedException | WrongCredentialsException e) {
            e.printStackTrace();
            }
        return null;
        }

    @Override
    public void removeAccount(String email, String password) throws Exception {
        FileService fileService = FileService.getInstance();
        for (Account account: Account.getAllAccounts()) {
            if (email.equals(account.getEmail()) && password.equals(account.getPassword())) {
                fileService.removeRecord(Integer.parseInt(account.getRowNumber()));
                int size = Account.getAllAccounts().size();
                Account.getAllAccounts().remove(account);
                List<Account> firstPart = Account.getAllAccounts().subList(0, Integer.parseInt(account.getRowNumber()));
                List<Account> secondPart = Account.getAllAccounts().subList(Integer.parseInt(account.getRowNumber()), size - 1);
                // Decreasing all row numbers that come after the removed one (in second part)
                for (Account account1: secondPart) {
                    account1.setRowNumber(String.valueOf(Integer.parseInt(account1.getRowNumber()) - 1));
                }
                List<Account> newList = new ArrayList<>(firstPart);
                newList.addAll(secondPart);
                Account.setAllAccounts(newList);
                return;
            }
        }
        throw new WrongCredentialsException("Cannot remove account since the email or the password is not correct.");
    }
}
