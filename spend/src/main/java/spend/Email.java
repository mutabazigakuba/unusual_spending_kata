package spend;

import java.util.List;

public class Email implements IEmail
{
    public String message = "";

    public void SendEmail(List<HighSpending> highSpendings)
    {
        if(highSpendings.isEmpty())
        {
            message = "* You have no unusual spendings ";
        }
        else
        {
            for (HighSpending highSpending : highSpendings) {
                message += "* You have spent "+highSpending.Price+" on "+highSpending.Cateogory+"\n";
            }
        }
    }
}