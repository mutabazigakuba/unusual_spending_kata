package spend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import static org.hamcrest.CoreMatchers.*;


public class DetermineUnusualSpendingTest 
{
    @InjectMocks
    DetermineUnusualSpending subject;

    LocalDate today = LocalDate.now();
    Integer currentMonth = today.getMonthValue();
    Integer previousMonth = currentMonth-1;
    
    @Test
    public void zeroUnusualPaymentsByUser()
    {
        IPayments iPayments = mock(IPayments.class);
        List<Payments> list = Arrays.asList(
            new Payments(150, Category.TRAVEL, 1, new Date(2020, currentMonth, 22)),
            new Payments(200, Category.TRAVEL, 1, new Date(2020, previousMonth, 22))
        );
        when(iPayments.getPayments(1)).thenReturn(list);
        subject = new DetermineUnusualSpending();

        List<HighSpending> actualList = subject.Compute(list, 1);

        assertEquals(new ArrayList<>(), actualList);
    }

    @Test
    public void oneUnusualSpendingByUser()
    {
        IPayments iPayments = mock(IPayments.class);
        List<Payments> list = Arrays.asList(
            new Payments(100, Category.TRAVEL, 1, new Date(2020, currentMonth, 22)),
            new Payments(50, Category.TRAVEL, 1, new Date(2020, previousMonth, 22)),
            new Payments(20, Category.ENTERNAINMENT, 1, new Date(2020, previousMonth, 3))
        );
        when(iPayments.getPayments(1)).thenReturn(list);
        subject = new DetermineUnusualSpending();

        List<HighSpending> expectedList = Arrays.asList(new HighSpending(150, Category.TRAVEL));
        List<HighSpending> actualList = subject.Compute(list, 1);

        assertThat("all of same size", actualList.size(), is(expectedList.size()));
    }

    @Test
    public void moreThanOneUnusualSpendingByuser()
    {
        IPayments iPayments = mock(IPayments.class);
        List<Payments> list = Arrays.asList(
            new Payments(100, Category.TRAVEL, 1, new Date(2020, currentMonth, 22)),
            new Payments(50, Category.TRAVEL, 1, new Date(2020, previousMonth, 22)),
            new Payments(100, Category.GROCERIES, 1, new Date(2020, currentMonth, 22)),
            new Payments(50, Category.GROCERIES, 1, new Date(2020, previousMonth, 22)),
            new Payments(50, Category.ENTERNAINMENT, 1, new Date(2020, previousMonth, 22))
            );
        when(iPayments.getPayments(1)).thenReturn(list);
        subject = new DetermineUnusualSpending();
        List<HighSpending> unsusalSpendingList = new ArrayList<>();
        unsusalSpendingList.add(new HighSpending(150, Category.TRAVEL));
        unsusalSpendingList.add(new HighSpending(150, Category.GROCERIES));

        List<HighSpending> expectedList = unsusalSpendingList;
        List<HighSpending> actualList = subject.Compute(list, 1);

        assertThat("All are of the same size", expectedList.size(), is(actualList.size()));
    }
}