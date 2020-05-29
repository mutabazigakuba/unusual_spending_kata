package spend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class EmailTest 
{
    Email subject;

    @Test
    public void zeroUnusualSpendingByUser()
    {
        subject = new Email();

        String expectedEmail = "* You have no unusual spendings ";
        subject.SendEmail(new ArrayList<>());
        String actualEmail = subject.message;

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void oneUnusualSpendingByUser()
    {
        List<HighSpending> highSpendingsList = new ArrayList<>();
        highSpendingsList.add(new HighSpending(150, Category.TRAVEL));
        subject = new Email();

        String expectedEmail = "* You have spent 150 on TRAVEL"+"\n";
        subject.SendEmail(highSpendingsList);
        String actualEmail = subject.message;

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void moreThanOneUnusualSpendingByUser()
    {
        List<HighSpending> unsusalSpendingList = Arrays.asList(
            new HighSpending(150, Category.TRAVEL),
            new HighSpending(150, Category.GROCERIES)
            );
        subject = new Email();
        

        String expectedEmail = "* You have spent 150 on TRAVEL"+"\n"+ "* You have spent 150 on GROCERIES"+"\n";
        subject.SendEmail(unsusalSpendingList);
        String actualEmail = subject.message;

        assertEquals(expectedEmail, actualEmail);
    }
}