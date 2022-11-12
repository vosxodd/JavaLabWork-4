package Lab4;

/** Interface for account management. */
public interface AccountManager {
    /**
     * Method checks if account exists in database according to its email
     * and if it does not, the method creates a new record.
     * If it does exist, throws new AccountAlreadyExistsException.
     */
    void register(Account account) throws Exception;

    /**
     * Method returns Account if an email and a password are
     * correct for some record and this account is not blocked.
     * If the email or the password are not correct, it throws
     * WrongCredentialsException. If account is blocked,
     * it throws AccountBlockedException. If particular user
     * tries more than 5 wrong credentials, their account
     * gets blocked.
     */
    Account login(String email, String password) throws AccountBlockedException, WrongCredentialsException;

    /**
     * Method removes user from the database if their email
     * and password are correct. Otherwise, it throws new
     * WrongCredentialsException.
     */
    void removeAccount(String email, String password) throws Exception;
}