package spend.console;

import java.time.LocalDate;
import java.util.List;

import spend.core.DetermineUnusualSpending;
import spend.core.HighSpending;
import spend.core.ILocalDate;
import spend.email.Email;

public class App 
{
    public static void main(String[] args) 
    {
        Expenditures expenditures = new Expenditures();
        expenditures.Start();

        DetermineUnusualSpending determineUnusualSpending = new DetermineUnusualSpending(new ILocalDate(){
            @Override
            public LocalDate getDate() {
                return LocalDate.now();
            }
        }); 
        List<HighSpending> highSpendings = determineUnusualSpending.Compute(expenditures.getPayments(1));

        Email email = new Email();
        email.SendEmail(highSpendings);

        System.out.println(email.message);
    }
}
