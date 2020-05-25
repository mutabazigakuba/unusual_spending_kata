package spend;

import java.util.List;

public class Email implements IEmail
{
    public String EmailToSend = "";

    public void SendEmail(List<HighSpending> highSpendings)
    {
        if(highSpendings.isEmpty()){
            EmailToSend = "";
        }
        else
        {
            for (int i = 0; i < highSpendings.size(); i++) 
            {
                Integer amount = highSpendings.get(i).Price;
                String category = highSpendings.get(i).Cateogory;
                EmailToSend += "* You have spent "+amount+" on "+category+" ";
            }
        }
    }
}