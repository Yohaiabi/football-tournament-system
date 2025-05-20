package core;

/**
 * Custom exception thrown when login-related issues occur in the match context.
 */
public class LogInMatchException extends Exception {

    public LogInMatchException(String message) {
        super(message);
    }
}
