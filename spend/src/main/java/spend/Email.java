package spend;

import java.util.List;

public class Email implements IEmail
{
    public String EmailToSend = "";

    public void SendEmail(List<HighSpending> highSpendings)
    {
        if(highSpendings.isEmpty())
        {
            EmailToSend = "* You have no unusual spendings ";
        }
        else
        {
            for (HighSpending highSpending : highSpendings) {
                EmailToSend += "* You have spent "+highSpending.Price+" on "+highSpending.Cateogory+" ";
            }
        }
    }
}