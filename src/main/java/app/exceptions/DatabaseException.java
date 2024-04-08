package app.exceptions;

public class DatabaseException extends Exception
{
    public DatabaseException(String userMsg, String systemMsg)
    {
        super(userMsg);
        System.out.println("User-message: " + userMsg);
        System.out.println("System-message: " + systemMsg);
    }
    public DatabaseException(String msg)
    {
        super(msg);
        System.out.println(msg);
    }
}
