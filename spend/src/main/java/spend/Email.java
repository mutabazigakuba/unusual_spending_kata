package spend;

import java.util.List;

public class Email implements IEmail
{
    private UnusualSpending HighSpendings;
    private String EmailToSend;

    public Email(UnusualSpending unusualSpending)
    {
        HighSpendings = unusualSpending;
    }
    public void SendEmail(List<HighSpending> highSpendings)
    {
        
    }
}