package app.exceptions;

public class DatabaseException extends Exception
{
    public DatabaseException(String userMsg, String systemMsg)
    {
        super(userMsg);
        System.out.println("Usermessage: " + userMsg);
        System.out.println("Systemmessage" + systemMsg);
    }
    public DatabaseException(String msg)
    {
        super(msg);
        System.out.println(msg);
    }

    public DatabaseException()
    {
        super();
    }
}
