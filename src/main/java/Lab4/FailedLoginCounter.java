package Lab4;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton-class for counting unsuccessful tries to log in.
 * In case there are more than 5 tries, the account is going to be blocked.
 */
public class FailedLoginCounter {
    private static FailedLoginCounter INSTANCE;
    private Map<String, Integer> map = new HashMap<>(); // map of unsuccessful tries

    private FailedLoginCounter() {}

    public static FailedLoginCounter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FailedLoginCounter();
        }
        return INSTANCE;
    }

    /** Increase counter of given email. If the email has not been used to log in yet,
     * it will be added to the map of tries with its counter equals zero. */
    public void increaseCounter(String email) {
        map.putIfAbsent(email, 0);
        map.put(email, map.get(email) + 1);
    }

    /** Return true if the account with given email is needed to be blocked.
     * Return false if it is not. */
    public boolean doNeedToBlock(String email) {
        return map.get(email) == 5;
    }

    /** Nullify the counter of given email in the map of tries.
     * It happens if the successful login has happened. */
    public void nullify(String email) {
        map.put(email, 0);
    }
}