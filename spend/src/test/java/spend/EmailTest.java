package spend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailTest 
{
    @InjectMocks
    Email subject;

    @Test
    public void zeroUnusualSpendingByUser()
    {
        subject = new Email();

        String expectedEmail = "* You have no unusual spendings ";
        subject.SendEmail(new ArrayList<>());
        String actualEmail = subject.EmailToSend;

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void oneUnusualSpendingByUser()
    {
        List<HighSpending> highSpendingsList = new ArrayList<>();
        highSpendingsList.add(new HighSpending(150, Category.TRAVEL));
        subject = new Email();

        String expectedEmail = "* You have spent 150 on TRAVEL ";
        subject.SendEmail(highSpendingsList);
        String actualEmail = subject.EmailToSend;

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void moreThanOneUnusualSpendingByUser()
    {
        List<HighSpending> unsusalSpendingList = new ArrayList<>();
        unsusalSpendingList.add(new HighSpending(150, Category.TRAVEL));
        unsusalSpendingList.add(new HighSpending(150, Category.GROCERIES));
        subject = new Email();
        

        String expectedEmail = "* You have spent 150 on TRAVEL * You have spent 150 on GROCERIES ";
        subject.SendEmail(unsusalSpendingList);
        String actualEmail = subject.EmailToSend;

        assertEquals(expectedEmail, actualEmail);
    }
}