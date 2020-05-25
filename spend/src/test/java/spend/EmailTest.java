package spend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailTest {

    @InjectMocks
    Email subject;

    @Test
    public void zeroUnusualSpendingByUser()
    {
        Expenditures expenditures = mock(Expenditures.class);
        when(expenditures.getPayments(1)).thenReturn(new ArrayList<>());
        UnusualSpending unusualSpending = mock(UnusualSpending.class);
        when(unusualSpending.Compute(new ArrayList<>(), 1)).thenReturn(new ArrayList<>());
        subject = new Email();

        String expectedEmail = "";
        subject.SendEmail(new ArrayList<>());
        String actualEmail = subject.EmailToSend;

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void oneUnusualSpendingByUser()
    {
        Expenditures expenditures = mock(Expenditures.class);
        List<Payments> paymentsList = new ArrayList<>();
        paymentsList.add(new Payments(100, "Travel", 1, new Date(2020, 05, 22)));
        paymentsList.add(new Payments(50, "Travel", 1, new Date(2020, 04, 22)));
        paymentsList.add(new Payments(20, "Enternaiment", 1, new Date(2020, 4, 3)));
        when(expenditures.getPayments(1)).thenReturn(paymentsList);
        UnusualSpending unusualSpending = mock(UnusualSpending.class);
        List<HighSpending> highSpendingsList = new ArrayList<>();
        highSpendingsList.add(new HighSpending(150, "Travel"));
        when(unusualSpending.Compute(paymentsList, 1)).thenReturn(highSpendingsList);
        subject = new Email();

        String expectedEmail = "* You have spent 150 on Travel";
        subject.SendEmail(highSpendingsList);
        String actualEmail = subject.EmailToSend;

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void moreThanOneUnusualSpendingByUser()
    {
        Expenditures expenditures = mock(Expenditures.class);
        List<Payments> paymentsList = new ArrayList<>();
        paymentsList.add(new Payments(100, "Travel", 1, new Date(2020, 05, 22)));
        paymentsList.add(new Payments(50, "Travel", 1, new Date(2020, 04, 22)));
        paymentsList.add(new Payments(100, "Groceries", 1, new Date(2020, 05, 22)));
        paymentsList.add(new Payments(50, "Groceries", 1, new Date(2020, 04, 22)));
        paymentsList.add(new Payments(50, "Enternaiment", 1, new Date(2020, 04, 22)));
        when(expenditures.getPayments(1)).thenReturn(paymentsList); 
        UnusualSpending unusualSpending = mock(UnusualSpending.class);
        List<HighSpending> unsusalSpendingList = new ArrayList<>();
        unsusalSpendingList.add(new HighSpending(150, "Travel"));
        unsusalSpendingList.add(new HighSpending(150, "Groceries"));
        when(unusualSpending.Compute(paymentsList, 1)).thenReturn(unsusalSpendingList);
        subject = new Email();
        

        String expectedEmail = "* You have spent 150 on Travel * You have spent 150 on Groceries ";
        subject.SendEmail(unsusalSpendingList);
        String actualEmail = subject.EmailToSend;

        assertEquals(expectedEmail, actualEmail);
    }
}